package hr.java.spring.boot.HardwareJPA.service;

import hr.java.spring.boot.HardwareJPA.domain.Hardware;
import hr.java.spring.boot.HardwareJPA.dto.HardwareDTO;
import hr.java.spring.boot.HardwareJPA.dto.HardwareFilterParams;
import hr.java.spring.boot.HardwareJPA.dto.HardwareRequestDTO;
import hr.java.spring.boot.HardwareJPA.dto.NewHardwareResponseDTO;
import hr.java.spring.boot.HardwareJPA.repository.HardwareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HardwareServiceImpl implements HardwareService{
    private HardwareRepository hardwareRepository;

    public HardwareServiceImpl(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public List<HardwareDTO> getAllHardware() {
        return hardwareRepository.getAllHardware().stream().map(this::convertToDTO).toList();
    }

    @Override
    public HardwareDTO getHardwareByUUID(String uuid) {
        return convertToDTO(hardwareRepository.getHardwareByUUID(uuid));
    }

    private HardwareDTO convertToDTO(Hardware hardware) {
        return hardware == null ? null : new HardwareDTO(hardware);
    }

    @Override
    public NewHardwareResponseDTO addNewHardware(HardwareRequestDTO newHardware) {
        return new NewHardwareResponseDTO(hardwareRepository.addNewHardware(new Hardware(newHardware)));
    }

    @Override
    public void updateHardware(String uuid, HardwareRequestDTO updatedHardware) {
        hardwareRepository.updateHardware(uuid, new Hardware(updatedHardware));
    }

    @Override
    public List<HardwareDTO> filterHardware(HardwareFilterParams params) {
        return hardwareRepository.filterHardware(params).stream().map(this::convertToDTO).toList();
    }

    @Override
    public void deleteHardware(String uuid) {
        hardwareRepository.deleteHardware(uuid);
    }

    @Override
    public boolean hardwareExists(String uuid) {
        return hardwareRepository.hardwareExists(uuid);
    }
}
