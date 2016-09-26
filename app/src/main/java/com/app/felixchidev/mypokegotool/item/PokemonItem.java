package com.app.felixchidev.mypokegotool.item;

import com.app.felixchidev.mypokegotool.DBPokemon;
import com.pokegoapi.api.pokemon.Pokemon;
import com.pokegoapi.api.pokemon.PokemonMetaRegistry;
import com.pokegoapi.api.pokemon.PokemonMoveMetaRegistry;
import com.pokegoapi.api.pokemon.PokemonType;

import java.util.ArrayList;
import java.util.List;

import POGOProtos.Enums.PokemonIdOuterClass;
import POGOProtos.Enums.PokemonMoveOuterClass;
import POGOProtos.Enums.PokemonTypeOuterClass;

/**
 * Created by FelixChi on 27/8/2016.
 */
public class PokemonItem {

    public long id;
    public int pokemonId;
    public String pokemonName;
    public int cp;
    public String DisplayName;
    public double ivRate;
    public int ivAtk;
    public int ivDef;
    public int ivSta;
    public int hp;
    public int maxHp;

    public long createTime;
    //
    public PokemonType type1;
    public PokemonType type2;

    public PokemonMoveOuterClass.PokemonMove move1;
    public PokemonMoveOuterClass.PokemonMove move2;

    public float getIV()
    {
        return (float)(ivRate *100f);
    }
    public String getName()
    {
        return DisplayName.isEmpty()? pokemonName:DisplayName;
    }
    public String showDisplayName()
    {
        String showName = DisplayName.isEmpty()? pokemonName:DisplayName;
        //
        showName = showName.replace("_FEMALE", " F").replace("_MALE", " M").replace("_", " ");
        return showName;
    }


    public static PokemonItem PokemonToItem(Pokemon pokemon)
    {
        PokemonItem item = new PokemonItem();
        item.id = pokemon.getId();
        item.DisplayName = pokemon.getNickname();
        item.pokemonName = pokemon.getPokemonId().name();
        //
        //current hp
        item.hp = pokemon.getStamina();
        //max hp
        item.maxHp = pokemon.getMaxStamina();
        //
        item.cp = pokemon.getCp();
        item.ivAtk = pokemon.getIndividualAttack();
        item.ivDef = pokemon.getIndividualDefense();
        item.ivSta = pokemon.getIndividualStamina();
        item.ivRate = pokemon.getIvRatio();
        //
        item.createTime = pokemon.getCreationTimeMs();
        //Pokemon No
        item.pokemonId = pokemon.getPokemonId().getNumber();
        //
        item.type1 = pokemon.getMeta().getType1();
        item.type2 = pokemon.getMeta().getType2();
        item.move1 = pokemon.getMove1();
        item.move2 = pokemon.getMove2();
//        pokemon.getMove1()

        return item;
    }

    /**
     * DB Pokemon to PokemonItem
     * @param dbpokemon
     * @return PokemonItem
     */
    public static PokemonItem DBPokemonToItem(DBPokemon dbpokemon)
    {
        PokemonItem item = new PokemonItem();
        item.id = dbpokemon.getId();
        item.DisplayName = dbpokemon.getNickname();
        item.pokemonName = dbpokemon.getName();
        //Pokemon No
        item.pokemonId= dbpokemon.getPokemon_id();
        //
        //current hp
        item.hp = dbpokemon.getHp();
        //max hp
        item.maxHp = dbpokemon.getMaxHP();
        //
        item.cp = dbpokemon.getCp();
        item.ivAtk = dbpokemon.getIV_Atk();
        item.ivDef = dbpokemon.getIV_Def();
        item.ivSta = dbpokemon.getIV_Sta();
        item.ivRate = dbpokemon.getIv();
        //
        item.createTime = dbpokemon.getCreateTime();
        //
//        String strTyp1 = PokemonTypeOuterClass.PokemonType.forNumber(dbpokemon.getType1()).name();
        item.type1 = PokemonType.values()[(dbpokemon.getType1()<0? 0:dbpokemon.getType1())];
        item.type2 = PokemonType.values()[(dbpokemon.getType1()<0? 0:dbpokemon.getType2())];
//        item.type1 = PokemonType.valueOf(PokemonTypeOuterClass.PokemonType.forNumber(dbpokemon.getType1()).name());
//        item.type2 = PokemonType.valueOf(PokemonTypeOuterClass.PokemonType.forNumber(dbpokemon.getType2()).name());
        //
        item.move1 = PokemonMoveOuterClass.PokemonMove.forNumber(dbpokemon.getMove1());
        item.move2 = PokemonMoveOuterClass.PokemonMove.forNumber(dbpokemon.getMove2());
        //get move type
//        PokemonMoveMetaRegistry.getMeta(item.move1).getType();

        return item;
    }
    public static List<PokemonItem> DBPokemonsToItems(List<DBPokemon> dbpokemons)
    {
        List<PokemonItem> items = new ArrayList<>();
        for(int i=0;i<dbpokemons.size();i++)
        {
            items.add(DBPokemonToItem(dbpokemons.get(i)));
        }
        return items;
    }

    public static DBPokemon ItemToDBPokemon(PokemonItem item)
    {
        DBPokemon dbpokemon = new DBPokemon(item.id);
        dbpokemon.setPokemon_id(item.pokemonId);
        dbpokemon.setName(item.pokemonName);
        dbpokemon.setNickname(item.DisplayName);
        //
        dbpokemon.setCp(item.cp);
        dbpokemon.setIv(item.ivRate);
        dbpokemon.setIV_Atk(item.ivAtk);
        dbpokemon.setIV_Def(item.ivDef);
        dbpokemon.setIV_Sta(item.ivSta);
        //
        dbpokemon.setHp(item.hp);
        dbpokemon.setMaxHP(item.maxHp);
        //
        dbpokemon.setCreateTime(item.createTime);
        //
        dbpokemon.setType1(item.type1.ordinal());
        dbpokemon.setType2(item.type2.ordinal());
        dbpokemon.setMove1(item.move1.getNumber());
        dbpokemon.setMove2(item.move2.getNumber());

        return dbpokemon;
    }
    public static List<DBPokemon> ItemsToDBPokemons(List<PokemonItem> items)
    {
        List<DBPokemon> dbpokemons = new ArrayList<>();
        for(int i=0;i<items.size();i++)
        {
            dbpokemons.add(ItemToDBPokemon(items.get(i)));
        }
        return dbpokemons;
    }
}
