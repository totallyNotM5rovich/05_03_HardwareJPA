package hr.java.spring.boot.HardwareJPA.repository;

import hr.java.spring.boot.HardwareJPA.domain.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SpringDataHardwareRepository extends JpaRepository<Hardware, Long>, JpaSpecificationExecutor<Hardware> {
    List<Hardware> findAllByOrderByNazivAsc();

    Hardware findBySifra(String sifra);

    boolean existsBySifra(String sifra);
}
