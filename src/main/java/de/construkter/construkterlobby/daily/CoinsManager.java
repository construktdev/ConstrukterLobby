package de.construkter.construkterlobby.daily;

import de.construkter.construkterlobby.manager.Prefix;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CoinsManager {
    private static final String FILE_NAME = "coins.txt";
    public static void addCoins(int coins, String name) {
        Map<String, Integer> coinMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null){
                String[] split = line.split(" ");
                if (split.length == 2){
                    String username = split[0];
                    int userCoins = Integer.parseInt(split[1]);
                    coinMap.put(username, userCoins);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        coinMap.put(name, coinMap.getOrDefault(name, 0) + coins);


        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            for (Map.Entry<String, Integer> entry : coinMap.entrySet()){
                pw.println(entry.getKey() + " " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setCoins(int coins, String name) {
        Map<String, Integer> coinMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null){
                String[] split = line.split(" ");
                if (split.length == 2){
                    String username = split[0];
                    int userCoins = Integer.parseInt(split[1]);
                    coinMap.put(username, userCoins);
                }
            }
        } catch (IOException e) {
            System.out.println(Prefix.errorPrefix() + "File Error: " + e.getMessage());
        }

        coinMap.put(name, coins);

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            for (Map.Entry<String, Integer> entry : coinMap.entrySet()){
                pw.println(entry.getKey() + " " + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println(Prefix.errorPrefix() + "File Error: " + e.getMessage());
        }
    }
}
