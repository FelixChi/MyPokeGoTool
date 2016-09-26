package com.app.felixchidev.mypokegotool.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.app.felixchidev.mypokegotool.R;


public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    protected ProgressDialog progressDialog;
    /**
     * InputMethod Manager
     */
    protected InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    protected abstract void initView();

    protected void initToolbar()
    {
        //
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        if(mToolbar == null)
            return;
        setSupportActionBar(mToolbar);
    }
    protected void enableToolbarBack()
    {
        //
        if(mToolbar == null)
            return;
        if(getSupportActionBar() == null)
            return;
        //set back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void showProgressDialog(String msg, boolean bcancel)
    {
        //
        if(isFinishing())
            return;
        if(progressDialog == null)
        {
            //
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(bcancel);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
    }
    protected void showProgressDialog(int msgId, boolean bcancel)
    {
        //
        showProgressDialog(getString(msgId),bcancel);
    }
    protected void dismissProgressDialog()
    {
        if(progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }
}
