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
import android.widget.ProgressBar;
import android.widget.TextView;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.googlecode.androidannotations.api.SdkVersionHelper;
import de.emgress.R.id;
import de.emgress.R.layout;

public final class BackgroundExampleActivity_
    extends BackgroundExampleActivity
{

    private Handler handler_ = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.background_example);
    }

    private void init_(Bundle savedInstanceState) {
    }

    private void afterSetContentView_() {
        statusTextView = ((TextView) findViewById(id.statusTextView));
        progressBar = ((ProgressBar) findViewById(id.progressBar));
        {
            View view = findViewById(id.stopProcess);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    public void onClick(View view) {
                        stopProcess();
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.startProcess);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    public void onClick(View view) {
                        startProcess();
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.resetProcess);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    public void onClick(View view) {
                        resetProcess();
                    }

                }
                );
            }
        }
        configureViews();
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

    public static BackgroundExampleActivity_.IntentBuilder_ intent(Context context) {
        return new BackgroundExampleActivity_.IntentBuilder_(context);
    }

    @Override
    public void notifyUser(final String msg) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    BackgroundExampleActivity_.super.notifyUser(msg);
                } catch (RuntimeException e) {
                    Log.e("BackgroundExampleActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void setText(final String text) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    BackgroundExampleActivity_.super.setText(text);
                } catch (RuntimeException e) {
                    Log.e("BackgroundExampleActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void updateProgressBar(final int progress) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    BackgroundExampleActivity_.super.updateProgressBar(progress);
                } catch (RuntimeException e) {
                    Log.e("BackgroundExampleActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void runTask() {
        BackgroundExecutor.execute(new Runnable() {


            @Override
            public void run() {
                try {
                    BackgroundExampleActivity_.super.runTask();
                } catch (RuntimeException e) {
                    Log.e("BackgroundExampleActivity_", "A runtime exception was thrown while executing code in a runnable", e);
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
            intent_ = new Intent(context, BackgroundExampleActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public BackgroundExampleActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

    }

}
