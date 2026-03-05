package hr.java.spring.boot.HardwareJPA.service;

import hr.java.spring.boot.HardwareJPA.dto.HardwareDTO;
import hr.java.spring.boot.HardwareJPA.dto.HardwareFilterParams;
import hr.java.spring.boot.HardwareJPA.dto.HardwareRequestDTO;
import hr.java.spring.boot.HardwareJPA.dto.NewHardwareResponseDTO;

import java.util.List;

public interface HardwareService {
    List<HardwareDTO> getAllHardware();

    HardwareDTO getHardwareByUUID(String uuid);

    NewHardwareResponseDTO addNewHardware(HardwareRequestDTO newHardware);

    void updateHardware(String uuid, HardwareRequestDTO updatedHardware);

    void deleteHardware(String uuid);

    boolean hardwareExists(String uuid);

//    List<HardwareDTO> filterHardware(HardwareFilterParams params);
}
