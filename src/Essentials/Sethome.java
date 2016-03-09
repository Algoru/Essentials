package Essentials;

import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Player;
import com.esotericsoftware.yamlbeans.YamlWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
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
            try {
                writer = new YamlWriter(new FileWriter("Config/homes.yml"));
                writer.write(coords);
                writer.close();

                RandomAccessFile f = new RandomAccessFile(new File("Config/homes.yml"), "rw");
                f.seek(0);
                f.write("#".getBytes());
                f.close();

                arg0.sendMessage(ChatColor.GOLD + " ~ Home set");
            } catch(Exception e) {
                System.out.println(e);
                arg0.sendMessage(ChatColor.RED + " ~ Could not create home");
            }
        }
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
