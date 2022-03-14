package Arena;

import Pokemoni.PokeFactory;
import Pokemoni.Pokemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Aventura {
    private static Aventura aventura;
    private StringBuilder log;

    /*
    Voi avea o singura instanta de aventura
    Implementare design pattern singleton
    */
    public static Aventura Instanta(){
        if(aventura == null)
            aventura = new Aventura();
        return aventura;
    }

    public StringBuilder getLog() {
        return log;
    }

    /*
    Executa prin thread-uri 2 lupte ce vor avea loc simultan intre pokemonii specificati pentru aveniment tip
    neutrel1 sau neutrel2
    */
    public void executaEvenimentNeutrel(Pokemon pokemon1, Pokemon pokemonAdversar1,
                                 Pokemon pokemon2, Pokemon pokemonAdversar2){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Lupta lupta1 = new Lupta(pokemon1, pokemonAdversar1);
        Lupta lupta2 = new Lupta(pokemon2, pokemonAdversar2);
        executor.execute(lupta1);
        executor.execute(lupta2);
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.append(lupta1.getLog());
        log.append(lupta2.getLog());
    }

    /*
    Executa mai intai 3 lupte ce vor avea loc simultan intre pokemonii antrenorilor
    Dupa afla cel mai puternic pokemon a fiecarui antrenor si executa lupta finala
    */
    public String executaEvenimentFinal(Antrenor antrenor1, Antrenor antrenor2){
        ExecutorService executor = Executors.newFixedThreadPool(3);
        log.append("############################################\n");
        log.append("###########  Luptele semifinale  ###########\n");
        log.append("############################################\n");
        Lupta lupta1 = new Lupta(antrenor1.getPokemon()[0], antrenor2.getPokemon()[0]);
        Lupta lupta2 = new Lupta(antrenor1.getPokemon()[1], antrenor2.getPokemon()[1]);
        Lupta lupta3 = new Lupta(antrenor1.getPokemon()[2], antrenor2.getPokemon()[2]);
        executor.execute(lupta1);
        executor.execute(lupta2);
        executor.execute(lupta3);
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.append(lupta1.getLog());
        log.append(lupta2.getLog());
        log.append(lupta3.getLog());

        executor = Executors.newFixedThreadPool(1);
        log.append("############################################\n");
        log.append("##############  Lupta finala  ##############\n");
        log.append("############################################\n");
        Pokemon pokemonFinal1 = antrenor1.celMaiPuternicPokemon();
        Pokemon pokemonFinal2 = antrenor2.celMaiPuternicPokemon();
        Lupta luptaFinal = new Lupta(pokemonFinal1, pokemonFinal2);
        executor.execute(luptaFinal);
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.append(luptaFinal.getLog());
        String castigator = null;
        if(luptaFinal.getCastigator() == 0)
            castigator = "Egalitate";
        if(luptaFinal.getCastigator() == 1)
            castigator = antrenor1.getNume();
        if(luptaFinal.getCastigator() == 2)
            castigator = antrenor2.getNume();
        return castigator;
    }

    /*
    Incepe aventura si executa evenimentele generate aleator pana nu nimereste duelul intre antrenori
    Returneaza numele antrenorului castigator
    */
    public String incepeAventura(Antrenor antrenor1, Antrenor antrenor2){
        log = new StringBuilder();
        int decideEveniment = -1;
        String castigator = null;
        PokeFactory pokeFactory = PokeFactory.Instanta();
        log.append("****** In arena intra " + antrenor1.getNume() + " si " + antrenor2.getNume() + " ******\n");
        int i = 0;
        while (decideEveniment != 2) {
            i++;
            decideEveniment = (int) (Math.random() * 3);
            int decidePokemon = (int) (Math.random() * 3);
            if(decideEveniment != 2) {
                log.append("###########################################\n");
                log.append("###############  Luptele " + i + "  ###############\n");
                log.append("###########################################\n");
            }

            if(decideEveniment == 0){
                Pokemon neutrelNr1 = pokeFactory.creazaPokemon("Neutrel1");
                Pokemon neutrelNr2 = pokeFactory.creazaPokemon("Neutrel1");
                executaEvenimentNeutrel(antrenor1.getPokemon()[decidePokemon], neutrelNr1,
                        antrenor2.getPokemon()[decidePokemon], neutrelNr2);
            }

            if(decideEveniment == 1){
                Pokemon neutrelNr1 = pokeFactory.creazaPokemon("Neutrel2");
                Pokemon neutrelNr2 = pokeFactory.creazaPokemon("Neutrel2");
                executaEvenimentNeutrel(antrenor1.getPokemon()[decidePokemon], neutrelNr1,
                        antrenor2.getPokemon()[decidePokemon], neutrelNr2);
            }

            if(decideEveniment == 2){
                castigator = executaEvenimentFinal(antrenor1, antrenor2);
            }
        }

        return castigator;
    }
}
