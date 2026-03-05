package hr.java.spring.boot.HardwareJPA.repository;

import hr.java.spring.boot.HardwareJPA.domain.Tip;
import org.springframework.data.jpa.repository.JpaRepository;




public interface SpringDataTipRepository extends JpaRepository<Tip, Long> {
    Tip findByNaziv(String naziv);
}
