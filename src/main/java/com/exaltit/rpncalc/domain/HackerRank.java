package com.exaltit.rpncalc.domain;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class HackerRank {

    public static final String API_URL = "https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=";

    public static void main(String[] args) {
        String str = "apple";
        ArrayList<String> listS = new ArrayList<>(Arrays.asList(str.split("")));
        listS.add(null);

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("John");
        list.add("Mac");
        list.add("Kevin");

        Iterator<String> it = list.iterator();
        list.remove("Mac");

        while (it.hasNext()) {
            System.out.println(it.next());
        }
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("10", "Ten");
        map.put("20", "Twenty");
        map.put("30", "Thirty");
        map.put(null, "");

        String result = map.putIfAbsent("20", "NewTwenty");
        map.get("20");


        // question 1 :
        int x = 5;
        int y = x++;
        int z = ++y; // z == 5 or 6 or 7 or 8 => 6
        System.out.println(z);

        System.out.println(Arrays.toString(getMovieTitles("spiderman")));
    }

    public static String[] getMovieTitles(String substr) {
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL(API_URL + substr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
        JsonArray array = jsonObject.get("data").getAsJsonArray();

        String[] result = new String[array.size()];
        int i = 0;
        for (JsonElement element : array) {
            JsonObject object = element.getAsJsonObject();
            String title = object.get("Title").getAsString();
            result[i] = title;
            i++;
        }
        Arrays.sort(result);

        return result;
    }

    public static int maxXor(int lo, int hi, int k) {
        int max = 0;
        if ((lo >= 1 || lo <= 10000)
                && (hi >= 1 || hi <= 10000)
                && (k >= 1 || k <= 10000)) {
            for (int i = lo; i <= hi; i++) {
                for (int j = i; j <= hi; j++) {
                    int xor = i ^ j;
                    if (xor <= k && xor > max) {
                        max = xor;
                    }
                }
            }
        }

        return max;
    }

}
