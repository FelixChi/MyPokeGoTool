package com.app.felixchidev.mypokegotool.helper;

import com.pokegoapi.api.pokemon.PokemonType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by FelixChi on 23/9/2016.
 */
public class PKMTypeHelper {

    public static int GRASS = 1;
    public static int FIRE = 2;
    public static int WATER = 3;
    public static int BUG = 4;
    public static int ELECTRIC = 5;
    public static int POISON = 6;
    public static int FAIRY = 7;
    public static int NORMAL = 8;
    public static int PSYCHIC = 9;
    public static int FIGHTING = 10;
    public static int DRAGON = 11;
    public static int FLYING = 12;
    public static int ICE = 13;
    public static int ROCK = 14;
    public static int GROUND = 15;
    public static int GHOST = 16;
    public static int STEEL = 17;
    public static int DARK = 18;

    public static String GRASS_COLOR = "#4caf50";
    public static String FIRE_COLOR = "#ff5722";
    public static String WATER_COLOR = "#2196f3";
    public static String BUG_COLOR = "#009688";
    public static String ELECTRIC_COLOR = "#ffc107";
    public static String POISON_COLOR = "#83229B";
    public static String FAIRY_COLOR = "#e91e63";
    public static String NORMAL_COLOR = "#4a4a4a";
    public static String PSYCHIC_COLOR = "#9c27b0";
    public static String FIGHTING_COLOR = "#F48235";
    public static String DRAGON_COLOR = "#5c6bc0";
    public static String FLYING_COLOR = "#2962ff";
    public static String ICE_COLOR = "#81d4fa";
    public static String ROCK_COLOR = "#795548";
    public static String GROUND_COLOR = "#CEA835";
    public static String GHOST_COLOR = "#8A7DC6";
    public static String STEEL_COLOR = "#979799";
    public static String DARK_COLOR = "#263238";

    public static String getTypeColor(PokemonType type)
    {
        switch (type)
        {
            case GRASS:
                return GRASS_COLOR;
            case FIRE:
                return FIRE_COLOR;
            case WATER:
                return WATER_COLOR;
            case BUG:
                return BUG_COLOR;
            case ELECTRIC:
                return ELECTRIC_COLOR;
            case POISON:
                return POISON_COLOR;
            case FAIRY:
                return FAIRY_COLOR;
            case NORMAL:
                return NORMAL_COLOR;
            case PSYCHIC:
                return PSYCHIC_COLOR;
            case FIGHTING:
                return FIGHTING_COLOR;
            case DRAGON:
                return DRAGON_COLOR;
            case FLYING:
                return FLYING_COLOR;
            case ICE:
                return ICE_COLOR;
            case ROCK:
                return ROCK_COLOR;
            case GROUND:
                return GROUND_COLOR;
            case GHOST:
                return GHOST_COLOR;
            case STEEL:
                return STEEL_COLOR;
            case DARK:
                return DARK_COLOR;
        }
        return "#ffffff";
    }

