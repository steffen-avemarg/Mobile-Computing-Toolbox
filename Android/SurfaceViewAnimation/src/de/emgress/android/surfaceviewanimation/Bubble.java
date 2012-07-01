package de.emgress.android.surfaceviewanimation;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Bubble
{
	private float x, y, speed;

	private static final Paint paint = new Paint();

	static
	{
		paint.setColor( Color.CYAN );
		paint.setStyle( Paint.Style.FILL );
		paint.setAlpha( 77 );
		paint.setAntiAlias( true );
	}

	public static final int RADIUS = 10;
	public static final int MAX_SPEED = 10;
	public static final int MIN_SPEED = 1;

	public Bubble(float x, float y, float speed)
	{
		this.x = x;
		this.y = y;
		this.speed = Math.max(speed, MIN_SPEED);
	}

	public void draw(Canvas c)
	{
		c.drawCircle(x, y, RADIUS, paint);
	}

	public void move()
	{
		y -= speed;
	}

	public boolean outOfRange()
	{
		return (y+RADIUS < 0);
	}

}
