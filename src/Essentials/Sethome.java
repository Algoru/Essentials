package Essentials;

import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Location;
import PluginReference.MC_Player;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Sethome implements MC_Command {
    @Override
    public List<String> getAliases() {
        return Arrays.asList("createhome");
    }

    @Override
    public String getCommandName() {
        return "sethome";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/sethome" + ChatColor.WHITE + " -- Sets your home location";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        YamlWriter writer;

        Coords coords = new Coords();
        coords.usr    = arg0.getName();
        coords.x      = arg0.getLocation().x;
        coords.y      = arg0.getLocation().y;
        coords.z      = arg0.getLocation().z;

        if(arg1.length > 0)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            PrintWriter hf = null;

            try {
                if(CheckIfUserHaveHome(arg0)) {
                    // TODO: Allow to user to overwrite his home pos
                    arg0.sendMessage(ChatColor.GOLD + " ~ You already have a home");
                } else {
                    hf = new PrintWriter(new BufferedWriter(new FileWriter("Config/homes.yml", true)));
                    hf.write("---\n");
                    hf.write("usr: " + coords.usr + "\n");
                    hf.write("x: " + coords.x + "\n");
                    hf.write("y: " + coords.y + "\n");
                    hf.write("z: " + coords.z + "\n");
                    arg0.sendMessage(ChatColor.GRAY + " ~ Home set");
                }

            } catch(Exception e) {
                arg0.sendMessage(ChatColor.RED + " ~ Could not create home.");
                System.out.println(" ~ Could not create home. User -> " + arg0.getName());
            } finally {
                if(hf != null)
                    hf.close();
            }
        }
    }

    public boolean CheckIfUserHaveHome(MC_Player arg0) {
        YamlReader reader;
        boolean yes = false;

        try {
            reader = new YamlReader(new FileReader("Config/homes.yml"));

            while(true) {
                try {
                    Map user = (Map)reader.read();

                    if(user.get("usr").equals(arg0.getName())) {
                        yes = true;
                        break;
                    } else
                        continue;
                }catch(Exception e) {
                    System.out.println("Error while reading homes.yml");
                    System.out.println(e);
                    return false;
                }
            }

            reader.close();
        } catch(Exception e) {
            System.out.println(e);
            System.out.println(" [*] There was an error during Config/homes.yml reading... Please check Config/homes.yml syntax.");
        }

        return yes;
    }

    @Override
    public boolean hasPermissionToUse(MC_Player arg0) {
        boolean perm = false;

        if(arg0 == null)
            System.out.println("You must be a player to do that !");
        else
            perm = true;

        return perm;
    }
}
