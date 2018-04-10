package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        RPN calc = new RPN();
        while (true) {
            System.out.println("Введите выражение:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String a = reader.readLine();
            System.out.println(calc.calculate(a));

        }

    }
}
