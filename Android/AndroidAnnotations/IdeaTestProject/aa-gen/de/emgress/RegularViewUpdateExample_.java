//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package de.emgress;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.googlecode.androidannotations.api.SdkVersionHelper;
import de.emgress.R.id;
import de.emgress.R.layout;

public final class RegularViewUpdateExample_
    extends RegularViewUpdateExample
{

    private Handler handler_ = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.regular_view_update);
    }

    private void init_(Bundle savedInstanceState) {
    }

    private void afterSetContentView_() {
        myView = ((DrawView) findViewById(id.drawView));
        {
            View view = findViewById(id.startMovingCircle);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    public void onClick(View view) {
                        startCircle();
                    }

                }
                );
            }
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        afterSetContentView_();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (((SdkVersionHelper.getSdkInt()< 5)&&(keyCode == KeyEvent.KEYCODE_BACK))&&(event.getRepeatCount() == 0)) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    public static RegularViewUpdateExample_.IntentBuilder_ intent(Context context) {
        return new RegularViewUpdateExample_.IntentBuilder_(context);
    }

    @Override
    public void updateUI() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    RegularViewUpdateExample_.super.updateUI();
                } catch (RuntimeException e) {
                    Log.e("RegularViewUpdateExample_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void doSomething() {
        BackgroundExecutor.execute(new Runnable() {


            @Override
            public void run() {
                try {
                    RegularViewUpdateExample_.super.doSomething();
                } catch (RuntimeException e) {
                    Log.e("RegularViewUpdateExample_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, RegularViewUpdateExample_.class);
        }

        public Intent get() {
            return intent_;
        }

        public RegularViewUpdateExample_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

    }

}
