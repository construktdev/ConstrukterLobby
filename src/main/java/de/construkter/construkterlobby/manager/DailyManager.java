package de.construkter.construkterlobby.manager;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DailyManager {

    public static boolean hasPremium(String player){
        player = player.toLowerCase();
        Map<String, Boolean> premium = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("premium.txt"))) {

            String line;
            while ((line = br.readLine()) != null){
                String[] split = line.split(" ");
                if (split.length == 2){
                    String username = split[0];
                    boolean hasPremium = Boolean.parseBoolean(split[1]);
                    premium.put(username, hasPremium);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (premium!=null && premium.containsKey(player)){
            return premium.get(player);
        }
        return false;
    }

    public static void addPremium(String player){
        try (PrintWriter pw = new PrintWriter(new FileWriter("premium.txt", true))) {
                pw.println(player + " true\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasClaimedDailyReward(String player) {
        try (BufferedReader reader = new BufferedReader(new FileReader("daily.txt"))) {
            String lastUpdate = reader.readLine();
            LocalDate lastUpdateDate = LocalDate.parse(lastUpdate, DateTimeFormatter.ISO_DATE);
            LocalDate today = LocalDate.now();

            if (!lastUpdateDate.isEqual(today)) {
                // Reset the file and update the last update date
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("daily.txt"))) {
                    writer.write(today.format(DateTimeFormatter.ISO_DATE));
                    writer.newLine();
                }
                return false; // Player hasn't claimed the reward yet
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts[0].equals(player)) {
                    return Boolean.parseBoolean(parts[1]);
                }
            }
            return false; // Player not found in the file
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Error reading the file
        }
    }

    public static void addClaimedDailyReward(String player) throws FileNotFoundException {
        String init = "";
        BufferedReader br = new BufferedReader(new FileReader("daily.txt"));
        try{
            while (br.readLine() != null){
                init = init + br.readLine();
            }
            init = init + "\n";
        } catch (IOException e){
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter("daily.txt", true))) {
            pw.println(init + player + " true\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasClaimedDailyRewardNormal(String player) {
        try (BufferedReader reader = new BufferedReader(new FileReader("daily_normal.txt"))) {
            String lastUpdate = reader.readLine();
            LocalDate lastUpdateDate = LocalDate.parse(lastUpdate, DateTimeFormatter.ISO_DATE);
            LocalDate today = LocalDate.now();

            if (!lastUpdateDate.isEqual(today)) {
                // Reset the file and update the last update date
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("daily_normal.txt"))) {
                    writer.write(today.format(DateTimeFormatter.ISO_DATE));
                    writer.newLine();
                }
                return false; // Player hasn't claimed the reward yet
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts[0].equals(player)) {
                    return Boolean.parseBoolean(parts[1]);
                }
            }
            return false; // Player not found in the file
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Error reading the file
        }
    }

    public static void addClaimedDailyRewardNormal(String player) throws FileNotFoundException {
        String init = "";
        BufferedReader br = new BufferedReader(new FileReader("daily_normal.txt"));
        try{
            while (br.readLine() != null){
                init = init + br.readLine();
            }
            init = init + "\n";
        } catch (IOException e){
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter("daily_normal.txt", true))) {
            pw.println(init + player + " true\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
