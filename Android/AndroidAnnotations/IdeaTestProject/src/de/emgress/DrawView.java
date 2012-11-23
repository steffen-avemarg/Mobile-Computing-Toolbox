package de.emgress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DrawView extends View
{
	private Paint paint;
	private int x = 50, y = 50;

	public DrawView(Context context, AttributeSet attrs)
	{
		super(context);
		this.paint = new Paint();
		this.paint.setAntiAlias( true );
		this.paint.setColor(Color.RED);
	}


	public void updateMyView()
	{
		Log.i( "Draw", "OnDraw x = " + x + ",  y = " + y );

		x = x + 10;
		y = y + 10;

		invalidate();
	}


	@Override
	protected void onDraw(Canvas canvas)
	{
		Log.i( "Draw", "OnDraw");
		canvas.drawCircle(x, y, 30, paint);
	}

}