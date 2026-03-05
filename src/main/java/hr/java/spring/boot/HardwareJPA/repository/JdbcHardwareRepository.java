package hr.java.spring.boot.HardwareJPA.repository;

import hr.java.spring.boot.HardwareJPA.domain.Hardware;
import hr.java.spring.boot.HardwareJPA.domain.Tip;
import hr.java.spring.boot.HardwareJPA.dto.HardwareFilterParams;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Primary
@Repository
public class JdbcHardwareRepository implements HardwareRepository{
    private NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcHardwareRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Hardware> getAllHardware() {
        return jdbcTemplate.query("SELECT * FROM HARDWARE", new HardwareMapper());
    }

    @Override
    public Hardware getHardwareByUUID(String uuid) {
        return jdbcTemplate.getJdbcTemplate().queryForObject("SELECT * FROM HARDWARE WHERE SIFRA = ?", new HardwareMapper(), uuid);
    }

    @Override
    public String addNewHardware(Hardware newHardware) {
        Map<String, Object> params = Map.of(
                "naziv", newHardware.getNaziv(),
                "sifra", newHardware.getSifra(),
                "tip", newHardware.getTip().getId(),
                "cijena", newHardware.getCijena(),
                "kolicina", newHardware.getKolicina()
        );
        Integer id = jdbcTemplate.queryForObject("SELECT ID FROM FINAL TABLE (INSERT INTO HARDWARE (NAZIV, SIFRA, CIJENA, KOLICINA, TIPID) VALUES (:naziv, :sifra, :cijena, :kolicina, :tip)) HARDWARE", params, Integer.class);
        newHardware.setId(Long.valueOf(id));
        return newHardware.getSifra();
    }

    @Override
    public void updateHardware(String uuid, Hardware updatedHardware) {
        Map<String, Object> params = Map.of(
                "naziv", updatedHardware.getNaziv(),
                "tip", updatedHardware.getTip().getId(),
                "cijena", updatedHardware.getCijena(),
                "uuid", uuid
        );
        jdbcTemplate.update("UPDATE HARDWARE SET NAZIV = :naziv , TIPID = :tip , CIJENA = :cijena WHERE SIFRA = :uuid", params);

    }

    @Override
    public void deleteHardware(String uuid) {
        jdbcTemplate.getJdbcTemplate().update("DELETE FROM HARDWARE WHERE SIFRA = ?", uuid);
    }

    @Override
    public boolean hardwareExists(String uuid) {
        return jdbcTemplate.getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM HARDWARE WHERE SIFRA = ?", Integer.class, uuid) > 0;
    }

    @Override
    public List<Hardware> filterHardware(HardwareFilterParams parametriRute) {
        String sql = "SELECT * FROM HARDWARE";
        List<String> sqlUvjeti = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();
        if(parametriRute.getNaziv() != null) {
            sqlUvjeti.add("LOWER(NAZIV) LIKE LOWER(:naziv)");
            params.put("naziv", "%" + parametriRute.getNaziv() + "%");
        }
        if(parametriRute.getKategorije() != null) {
            sqlUvjeti.add("TIPID IN (:tip)");
            params.put("tip", parametriRute.getKategorije().stream().map(tip -> Tip.getTip(tip).getId()).toList());
        }

        if(parametriRute.getMinCijena() != null && parametriRute.getMaxCijena() != null) {
            sqlUvjeti.add("CIJENA BETWEEN :minCijena AND :maxCijena");
            params.put("minCijena", parametriRute.getMinCijena());
            params.put("maxCijena", parametriRute.getMaxCijena());
        } else if (parametriRute.getMinCijena() != null) {
            sqlUvjeti.add("CIJENA >= :minCijena");
            params.put("minCijena", parametriRute.getMinCijena());
        } else if (parametriRute.getMaxCijena() != null) {
            sqlUvjeti.add("CIJENA >= :maxCijena");
            params.put("maxCijena", parametriRute.getMaxCijena());
        }

        if(!sqlUvjeti.isEmpty()) {
            sql = sql.concat(" WHERE ").concat(String.join(" AND ", sqlUvjeti));
        }
        return jdbcTemplate.query(sql, params, new HardwareMapper());
    }

    private static class HardwareMapper implements RowMapper<Hardware> {

        public Hardware mapRow(ResultSet rs, int i) throws SQLException {

            Hardware newHardware = new Hardware();
            newHardware.setId(rs.getLong("ID"));
            newHardware.setNaziv(rs.getString("NAZIV"));
            newHardware.setSifra(rs.getString("SIFRA"));
            newHardware.setCijena(rs.getBigDecimal("CIJENA"));
            newHardware.setKolicina(rs.getInt("KOLICINA"));

            int tipId = rs.getInt("TIPID");
            newHardware.setTip(Tip.getTip(tipId));

            return newHardware;
        }
    }
}

