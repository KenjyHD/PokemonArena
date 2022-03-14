package Pokemoni;

import Obiecte.Obiect;

public abstract class Pokemon{
    private String nume;
    private int hp;
    private int curentHP;
    private int atac;
    private int specialAtac;
    private int defense;
    private int specialDefense;
    public boolean dodgeStatus;
    public boolean stunStatus;
    private Abilitate abilitate1;
    private Abilitate abilitate2;
    Obiect[] obiecte;

    public Pokemon(String nume, int hp, int atac, int specialAtac, int defense, int specialDefense,
                   Abilitate abilitate1, Abilitate abilitate2) {
        this.nume = nume;
        this.hp = hp;
        this.curentHP = hp;
        this.atac = atac;
        this.specialAtac = specialAtac;
        this.defense = defense;
        this.specialDefense = specialDefense;
        this.stunStatus = false;
        this.dodgeStatus = false;
        this.abilitate1 = abilitate1;
        this.abilitate2 = abilitate2;
    }

    public String getNume() {
        return nume;
    }

    public int getHp() {
        return hp;
    }

    public int getCurentHP() {
        return curentHP;
    }

    public int getAtac() {
        return atac;
    }

    public int getSpecialAtac() {
        return specialAtac;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public Abilitate getAbilitate1() {
        return abilitate1;
    }

    public Abilitate getAbilitate2() {
        return abilitate2;
    }

    /*
    Ofera bonusul de atribute pokemonului castigator
    */
    public void bonusAtribute(){
        this.hp++;
        if (this.atac != 0) this.atac++;
        if (this.specialAtac != 0) this.specialAtac++;
        this.defense++;
        this.specialDefense++;
    }

    public void setStunStatus(boolean stunStatus) {
        this.stunStatus = stunStatus;
    }

    public void setDodgeStatus(boolean dodgeStatus) {
        this.dodgeStatus = dodgeStatus;
    }

    public Obiect[] getObiecte() {
        return obiecte;
    }

    public void setObiecte(Obiect[] obiecte) {
        this.obiecte = obiecte;
    }

    /*
    Returneaza caracteristica pokemonului
    */
    public String caracteristici() {
        String caracteristica = "";
        caracteristica = nume + " {" +
                "HP[" + hp;
        if(atac != 0) caracteristica += "], AN[" + atac;
        if(specialAtac != 0) caracteristica += "], AS[" + specialAtac;
        caracteristica += "], Def[" + defense +
                "], SDef[" + specialDefense +
                "]}";
        return caracteristica;
    }

    /*
    Calculeaza cite abilitati a pokemonului sunt in cooldown
    */
    public int abilitatiInCD(){
        if (abilitate1.getCooldownStatus() != 0 && abilitate2.getCooldownStatus() != 0) return 2;
        if(abilitate1.getCooldownStatus() != 0 || abilitate2.getCooldownStatus() != 0) return 1;
        return 0;
    }

    /*
    Verifica daca pokemonul are abilitati
    */
    public boolean areAbilitati(){
        return abilitate1 != null && abilitate2 != null;
    }

    public void setCurentHP(int curentHP) {
        this.curentHP = curentHP;
    }

    /*
    Calculeaza damage-ul ce trebuie cauzat prin atacuri tip special sau normal
     */
    public int folosesteAtac(Pokemon pokemon){
        int damageRedus = atac - pokemon.getDefense();
        int specialDamageRedus = specialAtac - pokemon.getSpecialDefense();

        if(damageRedus < 0) damageRedus = 0;
        if(specialDamageRedus < 0) specialDamageRedus = 0;

        return damageRedus + specialDamageRedus;
    }

    /*
    Seteaza statusurile respective a pokemonilor atacant si atacat(dodge si stun), porneste cooldown-ul si returneaza
    damage-ul ce trebuie cauzat
    */
    public int folosesteAbilitate(int nrAbilitate, Pokemon pokemon){
        int damage = 0;
        if (nrAbilitate == 1){
            damage = abilitate1.getDamage();
            dodgeStatus = abilitate1.isDodge();
            pokemon.setStunStatus(abilitate1.isStun());
            abilitate1.setCooldownStatus(abilitate1.getCooldown() + 1);
        }
        if (nrAbilitate == 2){
            damage = abilitate2.getDamage();
            dodgeStatus = abilitate2.isDodge();
            pokemon.setStunStatus(abilitate2.isStun());
            abilitate2.setCooldownStatus(abilitate2.getCooldown() + 1);
        }
        return damage;
    }

    /*
    Trecerea pokemonului din stare pasnica in stare de lupta
    Implementarea design patternu-lui comportamental state
    */
    public void echipeazaPokemon(){
        for (Obiect obiect : obiecte){
            hp += obiect.getHp();
            if(atac != 0) atac += obiect.getAtac();
            if(specialAtac != 0) specialAtac += obiect.getSpecialAtac();
            defense += obiect.getDefense();
            specialDefense += obiect.getSpecialDefense();
        }
    }

    /*
    Trecerea pokemonului din starea de lupta in stare pasnica
    Implementarea design patternu-lui comportamental state
    */
    public void deechipeazaPokemon(){
        for (Obiect obiect : obiecte){
            hp -= obiect.getHp();
            if(atac != 0) atac -= obiect.getAtac();
            if(specialAtac != 0) specialAtac -= obiect.getSpecialAtac();
            defense -= obiect.getDefense();
            specialDefense -= obiect.getSpecialDefense();
        }
    }

    public int coeficientPutere(){
        return hp + atac + specialAtac + defense + specialDefense;
    }
}