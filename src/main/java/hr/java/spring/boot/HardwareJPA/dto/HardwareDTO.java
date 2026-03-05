package hr.java.spring.boot.HardwareJPA.dto;


import hr.java.spring.boot.HardwareJPA.domain.Hardware;

import java.math.BigDecimal;

public class HardwareDTO {
    private String naziv;
    private BigDecimal cijena;
    private String tip;
    private String sifra;

    public HardwareDTO(String naziv, BigDecimal cijena, String tip, String sifra) {
        this.naziv = naziv;
        this.cijena = cijena;
        this.tip = tip;
        this.sifra = sifra;
    }

    public HardwareDTO() {
    }

    public HardwareDTO(Hardware hw) {
        this.naziv = hw.getNaziv();
        this.cijena = hw.getCijena();
        this.tip = hw.getTip().getNaziv();
        this.sifra = hw.getSifra();
    }

    public String getNaziv() {
        return naziv;
    }

    public BigDecimal getCijena() {
        return cijena;
    }

    public String getTip() {
        return tip;
    }

    public String getSifra() {
        return sifra;
    }
}
