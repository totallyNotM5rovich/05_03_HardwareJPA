package hr.java.spring.boot.HardwareJPA.domain;

public enum Tip {
    CPU("Procesor",1), GPU("Graficka kartica",2), MBO("Maticna ploca",3), RAM("Radna memorija",4), STORAGE("Pohrana podataka",5), OTHER("Ostalo",6);

    private final String naziv;
    private final int id;

    Tip(String naziv, int id) {
        this.naziv = naziv;
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getId() {
        return id;
    }

    public static Tip getTip(String naziv) {
        for (Tip value : Tip.values()) {
            if (naziv.equalsIgnoreCase(value.getNaziv())) return value;
        }
        return Tip.OTHER;
    }

    public static Tip getTip(int id) {
        for (Tip value : Tip.values()) {
            if (id == value.getId()) return value;
        }
        return Tip.OTHER;
    }
}
