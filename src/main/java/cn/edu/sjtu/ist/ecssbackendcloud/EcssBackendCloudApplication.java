package cn.edu.sjtu.ist.ecssbackendcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class EcssBackendCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcssBackendCloudApplication.class, args);
    }

}
