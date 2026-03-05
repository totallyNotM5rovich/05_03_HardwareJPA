package hr.java.spring.boot.HardwareJPA.domain;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;
    private String sifra;
    @ManyToOne
    @JoinColumn(name = "tipId")
    private Tip tip;
    private BigDecimal cijena;
    private int kolicina;

    public Hardware() {
    }

    public Hardware(Long id, String naziv, String sifra, Tip tip, BigDecimal cijena, int kolicina) {
        this.id = id;
        this.naziv = naziv;
        this.sifra = sifra;
        this.tip = tip;
        this.cijena = cijena;
        this.kolicina = kolicina;
    }

    public Hardware(String naziv, Tip tip, BigDecimal cijena, int kolicina) {
        this.naziv = naziv;
        this.tip = tip;
        this.cijena = cijena;
        this.kolicina = kolicina;
        this.sifra = UUID.randomUUID().toString();
    }

    public String getNaziv() {
        return naziv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public BigDecimal getCijena() {
        return cijena;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }
}
