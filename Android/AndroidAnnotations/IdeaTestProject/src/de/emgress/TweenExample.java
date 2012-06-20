package de.emgress;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;


@EActivity( R.layout.tween_example )
public class TweenExample extends Activity
{
	private static final int UNDECIDED = -1;
	private int fromTower = UNDECIDED;

	private static int[] towers = {
		R.id.tower_01,
		R.id.tower_02,
		R.id.tower_03
	};

	@AfterViews
	public void configureViews()
	{
		this.getActionBar().setTitle( "Tween Animation" );

		for( int i = 0; i < towers.length; i++ )
		{
			ViewGroup tower = (ViewGroup)findViewById( towers[i] );
			tower.setOnClickListener( new TowerPicker( i ) );
		}

	}

	private class TowerPicker implements View.OnClickListener
	{
		private int towerIndex;

		public TowerPicker( int towerIndex )
		{
			this.towerIndex = towerIndex;
		}

		public void onClick( View v )
		{
			if( fromTower == UNDECIDED )
			{
				ViewGroup tower = (ViewGroup)findViewById( towers[towerIndex] );
				if( tower.getChildCount() > 0 )
				{
					fromTower = towerIndex;

					Animation glowAnimation =
							AnimationUtils.loadAnimation(
									getApplicationContext(), R.anim.tower_glow );
					tower.startAnimation(glowAnimation);
				}
			}
			else
			{
				ViewGroup fromTowerView = (ViewGroup)findViewById( towers[fromTower] );
				if( fromTower != towerIndex )
				{
					ViewGroup toTowerView = (ViewGroup)findViewById( towers[towerIndex] );
					View block = fromTowerView.getChildAt(0);
					View supportingBlock = toTowerView.getChildAt( 0 );

					if( supportingBlock == null ||
							supportingBlock.getWidth() > block.getWidth() )
					{
						BlockMover bm = new BlockMover( block, fromTower, towerIndex );
						bm.move();
					}

					fromTowerView.clearAnimation();
					fromTower = UNDECIDED;
				}
			}
		}
	}

	private class BlockMover implements Animation.AnimationListener
	{
		private int from, to;
		View block;

		protected BlockMover( View block, int from, int to )
		{
			this.block = block;
			this.from = from;
			this.to = to;
		}

		public void move()
		{
			Animation removeAnimation = AnimationUtils.loadAnimation(
					getApplicationContext(), R.anim.block_move_right );
			removeAnimation.setAnimationListener( this );

			this.block.startAnimation( removeAnimation );

		}


		@Override
		public void onAnimationStart(Animation animation)
		{
			//To change body of implemented methods use File | Settings | File Templates.
		}

		@Override
		public void onAnimationEnd(Animation animation)
		{
			block.post(new Runnable() {
				public void run() {
					ViewGroup toTower = (ViewGroup) findViewById(towers[to]);
					ViewGroup fromTower = (ViewGroup) block.getParent();
					fromTower.removeView(block);
					fromTower.clearDisappearingChildren();
					toTower.addView(block, 0);
					Animation addAnimation =
							AnimationUtils.loadAnimation( getApplicationContext(), R.anim.drop_block);
					block.setAnimation(addAnimation);
				}
			});
		}

		@Override
		public void onAnimationRepeat(Animation animation)
		{
			//To change body of implemented methods use File | Settings | File Templates.
		}
	}

}