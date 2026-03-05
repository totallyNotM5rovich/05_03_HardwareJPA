package hr.java.spring.boot.HardwareJPA.dto;

import java.math.BigDecimal;
import java.util.List;

public class HardwareFilterParams {
    private String naziv;
    private List<String> kategorije;
    private BigDecimal minCijena;
    private BigDecimal maxCijena;

    public HardwareFilterParams() {
    }

    public HardwareFilterParams(String naziv, List<String> kategorije, BigDecimal minCijena, BigDecimal maxCijena) {
        this.naziv = naziv;
        this.kategorije = kategorije;
        this.minCijena = minCijena;
        this.maxCijena = maxCijena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<String> getKategorije() {
        return kategorije;
    }

    public void setKategorije(List<String> kategorije) {
        this.kategorije = kategorije;
    }

    public BigDecimal getMinCijena() {
        return minCijena;
    }

    public void setMinCijena(BigDecimal minCijena) {
        this.minCijena = minCijena;
    }

    public BigDecimal getMaxCijena() {
        return maxCijena;
    }

    public void setMaxCijena(BigDecimal maxCijena) {
        this.maxCijena = maxCijena;
    }
}
