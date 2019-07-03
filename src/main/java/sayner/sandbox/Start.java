package sayner.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

@SpringBootApplication
public class Start {

    // Входная точка приложения
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}


