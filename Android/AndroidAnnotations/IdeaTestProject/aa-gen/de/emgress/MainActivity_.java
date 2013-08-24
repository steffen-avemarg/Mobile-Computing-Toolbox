//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package de.emgress;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.googlecode.androidannotations.api.SdkVersionHelper;
import de.emgress.R.id;
import de.emgress.R.layout;

public final class MainActivity_
    extends MainActivity
{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.main);
    }

    private void init_(Bundle savedInstanceState) {
    }

    private void afterSetContentView_() {
        textview = ((TextView) findViewById(id.mytextview));
        {
            View view = findViewById(id.button);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    public void onClick(View view) {
                        buttonPressed(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.mytextview);
            if (view!= null) {
                view.setOnLongClickListener(new OnLongClickListener() {


                    public boolean onLongClick(View view) {
                        resetTextView();
                        return true;
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

    public static MainActivity_.IntentBuilder_ intent(Context context) {
        return new MainActivity_.IntentBuilder_(context);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(de.emgress.R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = super.onOptionsItemSelected(item);
        if (handled) {
            return true;
        }
        switch (item.getItemId()) {
            case id.switchToDrawExample:
                return switchToDrawExample();
            case id.switchToCompassExample:
                return switchToCompassExample();
            case id.switchToViewPagerExample:
                return switchToViewPagerExample();
            case id.switchToCameraAPIExample:
                return switchToCameraAPIExample();
            case id.switchToCameraIntentExample:
                return switchToCameraIntentExample();
            case id.switchToAccelerometerExample:
                return switchToAccelerometerExample();
            case id.switchToBackgroundExample:
                return switchToBackgroundExample();
            case id.switchToTweenExample:
                return switchToTweenExample();
            default:
                return false;
        }
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, MainActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public MainActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

    }

}
