package de.emgress.android.propertyanimation;

import android.animation.*;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

/**
 * http://developer.android.com/guide/topics/resources/animation-resource.html#Property
 */
public class MainActivity extends Activity
{
	private static final int ANIMATION_DURATION = 2500;

	private final int[] buttons = new int[]
			{
				R.id.object_animator, R.id.animator_set, R.id.xml_animators,
				R.id.property_value_holders, R.id.view_properties_animation,
				R.id.type_evaluators, R.id.layout_transitions
			};

	private TextView animatedView;
	private MyAnimatedView customAnimatedView;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		this.animatedView = (TextView)findViewById( R.id.text_view );
		this.customAnimatedView = new MyAnimatedView( animatedView );

		MyClickListener listener = new MyClickListener();

		for ( int buttonId : buttons )
		{
			Button button = (Button)findViewById( buttonId );
			button.setOnClickListener( listener );
		}
    }


	private class MyClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View view)
		{
			int buttonId = view.getId();

			switch( buttonId )
			{
				case R.id.object_animator:
				{
					this.handleObjectAnimator();
					break;
				}
				case R.id.animator_set:
				{
					this.handleAnimatorSet();
					break;
				}
				case R.id.xml_animators:
				{
					this.handleXMLAnimators();
					break;
				}
				case R.id.property_value_holders:
				{
					this.handlePropertyValueHolder();
					break;

				}
				case R.id.view_properties_animation:
				{
					this.handleViewPropertiesAnimation();
					break;
				}
				case R.id.type_evaluators:
				{
					this.handleTypeEvaluators();
					break;

				}
				case R.id.layout_transitions:
				{
					this.handleLayoutTransition();
					break;
				}
			}
		}

		private void handleObjectAnimator()
		{
			float toAlpha = (animatedView.getAlpha() != 0) ? 0f : 1f;

			ObjectAnimator fader =
					ObjectAnimator.ofFloat( animatedView, "alpha", toAlpha );
			fader.setDuration( ANIMATION_DURATION );

			fader.addUpdateListener( new ValueAnimator.AnimatorUpdateListener()
			{
				@Override
				public void onAnimationUpdate(ValueAnimator valueAnimator)
				{
					float value = (Float)valueAnimator.getAnimatedValue();
					animatedView.setText( "Value: " + value );

				}
			});

			fader.start();
		}

		/**
		 * Define several different animations of the same property
		 * which will run sequentially.
		 *
		 * The other option is "together" - which does not make sense
		 * regarding our fade out and fade in parts.
		 */
		private void handleAnimatorSet()
		{
			ObjectAnimator fadeOut =
					ObjectAnimator.ofFloat( animatedView, "alpha", 0f );
			ObjectAnimator fadeIn =
					ObjectAnimator.ofFloat( animatedView, "alpha", 1f );

			AnimatorSet as = new AnimatorSet();
			as.playSequentially( fadeOut, fadeIn );
			as.setDuration( ANIMATION_DURATION );
			as.start();
		}

		/**
		 * Example on how to load an animation definded via XML.
		 * Have a look at fader_animation.xml in res/animator
		 */
		private void handleXMLAnimators()
		{
			AnimatorSet as =
					(AnimatorSet)AnimatorInflater.loadAnimator(
							MainActivity.this, R.animator.fader_animation );

			as.setTarget( animatedView );
			as.start();
		}


		/**
		 * Example on how to change several properties of an
		 * object at once using PropertyValuesHolder instances.
		 */
		private void handlePropertyValueHolder()
		{
			float height = animatedView.getHeight();
			float width = animatedView.getWidth();
			float x = animatedView.getX();
			float y = animatedView.getY();

			animatedView.setX( width );
			animatedView.setY( height );

			PropertyValuesHolder pvhX =
					PropertyValuesHolder.ofFloat( "x", x );
			PropertyValuesHolder pvhY =
					PropertyValuesHolder.ofFloat( "y", y );

			ObjectAnimator oa =
					ObjectAnimator.ofPropertyValuesHolder( animatedView, pvhX, pvhY );
			oa.setDuration( ANIMATION_DURATION );
			oa.setInterpolator( new AccelerateDecelerateInterpolator() );

			oa.start();
		}


		/**
		 * Example for animating View Objects using the convenience class
		 * ViewPropertiesAnimator.
		 */
		private void handleViewPropertiesAnimation()
		{
			float height = animatedView.getHeight();
			float width = animatedView.getWidth();
			float x = animatedView.getX();
			float y = animatedView.getY();

			animatedView.setX( width );
			animatedView.setY( height );

			ViewPropertyAnimator animator = animatedView.animate();

			animator.x( x );
			animator.y( y );

			animator.setDuration(ANIMATION_DURATION);
			animator.setInterpolator( new AccelerateDecelerateInterpolator() );

			// No need to manually start animation
			// Will run as soon as the UI thread gets to it
		}

		/**
		 * Use custom model objects and animate their properties
		 * using type evaluators.
		 *
		 * See MyAnimatedView
		 */
		private void handleTypeEvaluators()
		{
			float height = animatedView.getHeight();
			float width = animatedView.getWidth();
			float x = animatedView.getX();
			float y = animatedView.getY();

			PointF startPoint = new PointF( width, height );
			PointF endPoint = new PointF( x, y );

			ObjectAnimator oa =
					ObjectAnimator.ofObject( customAnimatedView,
							"currentPosition",
							new MyPointEvaluator(),
							startPoint,
							endPoint );

			oa.setDuration( ANIMATION_DURATION );
			oa.start();
		}


		private void handleLayoutTransition()
		{
			LayoutTransition lt = new LayoutTransition();

			ViewGroup mainLayout = (ViewGroup)findViewById( R.id.main_layout );
			mainLayout.setLayoutTransition( lt );

			lt.setDuration( ANIMATION_DURATION );

			if( animatedView.getVisibility() == View.VISIBLE )
				animatedView.setVisibility( View.GONE );
			else
				animatedView.setVisibility( View.VISIBLE );
		}

	}

	/**
	 * Helper Class for the Type Evaluator Example
	 * Represents the Evaluator that is responsible to
	 * animate the PointF property of our custom View.
	 *
	 * See MyAnimatedView
	 */
	private class MyPointEvaluator implements TypeEvaluator<PointF>
	{

		@Override
		public PointF evaluate( float fraction, PointF startValue, PointF endValue)
		{
			return new PointF(
				startValue.x + (fraction * (endValue.x - startValue.x) ),
				startValue.y + (fraction * (endValue.y - startValue.y) )
			);
		}
	}

	/**
	 * Helper Class for the Type Evaluator Example
	 */
	private class MyAnimatedView
	{
		PointF currentPosition = null;
		View myView = null;

		public MyAnimatedView( View v )
		{
			this.myView = v;
			this.currentPosition = new PointF( v.getX(), v.getY() );
		}

		public PointF getCurrentPosition()
		{
			return this.currentPosition;
		}

		public void setCurrentPosition( PointF p )
		{
			this.currentPosition = p;
			this.myView.setX( p.x );
			this.myView.setY( p.y );
		}
	}
}
