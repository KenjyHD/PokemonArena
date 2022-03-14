package Arena;

import Pokemoni.Pokemon;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MiscarePokemon implements Runnable {
    private int damageCauzat;
    private final Pokemon pokemonAtacant;
    private final Pokemon pokemonAtacat;
    private final StringBuilder log = new StringBuilder();
    private final Lock lock = new ReentrantLock();

    public StringBuilder getLog() {
        return log;
    }

    public MiscarePokemon(Pokemon pokemonAtacant, Pokemon pokemonAtacat) {
        this.pokemonAtacant = pokemonAtacant;
        this.pokemonAtacat = pokemonAtacat;
    }

    public int getDamageCauzat() {
        return damageCauzat;
    }

    /*
    Implementare atac pokemon prin thread-uri ceea ce permite atacul simultan a 2 pokemoni
    */
    @Override
    public void run() {
        lock.lock();
        int decideTipAtac = (int)(Math.random() * 3);

        if (decideTipAtac == 0 || !pokemonAtacant.areAbilitati() || pokemonAtacant.abilitatiInCD() == 2) {
            damageCauzat = pokemonAtacant.folosesteAtac(pokemonAtacat);
            if(pokemonAtacant.getAtac() == 0) log.append(pokemonAtacant.getNume()).append(" foloseste atac special");
            else log.append(pokemonAtacant.getNume()).append(" foloseste atac normal");
        }
        else if (pokemonAtacant.areAbilitati() && pokemonAtacant.getAbilitate1().getCooldownStatus() > 0 &&
                decideTipAtac == 1) {
            damageCauzat = pokemonAtacant.folosesteAbilitate(2, pokemonAtacat);
            log.append(pokemonAtacant.getNume()).append(" foloseste abilitate2");
        }
        else if (pokemonAtacant.areAbilitati() && pokemonAtacant.getAbilitate2().getCooldownStatus() > 0 &&
                decideTipAtac == 2) {
            damageCauzat = pokemonAtacant.folosesteAbilitate(1, pokemonAtacat);
            log.append(pokemonAtacant.getNume()).append(" foloseste abilitate1");
        }
        else {
            damageCauzat = pokemonAtacant.folosesteAbilitate(decideTipAtac, pokemonAtacat);
            log.append(pokemonAtacant.getNume()).append(" foloseste abilitate").append(decideTipAtac);
        }
        log.append(" -> " + damageCauzat + "\n");
        lock.unlock();
    }
}
