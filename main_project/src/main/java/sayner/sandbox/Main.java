package sayner.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sayner.libbox.Simple;

// Верховный класс
@SpringBootApplication
public class Main {

    // Входная точка приложения
    public static void main(String[] args) {

        // for ex
        System.out.println("Hi from dry gradle");
        Simple simple = new Simple();
        simple.setValue(10);
        System.out.println("Value from Simple: " + simple.getValue());

        // run spring boot
        SpringApplication.run(Main.class, args);

    }
}


