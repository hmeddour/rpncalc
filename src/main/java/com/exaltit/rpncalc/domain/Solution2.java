package com.exaltit.rpncalc.domain;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'findMinCost' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY efficiency as parameter.
     */

    public static int findMinCost(List<Integer> efficiency) {
        int n = efficiency.size();
        int[] sortedEfficiency = new int[n];
        for (int i = 0; i < n; i++) {
            sortedEfficiency[i] = efficiency.get(i);
        }
        Arrays.sort(sortedEfficiency);

        int minCost = Integer.MAX_VALUE;
        int totalEfficiency = 0;
        for (int i = n - 1; i >= 0; i--) {
            totalEfficiency += sortedEfficiency[i];
            int cost = totalEfficiency * sortedEfficiency[i];
            if (cost < minCost) {
                minCost = cost;
            }
        }
        return minCost;
    }

}

public class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int efficiencyCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> efficiency = IntStream.range(0, efficiencyCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.findMinCost(efficiency);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

