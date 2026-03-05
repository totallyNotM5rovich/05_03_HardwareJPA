package hr.java.spring.boot.HardwareJPA.controller;

import hr.java.spring.boot.HardwareJPA.dto.HardwareDTO;
import hr.java.spring.boot.HardwareJPA.dto.HardwareFilterParams;
import hr.java.spring.boot.HardwareJPA.dto.HardwareRequestDTO;
import hr.java.spring.boot.HardwareJPA.dto.NewHardwareResponseDTO;
import hr.java.spring.boot.HardwareJPA.service.HardwareService;
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

    //NIJE IMPLEMENTIRANO
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

//TEST:
//
//GET (dohvacanje svih)
//curl http://localhost:8081/hardware
//
//GET (dohvacanje po sifri)
//curl http://localhost:8081/hardware/d65d77d4-1fdd-4e09-89d1-1c917d31dd80
//
//POST
//curl -X POST http://localhost:8081/hardware/new -H "Content-Type: application/json" -d '{"cijena":669.99,"naziv":"KINGSTON Fury Beast KF560C30BBE-32 DDR5","tip":"RAM"}'
//
//PUT
//curl -X PUT http://localhost:8081/hardware/<uuid> -H "Content-Type: application/json" -d '{"cijena":679.99,"naziv":"KINGSTON Fury Beast KF560C30BBE-32 DDR5","tip":"RAM"}'
//
//DELETE
//curl -X DELETE http://localhost:8081/hardware/<uuid> -v
//
//FILTRIRANJE
//curl --get http://localhost:8081/hardware/filter --data-urlencode "minCijena=200" --data-urlencode "maxCijena=700"
//curl --get http://localhost:8081/hardware/filter --data-urlencode "minCijena=200" --data-urlencode "maxCijena=700" --data-urlencode "naziv=x87"
//curl --get http://localhost:8081/hardware/filter --data-urlencode "minCijena=200" --data-urlencode "maxCijena=700" --data-urlencode "kategorije=MBO"



