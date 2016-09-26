package com.app.felixchidev.mypokegotool.asyntask;

import android.os.AsyncTask;
import android.util.Log;

import com.pokegoapi.api.PokemonGo;
import com.pokegoapi.api.inventory.Inventories;
import com.pokegoapi.api.pokemon.Pokemon;
import com.pokegoapi.exceptions.LoginFailedException;
import com.pokegoapi.exceptions.RemoteServerException;

import java.util.List;

/**
 * Created by FelixChi on 28/8/2016.
 */
public class GetUserInventoriesTask extends AsyncTask<Void, Void, Void>{

    PokemonGo pokego = null;
    UserInventoriesListener mListener;

    Inventories inventories = null;

    public GetUserInventoriesTask(PokemonGo go)
    {
        pokego = go;
    }
    public void setListener(UserInventoriesListener listener)
    {
        mListener = listener;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //
        mListener.onSuccess(inventories);

    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            inventories = pokego.getInventories();
            Log.d("UserInventories", "get user inventories pokemon count "+ inventories.getPokebank().getPokemons().size());
//            List<Pokemon> pokemons =  inventories.getPokebank().getPokemons();
//            Pokemon pokemon = pokemons.get(0);
//            pokemon.getId();
//            pokemon.getStamina();
//            pokemon.getCp();
//            pokemon.getBaseStam();
//            pokemon.getIndividualAttack();
//            pokemon.getIndividualDefense();
//            pokemon.getIndividualStamina();
//            pokemon.getIvRatio();
//            pokemon.getCreationTimeMs();
//            pokemon.getPokemonFamily().getNumber();
//            pokemon.getMeta().getType1();
//            pokemon.getMeta().getType2();
//            pokemon.getMove1();
//            pokemon.getMove2();

        } catch (LoginFailedException | RemoteServerException e) {
            e.printStackTrace();
            mListener.onException(e);
        }
        return null;
    }

    public interface UserInventoriesListener
    {
        public void onSuccess(Inventories inven);
        public void onException(Exception e);
    }
}
