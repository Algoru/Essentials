package Essentials;

import PluginReference.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MyPlugin extends PluginBase {
    public static MC_Server server = null;

    public static ArrayList<String> afkPlayers = new ArrayList<String>();

    private static final String rulestxt  = "rules.txt";
    private static final String ConfigDir = "Config";
    private static final Utils utils = new Utils();

    File r_f       = new File(rulestxt),
         configDir = new File(ConfigDir);

    public void onStartup(MC_Server server) {
        this.server = server;

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
        server.registerCommand(new Burn());
        server.registerCommand(new Ext());
        server.registerCommand(new Broadcast());
        server.registerCommand(new Tpall());
        server.registerCommand(new Ping());
        server.registerCommand(new Afk());
        server.registerCommand(new Day());
        server.registerCommand(new Night());
        server.registerCommand(new Speed());
        server.registerCommand(new Vanish());
        server.registerCommand(new Helpop());

        if(!r_f.exists()) {
            System.out.println(" [*] rules.txt not found... Creating.");
            PrintWriter nrf = null;

            try {
                nrf = new PrintWriter(rulestxt, "UTF-8");
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

        if(!configDir.exists()) {
            boolean ok = false;
            System.out.println(" [*] Config dir not found... Creating.");

            try {
                configDir.mkdir();
                ok = true;
            } catch(Exception e) {
                System.out.println(" [*] Could not create Config dir.");
            }

            if(ok)
                System.out.println(" [*] Config dir created, creating files...");
        } else
            System.out.println(" [*] Config dir detected.");

        System.out.println("=== Essentials enabled ===");
    }

    public void onShutdown() {
        System.out.println("=== Essentials disabled ===");
    }

    public PluginInfo getPluginInfo() {
        PluginInfo info = new PluginInfo();
        info.description = "Essentials commands for your server";
        info.name = "Essentials";
        info.version = "1.5";
        return info;
    }
}
