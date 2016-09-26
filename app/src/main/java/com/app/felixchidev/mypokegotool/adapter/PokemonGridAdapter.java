package com.app.felixchidev.mypokegotool.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.felixchidev.mypokegotool.R;
import com.app.felixchidev.mypokegotool.item.PokemonItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FelixChi on 27/8/2016.
 */
public class PokemonGridAdapter extends RecyclerView.Adapter<PokemonGridAdapter.PokemonViewHolder>{

    Context mContext;
    private List<PokemonItem> mPokemons;
    private PokemonClickListener mListener;

    public PokemonGridAdapter(Context ctx)
    {
        mContext = ctx;
        mPokemons = new ArrayList<>();
    }

    public void UpdateData(List<PokemonItem> pokemons)
    {
        mPokemons = pokemons;
        notifyDataSetChanged();
    }

    public void setListener(PokemonClickListener listener)
    {
        mListener = listener;
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.pokemon_grid_layout, parent, false);

        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        holder.setContentView(mPokemons.get(position));
    }

    @Override
    public int getItemCount() {
        return mPokemons.size();
    }

    class PokemonViewHolder extends RecyclerView.ViewHolder
    {

        View rootView;
        TextView idTV, cpTV, nameTV, hpTV, ivTV, atkTV, defTV, staTV;
        ImageView pokemonIV;
        public PokemonViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    PokemonItem item = mPokemons.get(getLayoutPosition());
                    if(mListener != null)
                        mListener.onClickPokemon(item);
                }
            });
            idTV = (TextView)itemView.findViewById(R.id.no_textView);
            cpTV = (TextView)itemView.findViewById(R.id.cp_textView);
            nameTV = (TextView)itemView.findViewById(R.id.name_textView);
            hpTV = (TextView)itemView.findViewById(R.id.hp_textView);
            ivTV = (TextView)itemView.findViewById(R.id.iv_textView);
            atkTV = (TextView)itemView.findViewById(R.id.atk_textView);
            defTV = (TextView)itemView.findViewById(R.id.def_textView);
            staTV = (TextView)itemView.findViewById(R.id.sta_textView);
            //
            pokemonIV = (ImageView)itemView.findViewById(R.id.pokemon_imageView);
        }

        public void setContentView(PokemonItem pokemon)
        {
            //
            idTV.setText(""+pokemon.pokemonId);
            String name = pokemon.showDisplayName();
            nameTV.setText(name);
            cpTV.setText(pokemon.cp +" cp");
            hpTV.setText(pokemon.hp +"/"+pokemon.maxHp +"hp");
            ivTV.setText("IV: "+String.format("%.2f", pokemon.getIV()));
            atkTV.setText("Atk: "+pokemon.ivAtk);
            defTV.setText("Def: "+pokemon.ivDef);
            staTV.setText("Sta: "+pokemon.ivSta);
            //pokemon image
            int imgid = mContext.getResources().getIdentifier("pokemon_"+pokemon.pokemonId, "drawable", mContext.getPackageName());
            pokemonIV.setImageResource(imgid);
        }
    }

    public interface PokemonClickListener
    {
        void onClickPokemon(PokemonItem pokemon);
    }
}
