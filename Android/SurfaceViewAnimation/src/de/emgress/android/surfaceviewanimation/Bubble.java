package de.emgress.android.surfaceviewanimation;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Bubble
{
	private float x, y, radius, speed;

	private static final Paint paint = new Paint();

	static
	{
		paint.setColor( Color.CYAN );
		paint.setStyle( Paint.Style.FILL );
		paint.setAlpha( 77 );
		paint.setAntiAlias( true );
	}

    public static final int MAX_RADIUS = 15;
    public static final int MIN_RADIUS = 5;
	public static final int MAX_SPEED = 10;
	public static final int MIN_SPEED = 1;

	public Bubble( float x, float y, float radius, float speed )
	{
		this.x = x;
		this.y = y;
        this.radius = Math.max( radius, MIN_RADIUS );
		this.speed = Math.max( speed, MIN_SPEED );
	}

	public void draw( Canvas c )
	{
		c.drawCircle( x, y, radius, paint );
	}

	public void move()
	{
		y -= speed;
	}

	public boolean outOfRange()
	{
		return ( y + radius < 0 );
	}

}
