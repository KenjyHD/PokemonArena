package Arena;

import Pokemoni.Pokemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lupta implements Runnable{
    private final Pokemon pokemon1;
    private final Pokemon pokemon2;
    private int castigator;
    private final StringBuilder log;
    private final Lock lock = new ReentrantLock();

    public Lupta(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.log = new StringBuilder();
    }

    public StringBuilder getLog() {
        return log;
    }

    public int getCastigator() {
        return castigator;
    }

    /*
    Scrie in log statusul luptei inainte de atac(starea in care se afla pokemonii)
    */
    public void logStatusRunda(int i){
        log.append("\nRunda ").append(i).append("\n");
        log.append(pokemon1.getNume()).append(" HP [").append(pokemon1.getCurentHP()).append("]\n");
        log.append("CDs: Abilitate1 [").append(pokemon1.getAbilitate1().getCooldownStatus())
                .append("] / Abilitate2 [").append(pokemon1.getAbilitate2().getCooldownStatus()).append("]\n");
        if(pokemon1.dodgeStatus || pokemon1.stunStatus) {
            if (pokemon1.dodgeStatus) log.append("Dodged ");
            if (pokemon1.stunStatus) log.append("Stunned");
            log.append("\n");
        }
        log.append("\n");

        log.append(pokemon2.getNume()).append(" HP [").append(pokemon2.getCurentHP()).append("]\n");
        if(pokemon2.areAbilitati())
            log.append("CDs: Abilitate1 [" + pokemon2.getAbilitate1().getCooldownStatus() + "] / Abilitate2 [").
                    append(pokemon2.getAbilitate2().getCooldownStatus() + "]\n");
        if(pokemon2.dodgeStatus || pokemon2.stunStatus) {
            if (pokemon2.dodgeStatus) log.append("Dodged ");
            if (pokemon2.stunStatus) log.append("Stunned");
            log.append("\n");
        }
        log.append("\n");
    }

    /*
    Implementare lupte intre pokemoni prin thread-uri ceea ce permite executia simultana am mai multor lupte
    */
    @Override
    public void run(){
        lock.lock();
        log.append("\n------ Se lupta ").append(pokemon1.getNume()).append(" cu ").append(pokemon2.getNume())
                .append(" ------\n");
        log.append(pokemon1.caracteristici()).append("\n").append(pokemon2.caracteristici()).append("\n");

        if(pokemon1.getObiecte() != null) pokemon1.echipeazaPokemon();
        if(pokemon2.getObiecte() != null) pokemon2.echipeazaPokemon();

        log.append("--- Caracteristici cu obiecte echipate ---\n");
        log.append(pokemon1.caracteristici()).append("\n").append(pokemon2.caracteristici()).append("\n");
        int i = 0;
        int damageCauzatDePokemon1 = 0, damageCauzatDePokemon2 = 0;

        while (pokemon1.getCurentHP() > 0 && pokemon2.getCurentHP() > 0){
            i++;
            if(pokemon1.areAbilitati()) {
                if (pokemon1.getAbilitate1().getCooldownStatus() != 0)
                    pokemon1.getAbilitate1().setCooldownStatus(pokemon1.getAbilitate1().getCooldownStatus() - 1);
                if (pokemon1.getAbilitate2().getCooldownStatus() != 0)
                    pokemon1.getAbilitate2().setCooldownStatus(pokemon1.getAbilitate2().getCooldownStatus() - 1);
            }
            if(pokemon2.areAbilitati()) {
                if (pokemon2.getAbilitate1().getCooldownStatus() != 0)
                    pokemon2.getAbilitate1().setCooldownStatus(pokemon2.getAbilitate1().getCooldownStatus() - 1);
                if (pokemon2.getAbilitate2().getCooldownStatus() != 0)
                    pokemon2.getAbilitate2().setCooldownStatus(pokemon2.getAbilitate2().getCooldownStatus() - 1);
            }

            logStatusRunda(i);

            boolean isStunnedPokemon1 = pokemon1.stunStatus;
            boolean isStunnedPokemon2 = pokemon2.stunStatus;

            pokemon1.setStunStatus(false);
            pokemon2.setStunStatus(false);
            pokemon1.setDodgeStatus(false);
            pokemon2.setDodgeStatus(false);

            ExecutorService executor = Executors.newFixedThreadPool(2);
            MiscarePokemon miscarePokemon1 = new MiscarePokemon(pokemon1, pokemon2);
            MiscarePokemon miscarePokemon2 = new MiscarePokemon(pokemon2, pokemon1);

            if(!isStunnedPokemon1) executor.execute(miscarePokemon1);
            else log.append(pokemon1.getNume() + " este stunned\n");

            if(!isStunnedPokemon2) executor.execute(miscarePokemon2);
            else log.append(pokemon2.getNume() + " este stunned\n");
            executor.shutdown();
            try {
                executor.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(miscarePokemon1.getLog() != null && miscarePokemon2.getLog() != null) {
                log.append(miscarePokemon1.getLog());
                log.append(miscarePokemon2.getLog());
            }
            damageCauzatDePokemon1 = miscarePokemon1.getDamageCauzat();
            damageCauzatDePokemon2 = miscarePokemon2.getDamageCauzat();

            if(!pokemon1.dodgeStatus && !isStunnedPokemon2)
                pokemon1.setCurentHP(pokemon1.getCurentHP() - damageCauzatDePokemon2);
            if(!pokemon2.dodgeStatus && !isStunnedPokemon1)
                pokemon2.setCurentHP(pokemon2.getCurentHP() - damageCauzatDePokemon1);
        }

        if(pokemon1.getObiecte() != null) pokemon1.deechipeazaPokemon();
        if(pokemon2.getObiecte() != null) pokemon2.deechipeazaPokemon();

        if(pokemon1.getCurentHP() <= 0 && pokemon2.getCurentHP() <= 0){
            castigator = 0;
            log.append("\n---------> Egalitate <---------\n");
        }
        else if(pokemon1.getCurentHP() > 0) {
            castigator = 1;
            log.append("\n---------> A castigat ").append(pokemon1.getNume()).append(" <---------\n")
                    .append(pokemon1.caracteristici()).append("\n\t\t\t\t  V\n");
            pokemon1.bonusAtribute();
            log.append(pokemon1.caracteristici()).append("\n\n");
        }
        else if(pokemon2.getCurentHP() > 0) {
            castigator = 2;
            log.append("\n---------> A castigat ").append(pokemon2.getNume()).append(" <---------\n")
                    .append(pokemon2.caracteristici()).append("\n\t\t\t\t  V\n");
            pokemon2.bonusAtribute();
            log.append(pokemon2.caracteristici()).append("\n\n");
        }

        pokemon1.setCurentHP(pokemon1.getHp());
        pokemon2.setCurentHP(pokemon2.getHp());

        pokemon1.setStunStatus(false);
        pokemon1.setDodgeStatus(false);
        pokemon2.setStunStatus(false);
        pokemon2.setStunStatus(false);

        pokemon1.getAbilitate1().setCooldownStatus(0);
        pokemon1.getAbilitate2().setCooldownStatus(0);

        if(pokemon2.getAbilitate1() != null && pokemon2.getAbilitate2() != null) {
            pokemon2.getAbilitate1().setCooldownStatus(0);
            pokemon2.getAbilitate2().setCooldownStatus(0);
        }

        lock.unlock();
    }
}
