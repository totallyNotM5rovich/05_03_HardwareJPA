package hr.java.spring.boot.HardwareJPA.controller;

import hr.java.spring.boot.HardwareH2DB.dto.HardwareDTO;
import hr.java.spring.boot.HardwareH2DB.dto.HardwareFilterParams;
import hr.java.spring.boot.HardwareH2DB.dto.HardwareRequestDTO;
import hr.java.spring.boot.HardwareH2DB.dto.NewHardwareResponseDTO;
import hr.java.spring.boot.HardwareH2DB.service.HardwareService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hardware") // http://localhost:8081/hardware
public class HardwareController {
    private HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAllHardware() {
        return hardwareService.getAllHardware();
    }

    @GetMapping("/{uuid}")
    public HardwareDTO getHardwareByUUID(@PathVariable String uuid) {
        return hardwareService.getHardwareByUUID(uuid);
    }

    @PostMapping("/new") // http://localhost:8081/hardware/new
    public ResponseEntity<NewHardwareResponseDTO> addNewHardware(@Valid @RequestBody HardwareRequestDTO newHardware) {
        return new ResponseEntity<>(hardwareService.addNewHardware(newHardware),HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> updateHardware(@PathVariable String uuid, @Valid @RequestBody HardwareRequestDTO updatedHardware) {
        if(!hardwareService.hardwareExists(uuid)) return ResponseEntity.notFound().build();
        hardwareService.updateHardware(uuid, updatedHardware);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteHardware(@PathVariable String uuid) {
        if(!hardwareService.hardwareExists(uuid)) return ResponseEntity.notFound().build();
        hardwareService.deleteHardware(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<HardwareDTO>> filterHardware(HardwareFilterParams params) {
        return ResponseEntity.ok(hardwareService.filterHardware(params));
    }
    // http://localhost:8081/hardware/filter?naziv=ryzen


//    @GetMapping("/brew-coffee")
//    public ResponseEntity<String> brewCoffee() {
//        return new ResponseEntity<>("I'm a teapot", HttpStatus.I_AM_A_TEAPOT);
//    }
}

// "Content-Type: application/json"
// {"cijena":669.99,"naziv":"KINGSTON Fury Beast KF560C30BBE-32 DDR5","tip":"Radna memorija"}

//Proširiti rješenje iz treće vježbe te umjesto "MockHardwareRepository" implementacije dodati novu implementaciju repozitorija koja će koristiti JdbcTemplate te H2 "in memory" baze podataka.
//Pomoću anotacije "@Primary" potrebno je proglasiti novu implementaciju repozitorija primarnim.
//        U "pom.xml" dodati ovisnosti o "spring-boot-starter-jdbc" i "h2"
//Domensku klasu "Hardware" proširiti s dodatnim identifikatorom "Long id" koji će generirati baza podataka.
//Kreirati datoteke "data.sql" i "schema.sql" i u nju dodati SQL naredbe koje će kreirati table u bazi podataka te spremiti podatke u odgovarajuće tablice.


