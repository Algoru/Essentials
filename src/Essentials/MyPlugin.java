package Essentials;

import PluginReference.*;

import java.io.File;
import java.io.PrintWriter;

public class MyPlugin extends PluginBase {
    public static MC_Server server = null;

    public void onStartup(MC_Server server) {
        server.registerCommand(new Fly());
        server.registerCommand(new Survival());
        server.registerCommand(new Creative());
        server.registerCommand(new Adventure());
        server.registerCommand(new Spectator());
        server.registerCommand(new ClearChat());
        server.registerCommand(new Rules());
        server.registerCommand(new Heal());
        server.registerCommand(new Feed());
        server.registerCommand(new More());
        server.registerCommand(new Skull());
        server.registerCommand(new Repair());
        server.registerCommand(new Realname());
        server.registerCommand(new Nick());
        server.registerCommand(new Msg());
        server.registerCommand(new Kickall());
        server.registerCommand(new Tphere());
        server.registerCommand(new Tppos());


        File r_f = new File("rules.txt");

        if(!r_f.exists()) {
            System.out.println(" [*] rules.txt not found... Creating.");
            PrintWriter nrf = null;

            try {
                nrf = new PrintWriter("rules.txt", "UTF-8");
                nrf.write("1. Be nice\n");
                nrf.write("2. Edit this file at your server folder. (file name = rules.txt)");
                System.out.println(" [*] rules.txt created.");
            } catch(Exception e) {
                System.out.println(" [*] Could not create rules.txt");
            } finally {
                if(nrf != null)
                    nrf.close();
            }
        } else
            System.out.println(" [*] rules.txt detected.");

        System.out.println("=== Essentials enabled ===");
        this.server = server;
    }

    public void onShutdown() {
        System.out.println("=== Essentials disabled ===");
    }

    public PluginInfo getPluginInfo() {
        PluginInfo info = new PluginInfo();
        info.description = "Essentials commands for your server";
        info.name = "Essentials";
        info.version = "1.1";
        return info;
    }
}
