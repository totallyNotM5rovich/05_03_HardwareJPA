package hr.java.spring.boot.HardwareJPA.repository;

import hr.java.spring.boot.HardwareJPA.domain.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpringDataHardwareRepository extends JpaRepository<Hardware, Long>, JpaSpecificationExecutor<Hardware> {
    Hardware findBySifra(String sifra);

    boolean existsBySifra(String sifra);
}
