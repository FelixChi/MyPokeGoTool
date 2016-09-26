package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Index;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class GFAOGenerator {

    public static void main(String[] args) throws Exception {
        //
        Schema schema = new Schema(1, "com.app.felixchidev.mypokegotool");
        //
        addData(schema);

        new DaoGenerator().generateAll(schema, "app/src/main/java-gen");
    }
    //
    private static void addData(Schema schema)
    {
        //
        //pokemon dao
        Entity pokemon = schema.addEntity("DBPokemon");
        pokemon.addIdProperty().primaryKey();
        //
        pokemon.addIntProperty("pokemon_id");
        pokemon.addIntProperty("cp");
        pokemon.addStringProperty("name");
        pokemon.addStringProperty("nickname");
        pokemon.addDoubleProperty("iv");
        pokemon.addIntProperty("IV_Atk");
        pokemon.addIntProperty("IV_Def");
        pokemon.addIntProperty("IV_Sta");
        pokemon.addLongProperty("createTime");
        //
        pokemon.addIntProperty("maxHP");
        pokemon.addIntProperty("hp");
        //
        pokemon.addIntProperty("move1");
        pokemon.addIntProperty("move2");
        pokemon.addIntProperty("type1");
        pokemon.addIntProperty("type2");
    }
}
