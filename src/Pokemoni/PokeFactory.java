package Pokemoni;

/*
Implementare design pattern factory
*/
public class PokeFactory {
    private static PokeFactory pokeFactory;

    /* Folosesc design pattern-ul Singleton pentru a avea o singura instanta de PokeFactory */
    public static PokeFactory Instanta(){
        if(pokeFactory == null)
            pokeFactory = new PokeFactory();
        return pokeFactory;
    }

    /* Returneaza pokemonul instantiat cu caracteristicile sale in dependenta de numele pokemonului */
    public Pokemon creazaPokemon(String nume){
        switch(nume){
            case "Neutrel1":
                return new Neutrel1(nume, 10, 3, 0, 1, 1,
                        null, null);
            case "Neutrel2":
                return new Neutrel2(nume, 20, 4, 0, 1, 1,
                        null, null);
            case "Pikachu":
                return new Picachu(nume, 35, 0, 4, 2, 3,
                        new Abilitate(6, false, false, 4),
                        new Abilitate(4, true, true, 5));
            case "Bulbasaur":
                return new Bulbasaur(nume, 42, 0, 5, 3, 1,
                        new Abilitate(6, false, false, 4),
                        new Abilitate(5, false, false, 3));
            case "Charmander":
                return new Charmander(nume, 50, 4, 0, 3, 2,
                        new Abilitate(4, true, false, 4),
                        new Abilitate(7, false, false, 6));
            case "Squirtle":
                return new Squirtle(nume, 60, 0, 3, 5, 5,
                        new Abilitate(4, false, false, 3),
                        new Abilitate(2, true, false, 2));
            case "Snorlax":
                return new Snorlax(nume, 62, 3, 0, 6, 4,
                        new Abilitate(4, true, false, 5),
                        new Abilitate(0, false, true, 5));
            case "Vulpix":
                return new Vulpix(nume, 36, 5, 0, 2, 4,
                        new Abilitate(8, true, false, 6),
                        new Abilitate(2, false, true, 7));
            case "Eevee":
                return new Eevee(nume, 39, 0, 4, 3, 3,
                        new Abilitate(5, false, false, 3),
                        new Abilitate(3, true, false, 3));
            case "Jigglypuff":
                return new Jigglypuff(nume, 34, 4, 0, 2, 3,
                        new Abilitate(4, true, false, 4),
                        new Abilitate(3, true, false, 4));
            case "Meowth":
                return new Meowth(nume, 41, 3, 0, 4, 2,
                        new Abilitate(5, false, true, 4),
                        new Abilitate(1, false, true, 3));
            case "Psyduck":
                return new Psyduck(nume, 43, 3, 0, 3, 3,
                        new Abilitate(2, false, false, 4),
                        new Abilitate(2, true, false, 5));

        }
        throw new IllegalArgumentException("Caracteristicile pokemonului " + nume + " nu sunt cunoscute.");
    }
}
