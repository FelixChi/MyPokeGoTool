package com.app.felixchidev.mypokegotool;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.app.felixchidev.mypokegotool.DBPokemon;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DBPOKEMON".
*/
public class DBPokemonDao extends AbstractDao<DBPokemon, Long> {

    public static final String TABLENAME = "DBPOKEMON";

    /**
     * Properties of entity DBPokemon.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Pokemon_id = new Property(1, Integer.class, "pokemon_id", false, "POKEMON_ID");
        public final static Property Cp = new Property(2, Integer.class, "cp", false, "CP");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property Nickname = new Property(4, String.class, "nickname", false, "NICKNAME");
        public final static Property Iv = new Property(5, Double.class, "iv", false, "IV");
        public final static Property IV_Atk = new Property(6, Integer.class, "IV_Atk", false, "IV__ATK");
        public final static Property IV_Def = new Property(7, Integer.class, "IV_Def", false, "IV__DEF");
        public final static Property IV_Sta = new Property(8, Integer.class, "IV_Sta", false, "IV__STA");
        public final static Property CreateTime = new Property(9, Long.class, "createTime", false, "CREATE_TIME");
        public final static Property MaxHP = new Property(10, Integer.class, "maxHP", false, "MAX_HP");
        public final static Property Hp = new Property(11, Integer.class, "hp", false, "HP");
        public final static Property Move1 = new Property(12, Integer.class, "move1", false, "MOVE1");
        public final static Property Move2 = new Property(13, Integer.class, "move2", false, "MOVE2");
        public final static Property Type1 = new Property(14, Integer.class, "type1", false, "TYPE1");
        public final static Property Type2 = new Property(15, Integer.class, "type2", false, "TYPE2");
    };


    public DBPokemonDao(DaoConfig config) {
        super(config);
    }
    
    public DBPokemonDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DBPOKEMON\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"POKEMON_ID\" INTEGER," + // 1: pokemon_id
                "\"CP\" INTEGER," + // 2: cp
                "\"NAME\" TEXT," + // 3: name
                "\"NICKNAME\" TEXT," + // 4: nickname
                "\"IV\" REAL," + // 5: iv
                "\"IV__ATK\" INTEGER," + // 6: IV_Atk
                "\"IV__DEF\" INTEGER," + // 7: IV_Def
                "\"IV__STA\" INTEGER," + // 8: IV_Sta
                "\"CREATE_TIME\" INTEGER," + // 9: createTime
                "\"MAX_HP\" INTEGER," + // 10: maxHP
                "\"HP\" INTEGER," + // 11: hp
                "\"MOVE1\" INTEGER," + // 12: move1
                "\"MOVE2\" INTEGER," + // 13: move2
                "\"TYPE1\" INTEGER," + // 14: type1
                "\"TYPE2\" INTEGER);"); // 15: type2
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DBPOKEMON\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DBPokemon entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer pokemon_id = entity.getPokemon_id();
        if (pokemon_id != null) {
            stmt.bindLong(2, pokemon_id);
        }
 
        Integer cp = entity.getCp();
        if (cp != null) {
            stmt.bindLong(3, cp);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(5, nickname);
        }
 
        Double iv = entity.getIv();
        if (iv != null) {
            stmt.bindDouble(6, iv);
        }
 
        Integer IV_Atk = entity.getIV_Atk();
        if (IV_Atk != null) {
            stmt.bindLong(7, IV_Atk);
        }
 
        Integer IV_Def = entity.getIV_Def();
        if (IV_Def != null) {
            stmt.bindLong(8, IV_Def);
        }
 
        Integer IV_Sta = entity.getIV_Sta();
        if (IV_Sta != null) {
            stmt.bindLong(9, IV_Sta);
        }
 
        Long createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindLong(10, createTime);
        }
 
        Integer maxHP = entity.getMaxHP();
        if (maxHP != null) {
            stmt.bindLong(11, maxHP);
        }
 
        Integer hp = entity.getHp();
        if (hp != null) {
            stmt.bindLong(12, hp);
        }
 
        Integer move1 = entity.getMove1();
        if (move1 != null) {
            stmt.bindLong(13, move1);
        }
 
        Integer move2 = entity.getMove2();
        if (move2 != null) {
            stmt.bindLong(14, move2);
        }
 
        Integer type1 = entity.getType1();
        if (type1 != null) {
            stmt.bindLong(15, type1);
        }
 
        Integer type2 = entity.getType2();
        if (type2 != null) {
            stmt.bindLong(16, type2);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DBPokemon readEntity(Cursor cursor, int offset) {
        DBPokemon entity = new DBPokemon( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // pokemon_id
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // cp
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // nickname
            cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5), // iv
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // IV_Atk
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // IV_Def
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // IV_Sta
            cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9), // createTime
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // maxHP
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // hp
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // move1
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13), // move2
            cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14), // type1
            cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15) // type2
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DBPokemon entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPokemon_id(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setCp(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNickname(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIv(cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5));
        entity.setIV_Atk(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setIV_Def(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setIV_Sta(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setCreateTime(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setMaxHP(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setHp(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setMove1(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setMove2(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
        entity.setType1(cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14));
        entity.setType2(cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DBPokemon entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DBPokemon entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}