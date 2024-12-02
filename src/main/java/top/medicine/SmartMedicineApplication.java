package top.medicine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("top.medicine.dao")
public class SmartMedicineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartMedicineApplication.class, args);
    }

}
