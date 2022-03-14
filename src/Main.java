import Arena.Antrenor;
import Arena.Aventura;
import Fisiere.CitireFisier;
import Fisiere.ScriereFisier;
import Pokemoni.Picachu;
import Pokemoni.PokeFactory;
import Pokemoni.Pokemon;

import javax.sql.PooledConnection;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    /* Testeaza executia fisierului test cu numarul dat parametru */
    public static void testeaza(int nrTest) throws IOException {
        CitireFisier citireFisier = new CitireFisier();
        ScriereFisier scriereFisier = new ScriereFisier();
        Aventura aventura = Aventura.Instanta();

        Antrenor antrenor1 = citireFisier.citesteAntrenor("src/tests/test" + nrTest + ".in", 1);
        Antrenor antrenor2 = citireFisier.citesteAntrenor("src/tests/test" + nrTest + ".in", 2);
        String castigator = aventura.incepeAventura(antrenor1, antrenor2);

        StringBuilder log = aventura.getLog();

        if(castigator == "Egalitate")
            log.append("###############  Egalitate  ###############");
        else
            log.append("##  " + castigator + " a iesit castigator din arena  ##\n");

        System.out.print(aventura.getLog());
        scriereFisier.scriereLog("src/tests/test" + nrTest + ".out", log);
    }

    public static void main(String[] args) throws IOException {
        Main.testeaza(1);
//        Main.testeaza(2);
//        Main.testeaza(3);
//        Main.testeaza(4);
//        Main.testeaza(5);
//        Main.testeaza(6);
//        Main.testeaza(7);
//        Main.testeaza(8);
//        Main.testeaza(9);
//        Main.testeaza(10);

    }
}
