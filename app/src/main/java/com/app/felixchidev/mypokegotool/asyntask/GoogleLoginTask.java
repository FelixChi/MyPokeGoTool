package com.app.felixchidev.mypokegotool.asyntask;

import android.os.AsyncTask;
import android.util.Log;

import com.pokegoapi.api.PokemonGo;
import com.pokegoapi.auth.CredentialProvider;
import com.pokegoapi.auth.GoogleUserCredentialProvider;
import com.pokegoapi.exceptions.LoginFailedException;
import com.pokegoapi.exceptions.RemoteServerException;

import okhttp3.OkHttpClient;

/**
 * Created by FelixChi on 27/8/2016.
 */
public class GoogleLoginTask extends AsyncTask<Void, Void, Void> {

    GoogleUserCredentialProvider userProvider;
    GoogleLoginListener mListener = null;
    String authCode;
    //
    OkHttpClient okHttp;
    PokemonGo pokego = null;

    public GoogleLoginTask() {
//        super();
    }

    /**
     *  Init Login Task, set okhttp, auth code
     * @param okhttp
     * @param code   Auth Code
     * @throws LoginFailedException
     * @throws RemoteServerException
     */
    public void init(OkHttpClient okhttp, String code){
        okHttp = okhttp;
        authCode = code;
        //
        Log.d("Login", "Auth Code: "+ authCode);
    }
    public void setListener(GoogleLoginListener listener)
    {
        mListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //
        mListener.onSuccess(userProvider);
    }

    @Override
    protected Void doInBackground(Void... params) {
        //
        if(authCode == null || authCode.isEmpty())
        {
            mListener.onException(new NullPointerException());
            return null;
        }
        try {
            userProvider = new GoogleUserCredentialProvider(okHttp);
            authCode = authCode.replaceAll("[\\r\\n]", "");
            userProvider.login(authCode);
            //
//            userProvider.getAuthInfo().getToken().getContents()
//            String refToken = userProvider.getRefreshToken();
            Log.d("Login", "token: "+ userProvider.getAuthInfo().getToken());

        } catch (LoginFailedException | RemoteServerException e) {
            e.printStackTrace();
            Log.d("Login", "Google auth user credential exception ");
            mListener.onException(e);
            return null;
        }
//        try {
//            //sleep
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {
//            pokego = new PokemonGo(userProvider, okHttp);
//        } catch (LoginFailedException | RemoteServerException | RuntimeException e) {
//            e.printStackTrace();
//            Log.d("Login", "PokemonGo login exception");
//            mListener.onException(e);
//        }
        return null;
    }

    public interface GoogleLoginListener
    {
        public void onSuccess(CredentialProvider userProvider);
        public void onException(Exception e);
    }
}