    private static int[][] TypeChart = new int[][]
            {
//                  GRASS, FIRE, WATER, BUG, ELECTRIC, POISON, FAIRY, NORMAL, PSYCHIC, FIGHTING, DRAGON, FLYING, ICE, ROCK, GROUND, GHOST, STEEL, DARK
                    {-1, -1, 2, -1, 1, -1, 1, 1, 1, 1, -1, -1, 1, 2, 2, 1, -1, 1},//GRASS
                    {2, -1, -1, 2, 1, 1, 1, 1, 1, 1, -1, 1, 2, -1, 1, 1, 2, 1},//FIRE
                    {-1, 2, -1, 1, 1, 1, 1, 1, 1, 1, -1, 1, 1, 2, 2, 1, 1, 1},//WATER
                    {2, 1, 2, 1, 1, -1, 1, 1, 2, -1, 1, -1, 1, 1, 1, 1, 1, 2},//BUG
                    {-1, 1, 2, 1, -1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1},//ELECTRIC
                    {2, 1, 1, 1, 1, -1, 2, 1, 1, 1, 1, 1, 1, -1, -1, -1, 0, 1},//POISON
                    {1, -1, 1, 1, 1, -1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, -1, 2},//FAIRY
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, 1, 0, -1, 1},//NORMAL
                    {1, 1, 1, 1, 1, 2, 1, 1, -1, 2, 1, 1, 1, 1, 1, 1, -1, 0},//PSYCHIC
                    {1, 1, 1, -1, 1, -1, -1, 2, -1, 1, 1, -1, 2, 2, 1, 0, 2, 2},//FIGHTING
                    {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 2, 1, 1, 1, 1, 1, -1, 1},//DRAGON
                    {2, 1, 1, 2, -1, 1, 1, 1, 1, 2, 1, 1, 1, -1, 1, 1, -1, 1},//FLYING
                    {2, -1, -1, 1, 1, 1, 1, 1, 1, 1, 2, 2, -1, 1, 2, 1, -1, 1},//ICE
                    {1, 2, 1, 2, 1, 1, 1, 1, 1, -1, 1, 2, 2, 1, -1, 1, -1, 1},//ROCK
                    {-1, 2, 1, -1, 2, 2, 1, 1, 1, 1, 1, 0, 1, 2, 1, 1, 2, 1},//GROUND
                    {1, 1, 1, 1, 1, 1, 1, 0, 2, 1, 1, 1, 1, 1, 1, 2, 1, -1},//GHOST
                    {1, -1, -1, 1, -1, 1, 2, 1, 1, 1, 1, 1, 2, 2, 1, 1, -1, 1},//STEEL
                    {1, 1, 1, 1, 1, 1, -1, 1, 2, -1, 1, 1, 1, 1, 1, 2, 1, -1}//DARK
            };
    private static Map<PokemonType, List<PokemonType>> TypeWeakMap = null;
//    private static Map<PokemonType, List<PokemonType>> TypeStrengthMap = null;

    //not complete
    private static void initWeaknessMap()
    {
        //
        List<PokemonType> types = new ArrayList<>();
        //GRASS
        types.add(PokemonType.FIRE);
        types.add(PokemonType.BUG);
        types.add(PokemonType.FLYING);
        types.add(PokemonType.POISON);
        TypeWeakMap.put(PokemonType.GRASS, types);
        //FIRE
        types = new ArrayList<>();
        types.add(PokemonType.WATER);
        types.add(PokemonType.ROCK);
        types.add(PokemonType.GROUND);
        TypeWeakMap.put(PokemonType.FIRE, types);
        //WATER
        types = new ArrayList<>();
        types.add(PokemonType.GRASS);
        types.add(PokemonType.ELECTRIC);
        TypeWeakMap.put(PokemonType.WATER, types);
        //BUG
        types = new ArrayList<>();
        types.add(PokemonType.FIRE);
        types.add(PokemonType.FLYING);
        types.add(PokemonType.ROCK);
        TypeWeakMap.put(PokemonType.BUG, types);
        //ELECTRIC
        types = new ArrayList<>();
        types.add(PokemonType.GROUND);
        TypeWeakMap.put(PokemonType.ELECTRIC, types);
        //POISON
        types = new ArrayList<>();
        types.add(PokemonType.PSYCHIC);
        types.add(PokemonType.GROUND);
        TypeWeakMap.put(PokemonType.POISON, types);
        //FAIRY
        types = new ArrayList<>();
        types.add(PokemonType.POISON);
        types.add(PokemonType.STEEL);
        TypeWeakMap.put(PokemonType.FAIRY, types);
        //NORMAL
        types = new ArrayList<>();
        types.add(PokemonType.FIGHTING);
        types.add(PokemonType.GHOST);
        TypeWeakMap.put(PokemonType.NORMAL, types);
    }
    public static List<PokemonType> getTypeWeakness(PokemonType type)
    {
        List<PokemonType> types = new ArrayList<>();
        int idx = type.ordinal()-1;
        int t = TypeChart[0][17];
        for(int i=0;i<18;i++)
        {
            int factor = TypeChart[i][idx];
            //
            if(factor > 1)
                types.add(PokemonType.values()[i+1]);
        }

        //
        return types;
    }
    public static List<PokemonType> getTypeStrength(PokemonType type)
    {
        List<PokemonType> types = new ArrayList<>();
        int idx = type.ordinal()-1;
        for(int i=0;i<18;i++)
        {
            int factor = TypeChart[idx][i];
            //
            if(factor > 1)
                types.add(PokemonType.values()[i+1]);
        }

        //
        return types;
    }

}
