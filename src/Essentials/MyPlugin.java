package Essentials;

import PluginReference.*;

import java.io.File;
import java.io.PrintWriter;

public class MyPlugin extends PluginBase {
    public static MC_Server server = null;

    private static final String rulestxt  = "rules.txt";
    private static final String ConfigDir = "Config";
    public static final String HomeFile   = "Config/homes.yml";
    public static final String SpawnFile  = "Config/spawn.yml";

    File r_f       = new File(rulestxt),
         configDir = new File(ConfigDir),
         home_f    = new File(HomeFile),
         spawn_f   = new File(SpawnFile);

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
        server.registerCommand(new Burn());
        server.registerCommand(new Ext());
        server.registerCommand(new Home());
        server.registerCommand(new Sethome());
        server.registerCommand(new Broadcast());
        server.registerCommand(new Tpall());
        server.registerCommand(new Ping());
        server.registerCommand(new Afk());
        server.registerCommand(new Setspawn());
        server.registerCommand(new Spawn());
        server.registerCommand(new Day());
        server.registerCommand(new Night());

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

            if(ok) {
                System.out.println(" [*] Config dir created, creating files...");
                CheckIfHomeExists();
            }
        } else {
            System.out.println(" [*] Config dir detected.");
            CheckIfHomeExists();
        }

        if (!spawn_f.exists()) {
            System.out.println(" [*] Config/spawn.yml not found... Creating.");
            PrintWriter sf = null;

            try {
                sf = new PrintWriter(SpawnFile, "UTF-8");
                System.out.println(" [*] Config/spawn.yml created.");
            } catch (Exception e) {
                System.out.println(" [*] Could not create Config/spawn.yml.");
            } finally {
                if (sf != null)
                    sf.close();
            }
        } else
            System.out.println(" [*] Config/spawn.yml detected.");

        System.out.println("=== Essentials enabled ===");
        this.server = server;
    }

    public void onShutdown() {
        System.out.println("=== Essentials disabled ===");
    }

    public void CheckIfHomeExists() {
        if(!home_f.exists()) {
            System.out.println(" [*] Config/homes.yml not found... Creating.");
            PrintWriter nrf = null;

            try {
                nrf = new PrintWriter(HomeFile, "UTF-8");
                System.out.println(" [*] homes.yml created.");
            } catch(Exception e) {
                System.out.println(" [*] Could not create homes.yml.");
            } finally {
                if (nrf != null)
                    nrf.close();
            }
        } else {
            System.out.println(" [*] Config/homes.yml detected.");
        }
    }

    public PluginInfo getPluginInfo() {
        PluginInfo info = new PluginInfo();
        info.description = "Essentials commands for your server";
        info.name = "Essentials";
        info.version = "1.5";
        return info;
    }
}
