package de.emgress.android.viewmatrixanimation;

import android.app.Activity;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity
{
	ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		String[] items = new String[] {
			"Item 1", "Item 2", "Item 3",
			"Item 4", "Item 5", "Item 6",
			"Item 7", "Item 8"
		};

		ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,
				android.R.layout.simple_list_item_1, items );

		lv = (ListView)findViewById( R.id.list_view );
		lv.setAdapter( adapter );

		Button startBtn = (Button)findViewById( R.id.start_animation );
		startBtn.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				lv.startAnimation(new MyListCameraAnimation());
			}
		});
    }


	class MyListCameraAnimation extends Animation
	{
		float centerX, centerY;
		Camera camera = new Camera();

		@Override
		public void initialize( int width, int height, int parentWidth, int parentHeight )
		{
			super.initialize( width, height, parentWidth, parentHeight );
			centerX = width/2;
			centerY = height/2;
			setDuration( 2500 );
			setFillAfter( true ); // Effect remains after animation
			setInterpolator( new LinearInterpolator() );
		}

		@Override
		protected void applyTransformation( float interpolatedTime, Transformation t )
		{
			final Matrix matrix = t.getMatrix();

			camera.save();
			camera.translate(0.0f, 0.0f, (1300 - 1300.0f * interpolatedTime));
			camera.rotateY(360 * interpolatedTime);
			camera.getMatrix( matrix );

			matrix.preTranslate( -centerX, -centerY );
			matrix.postTranslate( centerX, centerY );
			camera.restore();

		}
	}


	class MyListAnimation extends Animation
	{
		float centerX, centerY;

		@Override
		public void initialize( int width, int height, int parentWidth, int parentHeight )
		{
			super.initialize( width, height, parentWidth, parentHeight );
			centerX = width/2;
			centerY = height/2;
			setDuration( 2500 );
			setFillAfter( true ); // Effect remains after animation
			setInterpolator( new LinearInterpolator() );
		}

		@Override
		protected void applyTransformation( float interpolatedTime, Transformation t )
		{
			final Matrix matrix = t.getMatrix();
			matrix.setScale( interpolatedTime, interpolatedTime );
			matrix.preTranslate( -centerX, -centerY );
			matrix.postTranslate( centerX, centerY );

		}
	}

}


