package hr.java.spring.boot.HardwareJPA.specification;

import hr.java.spring.boot.HardwareJPA.domain.Hardware;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class HardwareSpecificationBuilder {
    public static Specification<Hardware> containsNaziv (String naziv) {
        return (root, query, builder) -> naziv == null ? null : builder.like(builder.lower(root.get("naziv")), "%" + naziv.toLowerCase() + "%");
    }

    public static Specification<Hardware> minCijena (BigDecimal min) {
        return (root, query, builder) -> min == null ? null : builder.greaterThanOrEqualTo(root.get("cijena"), min);
    }

    public static Specification<Hardware> maxCijena (BigDecimal max) {
        return (root, query, builder) -> max == null ? null : builder.lessThanOrEqualTo(root.get("cijena"), max);
    }

    public static Specification<Hardware> kategorije (List<String> kategorije) {

        return (root, query, builder) -> {
            if(kategorije == null || kategorije.isEmpty()) {
                return null;
            }
            Join<Object, Object> tip = root.join("tip");
            return tip.get("naziv").in(kategorije);
        };
    }
}
