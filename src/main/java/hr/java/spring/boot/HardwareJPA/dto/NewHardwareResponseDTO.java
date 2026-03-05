package hr.java.spring.boot.HardwareJPA.dto;

public class NewHardwareResponseDTO {
    private String sifra;

    public NewHardwareResponseDTO() {
    }

    public NewHardwareResponseDTO(String sifra) {
        this.sifra = sifra;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }
}
