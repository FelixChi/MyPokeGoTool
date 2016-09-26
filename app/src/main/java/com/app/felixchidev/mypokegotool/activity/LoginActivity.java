package com.app.felixchidev.mypokegotool.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.felixchidev.mypokegotool.MyApplication;
import com.app.felixchidev.mypokegotool.R;
import com.app.felixchidev.mypokegotool.asyntask.GoogleLoginTask;
import com.app.felixchidev.mypokegotool.asyntask.PokeGoLoginTask;
import com.pokegoapi.api.PokemonGo;
import com.pokegoapi.auth.CredentialProvider;
import com.pokegoapi.auth.GoogleUserCredentialProvider;

/**
 * Created by FelixChi on 27/8/2016.
 */
public class LoginActivity extends BaseActivity {
    public static String EXTRA_LOGIN_MODE = "LOGIN_MODE";
    public static int OFFLINE_LOGIN = 0;
    public static int LOGIN_GOOGLE = 1;
    public static int LOGIN_PTC = 2;

    Activity activity;
    EditText authCodeET;
    String authCode;

    CredentialProvider credProvider = null;

    Button loginBtn, getAuthBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        initView();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.login_activity);
//        initToolbar();
        //
        authCodeET = (EditText)findViewById(R.id.authcode_editText);
        getAuthBtn = (Button)findViewById(R.id.get_auth_btn);
        getAuthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGoogleAuthCode();
            }
        });
        loginBtn = (Button)findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authCode = authCodeET.getText().toString();
                if (TextUtils.isEmpty(authCode))
                    return;
//                if(credProvider == null)
                LoginGoogle();
//                else if(!credProvider.isTokenIdExpired())
//                    LoginPokeGo(credProvider);
            }
        });
        //
        findViewById(R.id.offline_login_textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offlineLogin();
            }
        });
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitle("Login");
    }

    private void getGoogleAuthCode()
    {
        //GoogleUserCredentialProvider.LOGIN_URL
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GoogleUserCredentialProvider.LOGIN_URL)));
    }

    private void LoginGoogle()
    {
        //
        GoogleLoginTask loginTask = new GoogleLoginTask();
        Log.d("Login", "OKHttp "+ MyApplication.getInstance().getOkHttpClient());
        Log.d("Login", "LoginTask "+ loginTask);
        loginTask.init(MyApplication.getInstance().getOkHttpClient(), authCode);
        loginTask.setListener(new GoogleLoginTask.GoogleLoginListener() {
            @Override
            public void onSuccess(CredentialProvider userProvider) {
                //
                if (userProvider == null) {
                    //login fail
//                    Toast.makeText(LoginActivity.this, "login fail", Toast.LENGTH_LONG).show();
                    return;
                }
//                Toast.makeText(LoginActivity.this, "login success", Toast.LENGTH_SHORT).show();
                //
//                MyApplication.getInstance().setPokemonGo(go);
                //
//                startActivity(new Intent(LoginActivity.this, PokemonBankActivity.class));
                //
                credProvider = userProvider;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "google login auth success", Toast.LENGTH_SHORT).show();
                    }
                });
                LoginPokeGo(userProvider);
            }

            @Override
            public void onException(Exception e) {
                //
                Log.d("Login", "login exception: " + e.getMessage());
//                Toast.makeText(LoginActivity.this, "login exception: " + e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        loginTask.execute();
    }
    private void LoginPokeGo(CredentialProvider provider)
    {
        PokeGoLoginTask task = new PokeGoLoginTask();
        task.init(MyApplication.getInstance().getOkHttpClient(), provider);
        task.setListener(new PokeGoLoginTask.PokeGoLoginListener() {
            @Override
            public void onSuccess(PokemonGo go) {
                if(go == null)
                {
                    return;
                }
                //
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "poke go login success", Toast.LENGTH_SHORT).show();
                    }
                });
                MyApplication.getInstance().setPokemonGo(go);
                //
                startActivity(new Intent(LoginActivity.this, PokemonBankActivity.class).putExtra(EXTRA_LOGIN_MODE, LOGIN_GOOGLE));
            }

            @Override
            public void onException(Exception e) {
                Log.d("Login", "login exception: " + e.getMessage());
            }
        });
        task.execute();
    }

    private void offlineLogin()
    {
        //
        startActivity(new Intent(LoginActivity.this, PokemonBankActivity.class).putExtra(EXTRA_LOGIN_MODE, OFFLINE_LOGIN));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
