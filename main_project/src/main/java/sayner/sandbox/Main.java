package sayner.sandbox;

import sayner.libbox.Simple;

// Верховный класс	
public class Main {

    // Входная точка приложения
    public static void main(String[] args) {

        System.out.println("Hi from dry gradle");


        Simple simple = new Simple();
        simple.setValue(10);
        System.out.println("Value from Simple: " + simple.getValue());

    }
}


