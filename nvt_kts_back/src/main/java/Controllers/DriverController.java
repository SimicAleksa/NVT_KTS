package Controllers;

import Beans.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DriverController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/drivers")
    public List<Driver> listAll(){
        String sql = "SELECT * FROM drivers";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Driver.class));
    }
}
