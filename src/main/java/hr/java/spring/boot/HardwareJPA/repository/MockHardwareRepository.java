package hr.java.spring.boot.HardwareJPA.repository;

import hr.java.spring.boot.HardwareJPA.domain.Hardware;
import hr.java.spring.boot.HardwareJPA.domain.Tip;
import hr.java.spring.boot.HardwareJPA.dto.HardwareFilterParams;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MockHardwareRepository implements HardwareRepository {
    private static List<Hardware> hardwareList;

    static {
        hardwareList = new ArrayList<>();

        hardwareList.add(new Hardware("AMD Ryzen 9 7900X", Tip.CPU, new BigDecimal("389.99"), 8));
        hardwareList.add(new Hardware("ASUS Prime X870-P", Tip.MBO, new BigDecimal("289.99"), 3));
        hardwareList.add(new Hardware("GIGABYTE AMD Radeon RX 9070 XT GAMING OC", Tip.GPU, new BigDecimal("929.99"), 2));
    }

    @Override
    public List<Hardware> getAllHardware() {
        return hardwareList;
    }

    @Override
    public Hardware getHardwareByUUID(String uuid) {
        return hardwareList.stream().filter(hardware -> hardware.getSifra().equalsIgnoreCase(uuid)).findFirst().orElse(null);
    }

    @Override
    public String addNewHardware(Hardware newHardware) {
        hardwareList.add(newHardware);
        return newHardware.getSifra();
    }

    @Override
    public void updateHardware(String uuid, Hardware updatedHardware) {
        Hardware hardware = hardwareList.stream().filter(hw -> uuid.equalsIgnoreCase(hw.getSifra())).findFirst().orElse(null);
        hardware.setNaziv(updatedHardware.getNaziv());
        hardware.setCijena(updatedHardware.getCijena());
        hardware.setTip(updatedHardware.getTip());
    }

    @Override
    public void deleteHardware(String uuid) {
        Hardware hardware = hardwareList.stream().filter(hw -> uuid.equalsIgnoreCase(hw.getSifra())).findFirst().orElse(null);
        hardwareList.remove(hardware);
    }

    @Override
    public boolean hardwareExists(String uuid) {
        return hardwareList.stream().filter(hw -> uuid.equalsIgnoreCase(hw.getSifra())).findFirst().isPresent();
    }

    @Override
    public List<Hardware> filterHardware(HardwareFilterParams params) {
        //TODO implementirati metodu, nije bitno za rjesenje zadatka
        return List.of();
    }
}
