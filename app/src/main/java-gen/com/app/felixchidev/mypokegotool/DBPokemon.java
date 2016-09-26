package com.app.felixchidev.mypokegotool;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "DBPOKEMON".
 */
public class DBPokemon {

    private Long id;
    private Integer pokemon_id;
    private Integer cp;
    private String name;
    private String nickname;
    private Double iv;
    private Integer IV_Atk;
    private Integer IV_Def;
    private Integer IV_Sta;
    private Long createTime;
    private Integer maxHP;
    private Integer hp;
    private Integer move1;
    private Integer move2;
    private Integer type1;
    private Integer type2;

    public DBPokemon() {
    }

    public DBPokemon(Long id) {
        this.id = id;
    }

    public DBPokemon(Long id, Integer pokemon_id, Integer cp, String name, String nickname, Double iv, Integer IV_Atk, Integer IV_Def, Integer IV_Sta, Long createTime, Integer maxHP, Integer hp, Integer move1, Integer move2, Integer type1, Integer type2) {
        this.id = id;
        this.pokemon_id = pokemon_id;
        this.cp = cp;
        this.name = name;
        this.nickname = nickname;
        this.iv = iv;
        this.IV_Atk = IV_Atk;
        this.IV_Def = IV_Def;
        this.IV_Sta = IV_Sta;
        this.createTime = createTime;
        this.maxHP = maxHP;
        this.hp = hp;
        this.move1 = move1;
        this.move2 = move2;
        this.type1 = type1;
        this.type2 = type2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPokemon_id() {
        return pokemon_id;
    }

    public void setPokemon_id(Integer pokemon_id) {
        this.pokemon_id = pokemon_id;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Double getIv() {
        return iv;
    }

    public void setIv(Double iv) {
        this.iv = iv;
    }

    public Integer getIV_Atk() {
        return IV_Atk;
    }

    public void setIV_Atk(Integer IV_Atk) {
        this.IV_Atk = IV_Atk;
    }

    public Integer getIV_Def() {
        return IV_Def;
    }

    public void setIV_Def(Integer IV_Def) {
        this.IV_Def = IV_Def;
    }

    public Integer getIV_Sta() {
        return IV_Sta;
    }

    public void setIV_Sta(Integer IV_Sta) {
        this.IV_Sta = IV_Sta;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(Integer maxHP) {
        this.maxHP = maxHP;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getMove1() {
        return move1;
    }

    public void setMove1(Integer move1) {
        this.move1 = move1;
    }

    public Integer getMove2() {
        return move2;
    }

    public void setMove2(Integer move2) {
        this.move2 = move2;
    }

    public Integer getType1() {
        return type1;
    }

    public void setType1(Integer type1) {
        this.type1 = type1;
    }

    public Integer getType2() {
        return type2;
    }

    public void setType2(Integer type2) {
        this.type2 = type2;
    }

}
