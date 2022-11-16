//package DataBase;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//@Configuration
//public class DataBaseInitializer {
//
//    @Autowired
//    private static JdbcTemplate jdbcTemplate;
//
//    @Bean
//    CommandLineRunner initDataBase(){
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                jdbcTemplate.execute("CREATE table drivers (id int primary key auto_increment,name varchar(30),email varchar(30))");
//                jdbcTemplate.execute("INSERT INTO drivers(name,email) VALUES ('Neko ime','neki e-mail')");
//                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
//            }
//        };
//    }
//}
