package Arena;

import Pokemoni.Pokemon;

public class Antrenor {
    private final String nume;
    private int virsta;
    private final Pokemon[] pokemon;

    public Antrenor(String nume, int virsta, Pokemon[] pokemon){
        this.nume = nume;
        this.virsta = virsta;
        this.pokemon = pokemon;
    }

    public String getNume() {
        return nume;
    }

    public Pokemon[] getPokemon() {
        return pokemon;
    }

    /*
    Cauta si returneaza cel mai puternic dupa atribute pokemon din lista de pokemoni
    */
    public Pokemon celMaiPuternicPokemon(){
        Pokemon celMaiPuternicPokemon = this.pokemon[0];
        for(Pokemon pokemon : this.pokemon){
            if(pokemon.coeficientPutere() > celMaiPuternicPokemon.coeficientPutere())
                celMaiPuternicPokemon = pokemon;
            if(pokemon.coeficientPutere() == celMaiPuternicPokemon.coeficientPutere())
                if(pokemon.getNume().compareTo(celMaiPuternicPokemon.getNume()) < 0)
                    celMaiPuternicPokemon = pokemon;
        }
        return celMaiPuternicPokemon;
    }
}
