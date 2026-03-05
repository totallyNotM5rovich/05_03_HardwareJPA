package hr.java.spring.boot.HardwareJPA.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class HardwareRequestDTO {
    @NotBlank(message = "Naziv je obavezan podatak!")
    private String naziv;
    @PositiveOrZero(message = "Cijena ne moze biti negativna!")
    private BigDecimal cijena;
    @Pattern(regexp = "CPU|GPU|MBO|RAM|STORAGE|OTHER", message = "Moguca vrijednost tipa je jedna od sljedecih vrijednosti: 'CPU', 'GPU', 'MBO', 'RAM', 'STORAGE' ili 'OTHER'")
    @NotBlank(message = "Tip je obavezan podatak!")
    private String tip;

    public HardwareRequestDTO() {
    }

    public HardwareRequestDTO(String naziv, BigDecimal cijena, String tip) {
        this.naziv = naziv;
        this.cijena = cijena;
        this.tip = tip;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public BigDecimal getCijena() {
        return cijena;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
