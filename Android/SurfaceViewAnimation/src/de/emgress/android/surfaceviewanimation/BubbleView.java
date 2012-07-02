package de.emgress.android.surfaceviewanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class BubbleView extends SurfaceView implements SurfaceHolder.Callback
{
	private float BUBBLE_FREQUENCY = 0.3f;
    private float NUMBER_OF_BUBBLES_PER_FRAME = 2;

	private ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
	private Paint backgroundPaint = new Paint();

	private SurfaceHolder surfaceHolder;
	private GameLoop gameLoop;


	public BubbleView( Context context, AttributeSet attrs )
	{
		super(context, attrs);
		getHolder().addCallback( this );
		backgroundPaint.setColor( Color.BLUE );
	}

	private void drawScreen( Canvas c )
	{
		c.drawRect(
				0,
				0,
				c.getWidth(),
				c.getHeight(),
				backgroundPaint );

		for ( Bubble bubble : bubbles )
		{
			bubble.draw( c );
		}
	}

	private void calculateDisplay( Canvas c )
	{
        for( int i = 0; i < NUMBER_OF_BUBBLES_PER_FRAME; i++ )
            randomlyAddBubbles( c.getWidth(), c.getHeight() );

		ArrayList<Bubble> bubblesToRemove = new ArrayList<Bubble>();

		for ( Bubble bubble : bubbles )
		{
			bubble.move();

			if ( bubble.outOfRange() )
			{
				bubblesToRemove.add( bubble );
			}
		}

		this.bubbles.removeAll( bubblesToRemove );
	}

	public void randomlyAddBubbles( int screenWidth, int screenHeight )
	{
		if ( Math.random() > BUBBLE_FREQUENCY ) return;

		bubbles.add(
				new Bubble(
						(int) ( screenWidth * Math.random() ),
                        (int) ( screenHeight + Bubble.MAX_RADIUS ),
						(int) ( Bubble.MAX_RADIUS * Math.random() ),
						(int) ( Bubble.MAX_SPEED * Math.random() )
				));
	}


	private class GameLoop extends Thread
	{
		private long msPerFrame = 1000/25;
		public boolean running = true;
		long frameTime = 0;

		public void run()
		{
			Canvas canvas = null;
			frameTime = System.currentTimeMillis();
			final SurfaceHolder surfaceHolder = BubbleView.this.surfaceHolder;

			while (running)
			{
				try
				{
					canvas = surfaceHolder.lockCanvas();

                    if( canvas == null ) continue;

					synchronized ( surfaceHolder )
					{
						calculateDisplay( canvas );
						drawScreen( canvas );
					}
				}
				finally
				{
					if ( canvas != null )
						surfaceHolder.unlockCanvasAndPost( canvas );

				}

				waitTillNextFrame();
			}
		}

		private void waitTillNextFrame()
		{
			long nextSleep = 0;
			frameTime += msPerFrame;
			nextSleep = frameTime - System.currentTimeMillis();

			if ( nextSleep > 0 )
			{
				try
				{
					sleep( nextSleep );
				}
				catch ( InterruptedException e ) {}
			}
		}
	}


	public void startAnimation()
	{
		synchronized (this)
		{
			if ( gameLoop == null )
			{
				gameLoop = new GameLoop();
			}

			gameLoop.start();
		}
	}

	public void stopAnimation()
	{
		synchronized (this)
		{
			boolean retry = true;
			if ( gameLoop != null )
			{
				gameLoop.running = false;

				while ( retry )
				{
					try
					{
						gameLoop.join();
						retry = false;
					}
					catch ( InterruptedException e )	{}
				}
			}

			gameLoop = null;
		}
	}

	/*
	 * SurfaceHolder Callback Methods
	 *
	 */
	@Override
	public void surfaceCreated( SurfaceHolder surfaceHolder )
	{
		this.surfaceHolder = surfaceHolder;
		startAnimation();
	}

	@Override
	public void surfaceChanged( SurfaceHolder surfaceHolder, int format, int width, int height )
    {
        stopAnimation();
        this.surfaceHolder = surfaceHolder;
        startAnimation();
    }

	@Override
	public void surfaceDestroyed( SurfaceHolder surfaceHolder )
	{
		stopAnimation();
	}
}
