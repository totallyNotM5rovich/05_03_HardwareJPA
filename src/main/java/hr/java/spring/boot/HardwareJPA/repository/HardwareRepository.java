package hr.java.spring.boot.HardwareJPA.repository;

import hr.java.spring.boot.HardwareJPA.domain.Hardware;
import hr.java.spring.boot.HardwareJPA.dto.HardwareFilterParams;

import java.util.List;

public interface HardwareRepository {
    List<Hardware> getAllHardware();

    Hardware getHardwareByUUID(String uuid);

    String addNewHardware(Hardware newHardware);

    void updateHardware(String uuid, Hardware updatedHardware);

    void deleteHardware(String uuid);

    boolean hardwareExists(String uuid);

    List<Hardware> filterHardware(HardwareFilterParams params);
}
