package de.emgress.android.frameanimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		Button toggleAnimation = (Button)findViewById( R.id.toggle_animation );
		toggleAnimation.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				ImageView animationView = (ImageView)findViewById( R.id.animation_view );
				animationView.setVisibility( ImageView.VISIBLE );
				animationView.setBackgroundResource( R.drawable.frame_animation );

				AnimationDrawable frameAnimation = (AnimationDrawable)animationView.getBackground();

				if( frameAnimation.isRunning() )
				{
					frameAnimation.stop();
				}
				else
				{
					frameAnimation.stop();
					frameAnimation.start();
				}

			}
		});

    }
}
