package com.luv2code.tdd;

public class FizzBuzz {
    public static String compute(int i) {

        StringBuilder sb=new StringBuilder();
        if (i % 3 == 0 && i % 5 == 0) {
            sb.append("FizzBuzz");
        } else if (i % 3 == 0) {
            sb.append("Fizz");
        } else if (i % 5 == 0) {
            sb.append("Buzz");
        }else{
            sb.append(i);
        }
        return sb.toString();
        /*
        if (i % 3 == 0 && i % 5 == 0) {
            return "FizzBuzz";
        } else if (i % 3 == 0) {
            return "Fizz";
        } else if (i % 5 == 0) {
            return "Buzz";
        }else{
            return Integer.toString(i);
        }
        */

    }
}
