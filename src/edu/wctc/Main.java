package edu.wctc;

import java.util.*;

public class Main {

    private final static FileInput indata = new FileInput("the_book.csv");
    private final static Map<String, Integer> map = new HashMap<>();


    public Main() {
        String line;
        String[] words;

        while ((line = indata.fileReadLine()) != null) {
            // Remove anything that's not a letter or space
            line = line.replaceAll("[^a-zA-Z ]", "")
                    .toLowerCase().trim();

            // Don't process lines with no characters
            if (line.isEmpty()) {
                continue;
            }

            // Split string over one or more spaces
            words = line.split(" +");

            // Look for each word in the map
            for (String word : words) {
                // This word isn't yet a key, init count to 1
                if (!map.containsKey(word)) {
                    map.put(word, 1);
                } else {
                    // Increment count using word as key
                    map.put(word, map.get(word) + 1);
                }

            }
        }

        Scanner keyboard = new Scanner(System.in);
        String resp = "0";
        while (!resp.equals("4"))
        {
            System.out.println("\nWhat would you like to do?\n" +
                    "1. Display top 10 most used words\n" +
                    "2. Display words only used once\n" +
                    "3. Display all words with how many uses\n" +
                    "4. Leave");
            resp = keyboard.nextLine();
            if (resp.equals("1")) {
                System.out.println("--- Top 10 used words with amount ---\n");
                List<Map.Entry<String, Integer>> topTen = new LinkedList<>(map.entrySet());
                Collections.sort(topTen, new Comparator<Map.Entry<String, Integer>>(){

                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });

                int loopCount = 1;
                    for (Map.Entry<String, Integer> item : topTen) {
                        if (loopCount <= 10) {
                            System.out.println(loopCount +": " + item);
                        }
                        loopCount++;
                    }
            }
            if (resp.equals("2")) {
                System.out.println("--- Words that were only used once ---\n");
                int count = 1;
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    if (entry.getValue() == 1){
                        System.out.println(count + ": " + entry.getKey() + " " + entry.getValue());
                        count++;
                    }
                }
            }
            if (resp.equals("3")) {
                System.out.println("--- All words with amount ---\n");
                int count = 1;
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        System.out.println(count + ": " + entry.getKey() + " " + entry.getValue());
                        count++;
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}