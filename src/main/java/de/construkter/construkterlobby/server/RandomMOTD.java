package de.construkter.construkterlobby.server;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class RandomMOTD extends BukkitRunnable {
    @Override
    public void run() {
        int random = (int) (Math.random() * 10);
        if (random == 3) random = 0;
        if (random == 4) random = 1;
        if (random == 5) random = 0;
        if (random == 6) random = 1;
        if (random == 7) random = 0;
        if (random == 8) random = 1;
        if (random == 9) random = 2;
        switch (random) {
            case 0:
                setMotd("\\u00a73\\u00a7kc \\u00a73\\u00a7lCONSTRUKTER.DE \\u00a73\\u00a7kc\\u00a7r\\n\\u00a76Survival, Varo, Events & more");
                break;
            case 1:
                setMotd("\\u00a73\\u00a7l\\u00a7kC \\u00a76\\u00a7lConstrukter.de \\u00a73\\u00a7l\\u00a7kC\\u00a7r\\n\\u00a72Glitzorium SMP Event");
                break;
            case 2:
                setMotd("\\u00a73\\u00a7l\\u00a7kC \\u00a76\\u00a7lConstrukter.de \\u00a73\\u00a7l\\u00a7kC\\u00a7r\\n\\u00a7b\\u00a7oEaster Egg: Squiizyy macht Hugo nach");
                break;
        }
    }

    private void setMotd(String s) {
        Bukkit.getServer().setMotd(s);
    }
}
