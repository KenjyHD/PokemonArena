package Obiecte;

public class Obiect {
    private final String nume;
    private int hp;
    private int atac;
    private int specialAtac;
    private int defense;
    private int specialDefense;

    public Obiect(ObiectBuilder builder) {
        this.nume = builder.nume;
        this.hp = builder.hp;
        this.atac = builder.atac;
        this.specialAtac = builder.specialAtac;
        this.defense = builder.defense;
        this.specialDefense = builder.specialDefense;
    }

    public Obiect(String nume) {
        this.nume = nume;
    }

    public String getName() {
        return nume;
    }

    public int getHp() {
        return hp;
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

    /*
    Implementare design pattern builder
    */
    public static class ObiectBuilder {
        private String nume;
        private int hp;
        private int atac;
        private int specialAtac;
        private int defense;
        private int specialDefense;

        public ObiectBuilder(String nume) {
            this.nume = nume;
        }

        public ObiectBuilder() {}

        public ObiectBuilder hp(int hp){
            this.hp = hp;
            return this;
        }

        public ObiectBuilder atac(int atac){
            this.atac = atac;
            return this;
        }

        public ObiectBuilder specialAtac(int specialAtac){
            this.specialAtac = specialAtac;
            return this;
        }

        public ObiectBuilder defense(int defense){
            this.defense = defense;
            return this;
        }

        public ObiectBuilder specialDefense(int specialDefense){
            this.specialDefense = specialDefense;
            return this;
        }

        public Obiect build(){
            return new Obiect(this);
        }

        /*
        Instantiaza obiectul cu atributele necesare in dependenta de numele obiectului
        */
        public Obiect creazaObiect(String nume){
            Obiect obiect;
            switch (nume) {
                case "Scut" -> {
                    obiect = new ObiectBuilder(nume)
                            .defense(2)
                            .specialDefense(2)
                            .build();
                    return obiect;
                }
                case "Vestă" -> {
                    obiect = new ObiectBuilder(nume)
                            .hp(10)
                            .build();
                    return obiect;
                }
                case "Săbiuță" -> {
                    obiect = new ObiectBuilder(nume)
                            .atac(3)
                            .build();
                    return obiect;
                }
                case "Baghetă Magică" -> {
                    obiect = new ObiectBuilder(nume)
                            .specialAtac(3)
                            .build();
                    return obiect;
                }
                case "Vitamine" -> {
                    obiect = new ObiectBuilder(nume)
                            .hp(2)
                            .atac(2)
                            .specialAtac(2)
                            .build();
                    return obiect;
                }
                case "Brad de Crăciun" -> {
                    obiect = new ObiectBuilder(nume)
                            .atac(3)
                            .defense(1)
                            .build();
                    return obiect;
                }
                case "Pelerină" -> {
                    obiect = new ObiectBuilder(nume)
                            .specialDefense(3)
                            .build();
                    return obiect;
                }
            }
            throw new IllegalArgumentException("Caracteristicile obiectului " + nume + " nu sunt cunoscute.");
        }

    }
}
