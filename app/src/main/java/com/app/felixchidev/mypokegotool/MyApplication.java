package com.app.felixchidev.mypokegotool;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.app.felixchidev.mypokegotool.helper.GDAOHelper;
import com.app.felixchidev.mypokegotool.item.PokemonItem;
import com.pokegoapi.api.PokemonGo;

import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by FelixChi on 27/8/2016.
 */
public class MyApplication extends MultiDexApplication {

    private static MyApplication instance = null;
    public static MyApplication getInstance()
    {
        return instance;
    }
    private OkHttpClient okHttpClient;
    private PokemonGo pokemonGo;

    //
    private GDAOHelper gdaoHelper;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        okHttpClient = new OkHttpClient();
        //
        gdaoHelper = GDAOHelper.getInstance(getApplicationContext());
    }

    public OkHttpClient getOkHttpClient()
    {
        return okHttpClient;
    }

    public void setPokemonGo(PokemonGo go)
    {
        pokemonGo = go;
    }
    public PokemonGo getPokemonGo()
    {
        return pokemonGo;
    }
    //
    public List<PokemonItem> loadLocalPokemons()
    {
        List<DBPokemon> dbpokemons = gdaoHelper.getDPPokemonDao().loadAll();
        return PokemonItem.DBPokemonsToItems(dbpokemons);
    }
    public PokemonItem getLocalPokemon(long id)
    {
        DBPokemon dbpkm = gdaoHelper.getDPPokemonDao().load(id);
        if(dbpkm == null)
            return null;
        return PokemonItem.DBPokemonToItem(dbpkm);
    }
    public void saveLocalPokemons(List<PokemonItem> items)
    {
        gdaoHelper.getDPPokemonDao().deleteAll();
        gdaoHelper.getDPPokemonDao().insertOrReplaceInTx(PokemonItem.ItemsToDBPokemons(items));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
