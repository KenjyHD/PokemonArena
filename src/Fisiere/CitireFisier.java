package Fisiere;

import Arena.Antrenor;
import Obiecte.Obiect;
import Pokemoni.PokeFactory;
import Pokemoni.Pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CitireFisier {
    /*
    Citeste din fisier si returneaza antrenorul cu numarul dat parametru din fisierul specificat
    */
    public Antrenor citesteAntrenor(String caleFisier, int nrAntrenor) throws IOException {
        FileReader file = new FileReader(caleFisier);
        Antrenor antrenor = null;
        try (BufferedReader br = new BufferedReader(file)) {
            String line;

            for(int i = 0; i < nrAntrenor; i++) br.readLine();

            if ((line = br.readLine()) != null) {
                String[] infoAntrenor = line.split("\\|");
                String[] pokemoniStr = infoAntrenor[2].split(";");
                Pokemon[] pokemoni = new Pokemon[3];

                String[] obiecteStrPokemon1 = infoAntrenor[3].split(";");
                String[] obiecteStrPokemon2 = infoAntrenor[4].split(";");
                String[] obiecteStrPokemon3 = infoAntrenor[5].split(";");

                Obiect.ObiectBuilder obiectBuilder = new Obiect.ObiectBuilder();
                Obiect[] obiectePokemon1 = new Obiect[obiecteStrPokemon1.length];
                Obiect[] obiectePokemon2 = new Obiect[obiecteStrPokemon2.length];
                Obiect[] obiectePokemon3 = new Obiect[obiecteStrPokemon3.length];

                obiectePokemon1[0] = obiectBuilder.creazaObiect(obiecteStrPokemon1[0]);
                obiectePokemon1[1] = obiectBuilder.creazaObiect(obiecteStrPokemon1[1]);
                obiectePokemon1[2] = obiectBuilder.creazaObiect(obiecteStrPokemon1[2]);

                obiectePokemon2[0] = obiectBuilder.creazaObiect(obiecteStrPokemon2[0]);
                obiectePokemon2[1] = obiectBuilder.creazaObiect(obiecteStrPokemon2[1]);
                obiectePokemon2[2] = obiectBuilder.creazaObiect(obiecteStrPokemon2[2]);

                obiectePokemon3[0] = obiectBuilder.creazaObiect(obiecteStrPokemon3[0]);
                obiectePokemon3[1] = obiectBuilder.creazaObiect(obiecteStrPokemon3[1]);
                obiectePokemon3[2] = obiectBuilder.creazaObiect(obiecteStrPokemon3[2]);

                PokeFactory pokeFactory = PokeFactory.Instanta();
                pokemoni[0] = pokeFactory.creazaPokemon(pokemoniStr[0]);
                pokemoni[1] = pokeFactory.creazaPokemon(pokemoniStr[1]);
                pokemoni[2] = pokeFactory.creazaPokemon(pokemoniStr[2]);

                pokemoni[0].setObiecte(obiectePokemon1);
                pokemoni[1].setObiecte(obiectePokemon2);
                pokemoni[2].setObiecte(obiectePokemon3);

                antrenor = new Antrenor(infoAntrenor[0], Integer.parseInt(infoAntrenor[1]) , pokemoni);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return antrenor;
    }
}
