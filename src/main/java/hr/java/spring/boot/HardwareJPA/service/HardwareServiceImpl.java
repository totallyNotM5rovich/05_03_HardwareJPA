package hr.java.spring.boot.HardwareJPA.service;

import hr.java.spring.boot.HardwareJPA.domain.Hardware;
import hr.java.spring.boot.HardwareJPA.dto.HardwareDTO;
import hr.java.spring.boot.HardwareJPA.dto.HardwareFilterParams;
import hr.java.spring.boot.HardwareJPA.dto.HardwareRequestDTO;
import hr.java.spring.boot.HardwareJPA.dto.NewHardwareResponseDTO;
import hr.java.spring.boot.HardwareJPA.repository.SpringDataHardwareRepository;
import hr.java.spring.boot.HardwareJPA.repository.SpringDataTipRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HardwareServiceImpl implements HardwareService{
    private SpringDataHardwareRepository hardwareRepository;
    private SpringDataTipRepository tipRepository;

    public HardwareServiceImpl(SpringDataHardwareRepository hardwareRepository, SpringDataTipRepository tipRepository) {
        this.hardwareRepository = hardwareRepository;
        this.tipRepository = tipRepository;
    }

    @Override
    public List<HardwareDTO> getAllHardware() {
        return hardwareRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Override
    public HardwareDTO getHardwareByUUID(String uuid) {
        return convertToDTO(hardwareRepository.findBySifra(uuid));
    }

    private HardwareDTO convertToDTO(Hardware hardware) {
        return hardware == null ? null : new HardwareDTO(hardware);
    }

    @Override
    public NewHardwareResponseDTO addNewHardware(HardwareRequestDTO newHardware) {
        Hardware newHw = hardwareRepository.save(mapHardwareRequestDTOToHardware(newHardware));
        return new NewHardwareResponseDTO(newHw.getSifra());
    }

    @Override
    public void updateHardware(String uuid, HardwareRequestDTO updatedHardware) {
        Hardware hw = hardwareRepository.findBySifra(uuid);
        hw.setNaziv(updatedHardware.getNaziv());
        hw.setCijena(updatedHardware.getCijena());
        hw.setTip(tipRepository.findByNaziv(updatedHardware.getTip()));
        hardwareRepository.save(hw);
//        hardwareRepository.updateHardware(uuid, new Hardware(updatedHardware));
    }

    @Override
    public List<HardwareDTO> filterHardware(HardwareFilterParams params) {
        List<Hardware> listaHw = hardwareRepository.findAll();
        if(params.getNaziv() != null) {
            listaHw = listaHw.stream().filter(hw -> hw.getNaziv().toLowerCase().contains(params.getNaziv().toLowerCase())).toList();
        }
        if(params.getMinCijena() != null && params.getMaxCijena() != null) {
            listaHw = listaHw.stream().filter(hw -> hw.getCijena().compareTo(params.getMinCijena()) >= 0 && hw.getCijena().compareTo(params.getMaxCijena()) <= 0).toList();
        } else if (params.getMinCijena() != null) {
            listaHw = listaHw.stream().filter(hw -> hw.getCijena().compareTo(params.getMinCijena()) >= 0).toList();
        } else if (params.getMaxCijena() != null) {
            listaHw = listaHw.stream().filter(hw ->  hw.getCijena().compareTo(params.getMaxCijena()) <= 0).toList();
        }
        if(params.getKategorije() != null) {
            listaHw = listaHw.stream().filter(hw -> params.getKategorije().contains(hw.getTip().getNaziv())).toList();
        }

        return listaHw.stream().map(this::convertToDTO).toList();
    }

    @Override
    public void deleteHardware(String uuid) {
        Hardware hw = hardwareRepository.findBySifra(uuid);
        hardwareRepository.delete(hw);
//        hardwareRepository.deleteHardware(uuid);
    }

    @Override
    public boolean hardwareExists(String uuid) {
//        return hardwareRepository.hardwareExists(uuid);
        return hardwareRepository.existsBySifra(uuid);
    }

    private Hardware mapHardwareRequestDTOToHardware(HardwareRequestDTO hardwareRequest) {
        Hardware hw = new Hardware();
        hw.setNaziv(hardwareRequest.getNaziv());
        hw.setSifra(UUID.randomUUID().toString());
        hw.setCijena(hardwareRequest.getCijena());
        hw.setKolicina(0);
        hw.setTip(tipRepository.findByNaziv(hardwareRequest.getTip()));
        return hw;
    }
}
