package com.app.felixchidev.mypokegotool.asyntask;

import android.os.AsyncTask;
import android.util.Log;

import com.pokegoapi.api.PokemonGo;
import com.pokegoapi.auth.CredentialProvider;
import com.pokegoapi.exceptions.LoginFailedException;
import com.pokegoapi.exceptions.RemoteServerException;

import okhttp3.OkHttpClient;

/**
 * Created by FelixChi on 27/8/2016.
 */
public class PokeGoLoginTask extends AsyncTask<Void, Void, Void> {

    CredentialProvider userProvider;
    PokeGoLoginListener mListener = null;
    String authCode;
    //
    OkHttpClient okHttp;
    PokemonGo pokego = null;

    public PokeGoLoginTask() {
//        super();
    }

    /**
     *  Init Login Task, set okhttp, auth code
     * @param okhttp
     * @param provider   CredentialProvider
     * @throws LoginFailedException
     * @throws RemoteServerException
     */
    public void init(OkHttpClient okhttp, CredentialProvider provider){
        okHttp = okhttp;
        //
        userProvider = provider;
    }
    public void setListener(PokeGoLoginListener listener)
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
        mListener.onSuccess(pokego);
    }

    @Override
    protected Void doInBackground(Void... params) {
        //
        try {
            pokego = new PokemonGo(userProvider, okHttp);
        } catch (LoginFailedException | RemoteServerException | RuntimeException e) {
            e.printStackTrace();
            Log.d("Login", "PokemonGo login exception");
            mListener.onException(e);
        }
        return null;
    }

    public interface PokeGoLoginListener
    {
        public void onSuccess(PokemonGo go);
        public void onException(Exception e);
    }
}
