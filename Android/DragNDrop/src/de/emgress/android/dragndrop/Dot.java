package de.emgress.android.dragndrop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import de.fhe.R;

public class Dot extends View 
{
	private static final String TAG = "TouchDrag";
	private float left = 0;
	private float top = 0;
	private float radius = 20;
	private float offsetX = 0;
	private float offsetY = 0;
	private Paint myPaint;
	private MainActivity myContext;
	
	public Dot(Context context, AttributeSet attrs, int defStyle) 
	{
		this( context, attrs );
	}

	public Dot(Context context) 
	{
		this( context, null );
	}

	public Dot(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		this.myContext = (MainActivity)context;
		this.myPaint = new Paint();
		this.myPaint.setColor( Color.WHITE );
		this.myPaint.setAntiAlias( true );
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		int action = event.getAction();
		float eventX = event.getX();
		float eventY = event.getY();
		
		switch(action)
		{
		case MotionEvent.ACTION_DOWN:
			if( !(left-20 < eventX && eventX < left+radius*2+20 && 
					top-20 < eventY && eventY < top+radius*2+20) )
				return false;
			offsetX = eventX - left;
			offsetY = eventY - top;
			break;
		case MotionEvent.ACTION_MOVE:
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			left = eventX - offsetX;
			top = eventY - offsetY;
			if( action == MotionEvent.ACTION_UP ) 
				checkDrop( eventX, eventY );
			break;
		}
		invalidate();
		return true;
	}
	

	
	private void checkDrop( float x, float y )
	{
		TextView tv = (TextView)myContext.findViewById( R.id.textView );
		
		if( x > tv.getLeft() - 20 && tv.getTop() - 20 < y && y < tv.getBottom() + 20 )
		{
			int count = Integer.parseInt(tv.getText().toString() );
			tv.setText( String.valueOf( ++count ) );
			left = top = 0;
		}
	}
	
	@Override
	public void draw(Canvas canvas) 
	{
		canvas.drawCircle( left+radius, top+radius, radius, myPaint );
	}


}
