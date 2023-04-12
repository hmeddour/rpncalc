package com.exaltit.rpncalc.domain;

import java.util.*;
import java.util.stream.Collectors;

public class CodingGame {
    private List<Object> elements;

    public static void main(String[] args) {
        String str = "abcd";
        str.toUpperCase();
        str.substring(3, 4);
        System.out.println(str);
    }

    /**
     * filter array duplicate
     *
     * @param numbers arrays of numbers with duplicate
     * @return array without duplicates
     */
    public static int[] filterDuplicates(int[] numbers) {
        // return Arrays.stream(numbers).distinct().toArray();
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());

        Set<Integer> set = new HashSet<>();
        for (int i : numbers) {
            set.add(i);
        }
        int[] result = new int[set.size()];
        int j = 0;
        for (int i : set) {
            result[j] = i;
            j++;
        }

        return result;
    }

    /**
     * find two index of sum two pair
     *
     * @param numbers arrays of numbers
     * @param k
     * @return array with the two index of pairs
     */
    public static int[] findSumPair(int[] numbers, int k) {
        // solution 1
        /*
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int complement = k - numbers[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(numbers[i], i);
        }
        return new int[]{0, 0};
         */

        // solution 2
        HashMap<Integer, Integer> my_map = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        result.add(1);
        for (int i = 0; i < numbers.length; i++) {
            if (my_map.containsKey(numbers[i])) {
                int index = my_map.get(numbers[i]);
                result.set(0, index);
                result.set(1, i);
                break;
            } else {
                my_map.put(k - numbers[i], i);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * return two index of two largest element in list
     *
     * @param profits
     * @return array two index
     */
    public static int[] maxProfits(int[] profits) {
        int largestA = Integer.MIN_VALUE, largestB = Integer.MIN_VALUE;
        int indexA = 0, indexB = 0;
        for (int i = 0; i < profits.length; i++) {
            if (profits[i] > largestA) {
                largestB = largestA;
                largestA = profits[i];
                indexA = i;
            } else if (profits[i] > largestB) {
                largestB = profits[i];
                indexB = i;
            }
        }
        return new int[]{indexA, indexB};
    }

    /**
     * lucky money chaneese culture
     *
     * @param money
     * @param giftees
     * @return
     */
    public static int luckyMoney(int money, int giftees) {
        if (money < 0 || money >= 100) return 2;
        if (giftees < 0 || giftees >= 10) return -1;
        if (money >= giftees * 8) return giftees;
        if (money < giftees) return 0;
        int b = money, g = giftees;
        while (b >= 8 + (g - 1) && g > 0) {
            b -= 8;
            g--;
        }
        if (b == 4) return 0;

        System.out.println("result : " + (giftees - g));
        return giftees - g;
    }

    /**
     * compute multiple sum 3, 5, 7
     *
     * @param n
     * @return
     */
    public static int computeMultiplesSum(int n) {
        String s = new String("s");
        if (n < 0 || n >= 1000) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if ((i % 3 == 0) || (i % 5 == 0) || (i % 7 == 0)) {
                sum += i;
            }
        }

        return sum;
    }

    /**
     * frequences words in array
     *
     * @param words
     * @return
     */
    public static int[] countFrequencies(String[] words) {
        List<String> list = Arrays.asList(words);
        Map<String, Integer> map = new TreeMap<>();
        for (String word : list) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        System.out.println("map : " + map);
        return map.values().stream().mapToInt(e -> e).toArray();

        /*
        List<String> listWords = Arrays.asList(words);
        Collections.sort(listWords);
        Map<String, Long> groupingMap = listWords.stream()
                .collect
                        (Collectors
                                .groupingBy(
                                        Function.identity(),
                                        Collectors.counting()
                                )
                        );
        System.out.println("concurrentMap : " + groupingMap);
        Map<String, Integer> concurrentMap = listWords
                .parallelStream()
                .collect(Collectors
                        .toConcurrentMap(w -> w,
                                w -> 1,
                                Integer::sum
                        )
                );
        System.out.println("concurrentMap : " + concurrentMap);
        return groupingMap.values().stream().mapToInt(e -> e.intValue()).toArray();
         */
    }

    public static String[] filterWords(String[] words, String filter) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (filter.contains(String.valueOf(word.charAt(i)))) {
                    result.add(word);
                    break;
                }
            }
        }
        return result.toArray(new String[0]);
    }

    public String jeuxOlymp(String s) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if ('P' == s.charAt(i) && 'F' == s.charAt(i + 1)) {
                sb.append(sb.substring(i + 1, s.length()));
            } else if ('F' == s.charAt(i) && 'C' == s.charAt(i + 1)) {
                sb.append(sb.substring(i + 1, s.length()));
            } else if ('P' == s.charAt(i) && 'C' == s.charAt(i + 1)) {
                sb.append(sb.substring(i + 1, s.length()));
            } else if (s.charAt(i) == s.charAt(i + 1)) {
                sb.append(sb.substring(i + 1, s.length()));
            }
        }

        return sb.toString();
    }

    public static boolean isTwin(String a, String b) {
        StringBuilder sb = new StringBuilder();
        boolean b1 = a.charAt(1) == 'P';

        if (a.length() != b.length()) {
            return false;
        }

        char[] aChar = a.toLowerCase().toCharArray();
        char[] bChar = b.toLowerCase().toCharArray();

        Arrays.sort(aChar);
        Arrays.sort(bChar);

        return Arrays.equals(aChar, bChar);
    }

    static int sumRange(int[] ints) {
        int sum = 0;
        for (int i = 1; i < ints.length; i++) {
            int n = ints[i];
            if (n >= 10 || n <= 100)
                sum += n;
        }
        return sum;
    }

    /*
    public static void main(String[] args) {


        // words frequency
        String[] wordsFrequencies = {"the", "dog", "got", "the", "bone"};
        int[] frequencies = countFrequencies(wordsFrequencies);
        System.out.println("word frequencies : " + frequencies);

        // max profits
        int[] profits = {-1, 9, 0, 8, -5, 6, -24};
        int[] resultProfits = maxProfits(profits);
        System.out.println("result profits : " + Arrays.toString(resultProfits));

        // filter duplicate
        int[] numbers = {1, 1, 2, 2, 3, 4, 4, 5};
        int[] result1 = filterDuplicates(numbers);
        System.out.println("array without duplicates : " + Arrays.toString(result1));

        // find sum two pair
        int[] data = {16, 1, 1, 2, 2, 3, 5, 14, 18, 6, 9, 16, 1, 1, 2, 2, 3, 5, 14, 18, 6, 9, 16, 1, 1, 2, 2, 3, 5, 14, 18, 6, 9, 16, 1, 1, 2, 2, 3, 5, 14, 18, 6, 9, 6, 7};
        int k = 13;
        int[] data1 = findSumPair(data, k);
        System.out.println("sum pairs : " + Arrays.toString(data1));

        String[] words = {"abc", "def", "ghi"};

        // string to list
        String letters = "abc";
        System.out.println("chars : " + letters);
        letters = "abcd";
        System.out.println("chars : " + letters);


        List<Character> chars = letters.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        System.out.println("chars : " + chars);

        // string with comma to list with split
        String fruits = "Grapes, Apple, Mango, Banana, Orange, Melons";
        List<String> fruitList = Arrays.stream(fruits.split("\\,"))
                .map(str -> str.trim())
                .collect(Collectors.toList());
        System.out.println("list de fruits : " + fruitList);

        List<String> result = new ArrayList<>();
        for (int i = 0; i < letters.length(); i++) {
            for (int j = 0; j < words.length; j++) {
                if (result.contains(words[j])) {
                    continue;
                }
                if (words[j].contains(String.valueOf(letters.charAt(i)))) {
                    result.add(words[j]);
                }
            }
        }

        System.out.println("result : " + result.toArray());

        System.out.println("result : " + result.toArray());
        SpringApplication.run(RpncalcApplication.class, args);
    }
     */

    static boolean exists(int[] ints, int k) {
        Math.abs(k);
        if (ints.length == 0)
            return false;

        for (int i = 0; i < ints.length; i++) {
            if (k == ints[i])
                return true;
        }

        return false;
    }

    public static int computeClosestToZero(int[] ts) {
        if (ts.length == 0 || ts.length > 1000)
            return 0;

        int closestToZero = ts[0];
        for (int i = 1; i < ts.length; i++) {
            if (ts[i] > 0 && ts[i] == Math.abs(closestToZero)) {
                closestToZero = ts[i];
            }

            if (Math.abs(ts[i]) < Math.abs(closestToZero)) {
                closestToZero = ts[i];
            }
        }

        return closestToZero;
    }

    public static String translate(String text) {
        String[] array = text.split("");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (isVowel(array[i])) {
                sb.append("av");
                sb.append(array[i]);
                if (i < array.length - 1 && isVowel(array[i + 1])) {
                    sb.append(array[i + 1]);
                    i++;
                }
            } else {
                sb.append(array[i]);
            }
        }

        return sb.toString();
    }

    private static boolean isVowel(String c) {
        String VOWELS = "aeiou";
        return VOWELS.contains(c.toLowerCase());
    }
}
