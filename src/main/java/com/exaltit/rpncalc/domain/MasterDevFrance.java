package com.exaltit.rpncalc.domain;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MasterDevFrance {

    public static void main(String[] args) {
        String line = null;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if (line.length() <= 1) {
                System.out.println(line);
                break;
            }

            String[] array = line.split("");
            List<Character> list = line.chars().mapToObj((i) -> Character.valueOf((char) i)).collect(Collectors.toList());
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.size() <= 1) {
                    System.out.println(list.toString());
                    break;
                }

                if (isP(list.get(i)) && isF(list.get(i + 1))
                        || isF(list.get(i)) && isC(list.get(i + 1))
                        || isC(list.get(i)) && isP(list.get(i + 1))) {
                    list.remove(i);
                    System.out.println(list.toString());
                } else if (isP(list.get(i)) && isC(list.get(i + 1))
                        || isF(list.get(i)) && isP(list.get(i + 1))
                        || isC(list.get(i)) && isF(list.get(i + 1))
                        || array[i].equals(array[i + 1])) {
                    list.remove(i + 1);
                    System.out.println(list.toString());
                }
            }
        }
    }

    public static String pfc(String str) {
        String[] array = str.split("");
        List<Character> list = str.chars().mapToObj((i) -> Character.valueOf((char) i)).collect(Collectors.toList());
        for (int i = 0; i < list.size() - 1; i++) {
            if (isP(list.get(i)) && isF(list.get(i + 1))
                    || isF(list.get(i)) && isC(list.get(i + 1))
                    || isC(list.get(i)) && isP(list.get(i + 1))) {
                str = str.substring(i + 1, str.length());
            } else if (array[i].equals(array[i + 1])) {
                str = str.substring(i, i + 1).concat(str.substring(i + 2, str.length()));
            }
        }

        return str;
    }

    public static boolean isP(Character c) {
        return 'P' == c;
    }

    public static boolean isF(Character c) {
        return 'F' == c;
    }

    public static boolean isC(Character c) {
        return 'C' == c;
    }
}
