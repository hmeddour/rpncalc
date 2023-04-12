package com.exaltit.rpncalc.domain;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution1 {

    public static void main(String[] args) {

        Regex_Test tester = new Regex_Test();
        tester.checker("__________"); // Use \\ instead of using \
    }
}

class Regex_Test {
    public void checker(String Regex_Pattern) {

        Scanner Input = new Scanner(System.in);
        String Test_String = Input.nextLine();

        if (Test_String.length() % 2 == 0) {
            long countA = Test_String.chars().filter(ch -> ch == 'a').count();
            long countC = Test_String.chars().filter(ch -> ch == 'c').count();
            long countB = Test_String.chars().filter(ch -> ch == 'b').count();
            long countD = Test_String.chars().filter(ch -> ch == 'd').count();

            if (countA == countC && countB == countD) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }
        } else {
            System.out.println(false);
        }

        Pattern p = Pattern.compile(Regex_Pattern);
        Matcher m = p.matcher(Test_String);
        // System.out.println(m.find());
    }
}
