package com.app.felixchidev.mypokegotool.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.felixchidev.mypokegotool.MyApplication;
import com.app.felixchidev.mypokegotool.R;
import com.app.felixchidev.mypokegotool.adapter.PokemonGridAdapter;
import com.app.felixchidev.mypokegotool.asyntask.GetUserInventoriesTask;
import com.app.felixchidev.mypokegotool.item.PokemonItem;
import com.pokegoapi.api.PokemonGo;
import com.pokegoapi.api.inventory.Inventories;
import com.pokegoapi.api.pokemon.Pokemon;
import com.pokegoapi.api.pokemon.PokemonMoveMeta;
import com.pokegoapi.api.pokemon.PokemonMoveMetaRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by FelixChi on 28/8/2016.
 */
public class PokemonBankActivity extends BaseActivity {


    RecyclerView recyclerView;
    PokemonGridAdapter mAdapter;
    //
    Spinner sortSpinner;
    //
    List<PokemonItem> mPokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        //
        int loginMode = getIntent().getIntExtra(LoginActivity.EXTRA_LOGIN_MODE, 0);
        if(loginMode != 0)
            loadUserInventories();
        else
        {
            //offline
            loadLocalInventories();
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pokebank);
        initToolbar();
        //
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mAdapter = new PokemonGridAdapter(this);
        mAdapter.setListener(new PokemonGridAdapter.PokemonClickListener() {
            @Override
            public void onClickPokemon(PokemonItem pokemon) {
                //
//                Toast.makeText(PokemonBankActivity.this, "clicked "+ pokemon.id, Toast.LENGTH_LONG).show();
                //
                gotoPkmDetail(pokemon);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(mAdapter);
        //
        sortSpinner = (Spinner)findViewById(R.id.sort_spinner);
        sortSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"IV", "CP", "Number", "HP", "Battle"}));
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //
                if (mPokemons == null)
                    return;
                List<PokemonItem> items = new ArrayList<PokemonItem>();
                switch (position) {
                    case 0:
                        items = sortIV(mPokemons);
                        break;
                    case 1:
                        items = sortCP(mPokemons);
                        break;
                    case 2:
                        items = sortNumber(mPokemons);
                        break;
                    case 3:
                        items = sortHP(mPokemons);
                        break;
                    case 4:
                        items = filterGreat(mPokemons);
                        break;
                }
                mAdapter.UpdateData(items);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    //
    private void loadUserInventories()
    {
        PokemonGo go = MyApplication.getInstance().getPokemonGo();
        GetUserInventoriesTask invenTask = new GetUserInventoriesTask(go);
        invenTask.setListener(new GetUserInventoriesTask.UserInventoriesListener() {
            @Override
            public void onSuccess(Inventories inven) {
                //
                List<PokemonItem> pokemonItems = PokemonsToItems(inven.getPokebank().getPokemons());

                pokemonItems = sortIV(pokemonItems);
                mPokemons = pokemonItems;
                mAdapter.UpdateData(pokemonItems);
                setTitle("Pokemons " + pokemonItems.size());
                //
                try {
                    MyApplication.getInstance().saveLocalPokemons(mPokemons);
                } catch (Exception e) {
                    Toast.makeText(PokemonBankActivity.this, "save pokemons to local DB exception " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onException(Exception e) {

            }
        });
        invenTask.execute();
    }
    private void loadLocalInventories()
    {
        //
        mPokemons = MyApplication.getInstance().loadLocalPokemons();
        sortSpinner.setSelection(0);
//        mAdapter.UpdateData(mPokemons);
        setTitle("Pokemons " + mPokemons.size());
    }
    private List<PokemonItem> PokemonsToItems(List<Pokemon> pokemons)
    {
        List<PokemonItem> pokemonItems = new ArrayList<>();
        //
        for(int i=0;i<pokemons.size();i++)
        {
            Pokemon pokemon = pokemons.get(i);
            pokemon.getMove1().getNumber();

            PokemonItem item = PokemonItem.PokemonToItem(pokemon);
            //
            pokemon.debug();
            Log.d("Pokemon", "pokemon : "+ item.DisplayName +" " + item.id +" "+ item.pokemonId +" "+ item.pokemonName +" "+ item.cp +", " + item.getIV());
            //
            pokemonItems.add(item);
        }
        return pokemonItems;
    }

    //Sorting
    private List<PokemonItem> sortCP(List<PokemonItem> pokemons)
    {
        List<PokemonItem> items = pokemons;
        Collections.sort(pokemons, new Comparator<PokemonItem>() {
            @Override
            public int compare(PokemonItem lhs, PokemonItem rhs) {
                //cp
                if(lhs.cp < rhs.cp)
                {
                    return 1;
                }
                else if(lhs.cp == rhs.cp)
                    return 0;
                return (-1);
            }
        });
        return pokemons;
    }
    private List<PokemonItem> sortIV(List<PokemonItem> pokemons)
    {
        List<PokemonItem> items = pokemons;
        Collections.sort(pokemons, new Comparator<PokemonItem>() {
            @Override
            public int compare(PokemonItem lhs, PokemonItem rhs) {
                //pokemon IV
                if(lhs.getIV() < rhs.getIV())
                {
                    return 1;
                }
                else if(lhs.getIV() == rhs.getIV())
                    return 0;
                return (-1);
            }
        });
        return pokemons;
    }
    private List<PokemonItem> sortNumber(List<PokemonItem> pokemons)
    {
        List<PokemonItem> items = pokemons;
        Collections.sort(pokemons, new Comparator<PokemonItem>() {
            @Override
            public int compare(PokemonItem lhs, PokemonItem rhs) {
                //pokemon number
                return (lhs.pokemonId - rhs.pokemonId);
            }
        });
        return pokemons;
    }
    private List<PokemonItem> sortHP(List<PokemonItem> pokemons)
    {
        List<PokemonItem> items = pokemons;
        Collections.sort(pokemons, new Comparator<PokemonItem>() {
            @Override
            public int compare(PokemonItem lhs, PokemonItem rhs) {
                //sort hp
                if(lhs.maxHp < rhs.maxHp)
                {
                    return 1;
                }
                else if(lhs.maxHp == rhs.maxHp)
                    return 0;
                return (-1);
            }
        });
        return pokemons;
    }
    //filter great pokemon
    private List<PokemonItem> filterGreat(List<PokemonItem> pokemons)
    {
        List<PokemonItem> items = new ArrayList<>();
        //filter CP >500, IV > 80
        for(int i=0;i<pokemons.size();i++)
        {
            //
            PokemonItem pokeItem = pokemons.get(i);
            if(pokeItem.cp > 400 && pokeItem.getIV() >79)
            {
                items.add(pokeItem);
            }
        }
        return items;
    }
    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitle("Pokemons");
    }

    private void gotoPkmDetail(PokemonItem pokemon)
    {
        //
        startActivity(new Intent(this, PokemonDetailActivity.class).putExtra(PokemonDetailActivity.EXTRA_POKEMON_ID, pokemon.id));
    }
}
