package com.app.felixchidev.mypokegotool.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.felixchidev.mypokegotool.MyApplication;
import com.app.felixchidev.mypokegotool.R;
import com.app.felixchidev.mypokegotool.helper.PKMTypeHelper;
import com.app.felixchidev.mypokegotool.item.PokemonItem;
import com.app.felixchidev.mypokegotool.widget.LabelView;
import com.pokegoapi.api.PokemonGo;
import com.pokegoapi.api.pokemon.Pokemon;
import com.pokegoapi.api.pokemon.PokemonMoveMetaRegistry;
import com.pokegoapi.api.pokemon.PokemonType;
import com.pokegoapi.exceptions.LoginFailedException;
import com.pokegoapi.exceptions.RemoteServerException;

import java.util.List;

/**
 * Created by FelixChi on 1/9/2016.
 */
public class PokemonDetailActivity extends BaseActivity {
    public static String EXTRA_POKEMON_ID = "SELECTED_POKE_ID_EXTRA";

    private long selectedId;
    private PokemonItem selectedPokemon;

    TextView nameTV, cpTV, ivTV, atkTV, defTV, staTV, hpTV;
    LabelView typeTV, type2TV;
    TextView move1TV, move2TV;
    LabelView move1TypTV, move2TypTV;
    ViewGroup weaknessLL, move1WeakLL, move2WeakLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedId = getIntent().getLongExtra(EXTRA_POKEMON_ID, 0);
        initView();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pokemon_detail);
        initToolbar();
        //
        //PokemonGo pokeGo = MyApplication.getInstance().getPokemonGo();
        //
        if(selectedId ==0)
            return;
        selectedPokemon = MyApplication.getInstance().getLocalPokemon(selectedId);
        if(selectedPokemon == null)
            return;
        initPokemonView();
//        try {
//            Pokemon pokemon = pokeGo.getInventories().getPokebank().getPokemonById(selectedId);
//            selectedPokemon = PokemonItem.PokemonToItem(pokemon);
//            setTitle(selectedPokemon.pokemonName);
//            initPokemonView();
//        } catch (LoginFailedException | RemoteServerException e) {
//            e.printStackTrace();
//
//        }
        //
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitle("Pokemon");
        enableToolbarBack();
    }

    //
    private void initPokemonView()
    {
        //
        //find view
        nameTV = (TextView)findViewById(R.id.name_textView);
        cpTV = (TextView)findViewById(R.id.cp_textView);
        ivTV = (TextView)findViewById(R.id.iv_textView);
        typeTV = (LabelView)findViewById(R.id.type_textView);
        type2TV = (LabelView)findViewById(R.id.type2_textView);
        atkTV = (TextView)findViewById(R.id.atk_textView);
        defTV = (TextView)findViewById(R.id.def_textView);
        staTV = (TextView)findViewById(R.id.sta_textView);
        hpTV = (TextView)findViewById(R.id.hp_textView);
        //
        move1WeakLL = (ViewGroup)findViewById(R.id.move1_weakness_ll);
        move2WeakLL = (ViewGroup)findViewById(R.id.move2_weakness_ll);
        //
        move1TV = (TextView)findViewById(R.id.move1_name_textView);
        move2TV = (TextView)findViewById(R.id.move2_name_textView);
        move1TypTV = (LabelView)findViewById(R.id.move1_type_textView);
        move2TypTV = (LabelView)findViewById(R.id.move2_type_textView);
        //Pokemon Info (Name, hp, IV, Atk, Def, Sta, Type)
        setPokemonInfo();
        //Moves info
        setMovesView();
        //Pokemon image
        ImageView pokeIV = (ImageView)findViewById(R.id.pokemon_imageView);
        //pokemon image
        int imgid = this.getResources().getIdentifier("pokemon_"+selectedPokemon.pokemonId, "drawable", this.getPackageName());
        pokeIV.setImageResource(imgid);
    }
    private void setPokemonInfo()
    {
        setTitle(selectedPokemon.pokemonName);
        nameTV.setText(selectedPokemon.showDisplayName());
        hpTV.setText(selectedPokemon.hp+"/"+selectedPokemon.maxHp +"hp");
        cpTV.setText(selectedPokemon.cp +" cp");
        ivTV.setText("IV "+ String.format("%.2f", selectedPokemon.getIV()));
        atkTV.setText("Atk:"+selectedPokemon.ivAtk);
        defTV.setText("Def:"+selectedPokemon.ivDef);
        staTV.setText("Sta:"+selectedPokemon.ivSta);
        //
//        String type = (selectedPokemon.type2.equals(PokemonType.NONE)? selectedPokemon.type1.name():(selectedPokemon.type1.name()+"/"+selectedPokemon.type2.name()));
        typeTV.setText(selectedPokemon.type1.name());
        typeTV.setBGColor(Color.parseColor(PKMTypeHelper.getTypeColor(selectedPokemon.type1)));
        if(selectedPokemon.type2.equals(PokemonType.NONE))
        {
            type2TV.setVisibility(View.GONE);
            return;
        }
        type2TV.setVisibility(View.VISIBLE);
        type2TV.setText(selectedPokemon.type2.name());
        type2TV.setBGColor(Color.parseColor(PKMTypeHelper.getTypeColor(selectedPokemon.type2)));
        //
    }
    private void setMovesView()
    {
        String move1 = selectedPokemon.move1.name().replace("_FAST", "").replace("_", " ");
        String move2 = selectedPokemon.move2.name().replaceAll("_", " ");
        move1TV.setText(move1);
        move2TV.setText(move2);
        //
        PokemonType type1 = PokemonMoveMetaRegistry.getMeta(selectedPokemon.move1).getType();
        PokemonType type2 = PokemonMoveMetaRegistry.getMeta(selectedPokemon.move2).getType();
        move1TypTV.setText(type1.name());
        move1TypTV.setBGColor(Color.parseColor(PKMTypeHelper.getTypeColor(type1)));
        move2TypTV.setText(type2.name());
        move2TypTV.setBGColor(Color.parseColor(PKMTypeHelper.getTypeColor(type2)));
        //
        addTypeListTo(move1WeakLL, PKMTypeHelper.getTypeStrength(type1));
        addTypeListTo(move2WeakLL, PKMTypeHelper.getTypeStrength(type2));
    }
    private void addTypeListTo(ViewGroup viewGroup, List<PokemonType> types)
    {
        for (int i=0;i<types.size();i++)
        {
            LabelView labelV = LabelView.create(viewGroup);
            labelV.setText(types.get(i).name());
            labelV.setBGColor(Color.parseColor(PKMTypeHelper.getTypeColor(types.get(i))));
            viewGroup.addView(labelV);
        }
    }
}
