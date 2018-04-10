package com.company;

import java.math.BigDecimal;
import java.util.Stack;

public class RPN {


    public  double calculate(String input) {
        StringBuilder output = getExpression(input);
        double result = counting(output);
        return result;
    }

    private  boolean isDelimetr(char c) {
        if ((" =".indexOf(c)) != -1) {
            return true;
        }
        return false;
    }

    private  boolean isOperator(char c) {
        if ("+-/*()".indexOf(c) != -1) {
            return true;
        }
        return false;
    }

    private  byte getPriority(char s) {
        switch (s) {
            case ('('):
                return 0;
            case (')'):
                return 1;
            case ('+'):
                return 2;
            case ('-'):
                return 3;
            case ('*'):
                return 4;
            case ('/'):
                return 4;
            default:
                return 5;
        }
    }



    private  StringBuilder getExpression(String input) {
        StringBuilder output = new StringBuilder(" ");
        Stack<Character> operatopsStak = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (isDelimetr(input.charAt(i))) {
                continue;
            }
            if (Character.isDigit(input.charAt(i))) {
                while (!isDelimetr(input.charAt(i)) && !isOperator(input.charAt(i))) {
                    output = output.append(input.charAt(i));
                    i++;
                    if (i == input.length()) {
                        break;
                    }

                }
                output = output.append(" ");
                i--;

            }
            if (isOperator(input.charAt(i))) {
                if (input.charAt(i) == '(') {
                    operatopsStak.push(input.charAt(i));
                } else if (input.charAt(i) == ')') {
                    char s = operatopsStak.pop();
                    while (s != '(') {
                        output = output.append(s).append(" ");
                        s = operatopsStak.pop();
                    }
                } else {
                    if (!operatopsStak.empty()) {

                        if (getPriority(input.charAt(i)) <= getPriority(operatopsStak.peek())) {

                            output = output.append(operatopsStak.pop()).append(" ");
                        }
                    }
                    operatopsStak.push(input.charAt(i));
                }

            }

        }
        while (!operatopsStak.empty()) {
            output = output.append(operatopsStak.pop()).append(" ");
        }
        return output;

    }

    private  double counting(StringBuilder input) {
        double result = 0;
        Stack<Double> temp = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                StringBuilder a = new StringBuilder("");
                while (!isDelimetr(input.charAt(i)) && !isOperator(input.charAt(i))) {
                    a.append(input.charAt(i));
                    i++;
                    if (i == input.length())
                        break;
                }
                temp.push(Double.parseDouble(String.valueOf(a)));
                i--;
            } else if (isOperator(input.charAt(i))) {
                double a = temp.pop();
                double b = temp.pop();
                switch (input.charAt(i)) {
                    case ('+'):
                        result = b + a;
                        break;
                    case ('-'):
                        result = b - a;
                        break;
                    case ('*'):
                        result = b * a;
                        break;
                    case ('/'):
                        if (a == 0) {
                            System.out.println("Нa ноль делить нельзя!!! \n" +
                                    "Попробуйте снова.");
                            break;
                        }
                        result = b / a;

                        break;
                }
                temp.push(result);
            }
        }
        System.out.print("= ");
        return temp.peek();
    }


}
