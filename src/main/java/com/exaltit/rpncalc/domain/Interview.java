package com.exaltit.rpncalc.domain;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class Interview {

    public static class Acquaintance {

        protected String name;

        public Acquaintance(String name) {
            this.name = name;
        }

        public void getStatus() {
            System.out.println(this.name + " is just an acquaintance");
        }
    }

    public static class Friend extends Acquaintance {

        protected String homeTown;

        public Friend(String name, String homeTown) {
            super(name);
            this.homeTown = homeTown;
        }

        @Override
        public void getStatus() {
            System.out.println(this.name + " is just a friend and is from " + this.homeTown);
        }
    }

    public static class BestFriend extends Friend {

        private String favoriteSong;

        public BestFriend(String name, String homeTown, String favoriteSong) {
            super(name, homeTown);
            this.favoriteSong = favoriteSong;
        }

        @Override
        public void getStatus() {
            System.out.println(this.name + " is my best friend.");
            System.out.println("He is from " + this.homeTown + " and his favorite song is " + this.favoriteSong);
        }
    }


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner sc = new Scanner(System.in);
        String sub = sc.nextLine();
        int t = Integer.parseInt(sub);
        for (int i = 0; i < t; i++) {

            String[] input = sc.nextLine().split(" ");

            if (input[0].equals("Acquaintance")) {
                String friendName = input[1];
                Acquaintance acq = new Acquaintance(friendName);
                acq.getStatus();
            } else if (input[0].equals("Friend")) {
                String friendName = input[1];
                String homeTown = input[2];
                Friend frnd = new Friend(friendName, homeTown);
                frnd.getStatus();
            } else if (input[0].equals("BestFriend")) {
                String friendName = input[1];
                String homeTown = input[2];
                String favoriteSong = input[3];
                BestFriend bf = new BestFriend(friendName, homeTown, favoriteSong);
                bf.getStatus();
            }
        }


        int x = 5;
        int y = x++;
        int z = ++y;
        System.out.println(z);

        // tester prices
        int[] prices = new int[]{10, 20, 30, 40, 50, 60, 90};
        short discount = 20;
        System.out.println("total prices " + computePrice(prices, discount));

        String text = "hEllo world Outlook aUx";
        // havEllavo wavorld avOutlavook avaUx;
        System.out.println("havEllavo wavorld avOutlavook avaUx");
        System.out.println(translate(text));

        int[] array = new int[]{7, -10, 13, 8, 4, -7, -12, -3, 3, -9, 6, -1, -6, 7};
        System.out.println("closest to zero is : " + computeClosestToZero(array));

        String line;
        Scanner sc1 = new Scanner(System.in);
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            /* Lisez les données et effectuez votre traitement */
            System.out.println(line);
            if ('P' == line.charAt(line.length() - 2) && 'F' == line.charAt(line.length() - 1)) {
                System.out.println("result : " + line.substring(line.length() - 2, line.length() - 1));
            } else if ('F' == line.charAt(line.length() - 2) && 'C' == line.charAt(line.length() - 1)) {
                System.out.println("result : " + line.substring(line.length() - 2, line.length() - 1));
            } else if ('C' == line.charAt(line.length() - 2) && 'P' == line.charAt(line.length() - 1)) {
                System.out.println("result : " + line.substring(line.length() - 2, line.length() - 1));
            } else if ('P' == line.charAt(line.length() - 2) && 'C' == line.charAt(line.length() - 1)) {
                System.out.println("result : " + line.substring(line.length() - 2, line.length() - 1));
            }
        }

        Book b0 = new Book("Les Métamorphoses");
        Book b1 = new Book("Les Enfants de minuit");
        Book b2 = new Book("Hamlet");
        Book b3 = new Book("Les Voyages de Gulliver");
        Book b4 = new Book("La Mort d'Ivan Ilitch");
        Book b5 = new Book("Mrs Dalloway");
        Book b6 = new Book("Mémoires d'Hadrien");
        Book b7 = new Book("Le Jardin des fruits");
        Book b8 = new Book("Le Carnet d'or");
        Book b9 = new Book("Madame Bovary");

        List<Person> persons = List.of(
                new Person("Gilles", 35, List.of(b1, b2, b3, b4, b9)),
                new Person("Adrien", 27, List.of(b0, b6, b3, b9)),
                new Person("Emilie", 29, List.of(b7, b2, b4)),
                new Person("Carlos", 29, List.of(b8, b1, b6)),
                new Person("Rafik", 27, List.of(b1, b2, b3, b4, b9)),
                new Person("Sabine", 35, Collections.emptyList()),
                new Person("Hanss", 18, List.of(b5)),
                new Person("Fatima", 15, List.of(b9))
        );

        // 1 - La liste des noms des personnes majeures.
        System.out.println("\nLa liste des noms des personnes majeures :");

        List<String> adults = persons
                .stream()
                .filter(p -> p.getAge() >= 18)
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(adults);

        // 2 - L'ensemble des livres lus.
        System.out.println("\nL'ensemble des livres lus");

        Set<Book> booksRead = persons
                .stream()
                .flatMap(p -> p.getBooks().stream())
                .collect(Collectors.toSet());
        booksRead.add(null);
        System.out.println(booksRead);

        // 3 - Les ensembles des livres lus indexés par l'âge du lecteur.
        System.out.println("\nLes ensembles des livres lus indexés par l'âge du lecteur");

        Map<Integer, Set<Book>> booksReadByAge = new HashMap<>();
        for (Person person : persons) {
            int age = person.getAge();
            List<Book> books = person.getBooks();
            if (!booksReadByAge.containsKey(age)) {
                booksReadByAge.put(age, new HashSet<>());
            }
            booksReadByAge.get(age).addAll(books);
        }

        System.out.println(toString(booksReadByAge));

        // 4 - Pour chaque livre, le nombre de personnes l'ayant lu
        System.out.println("\nPour chaque livre, le nombre de personnes l'ayant lu");

        List<Book> books = Arrays.asList(b0, b1, b2, b3, b4, b5, b6, b7, b8, b9);
        Map<Book, Long> ownerCountByBook = new HashMap<Book, Long>();
        books.stream().forEach(b -> {
            AtomicInteger ownerCount = new AtomicInteger();
            persons.stream().forEach(p -> {
                if (p.getBooks().contains(b)) {
                    ownerCount.getAndIncrement();
                }
            });

            ownerCountByBook.put(b, ownerCount.longValue());
        });
        System.out.println(toString(ownerCountByBook));
    }

    static <K, V> String toString(Map<K, V> map) {
        if (map == null) {

            return "null";

        }
        return map.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " => " + entry.getValue())
                .collect(Collectors.joining("\n"));

    }

    public static int computeClosestToZero(int[] ts) {
        if (ts.length == 0 || ts.length > 1000)
            return 0;

        int closestToZero = ts[0];
        for (int i = 1; i < ts.length; i++) {
            if ((ts[i] > 0 && ts[i] == Math.abs(closestToZero))
                    || Math.abs(ts[i]) < Math.abs(closestToZero)) {
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
        String VOWELS = "aeiouAEIOU";
        return VOWELS.contains(c);
    }

    public static int findLargest(int[] numbers) {
        // Your code goes here
        int size = numbers.length;
        if (size == 0)
            return 0;

        int max = numbers[0];
        for (int i = 1; i < size; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        return max;
    }

    public static int computePrice(int[] prices, int discount) {
        int size = prices.length;
        if (discount < 0 || discount > 100 || size == 0 || size > 100) {
            return 0;
        }

        int total = 0;
        for (int i = 0; i < size; i++) {
            total += prices[i];
        }

        int maxPrice = findLargest(prices);
        int priceDiscounted = (maxPrice * discount) / 100;

        return total - priceDiscounted;
    }
}
