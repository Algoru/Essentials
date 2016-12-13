package Essentials;


import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Location;
import PluginReference.MC_Player;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class Setspawn implements MC_Command {
    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "setspawn";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/setspawn" + ChatColor.WHITE + " -- Sets spawn at your actual position";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if (arg1.length > 0)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            Coords cords = new Coords();
            cords.x = arg0.getLocation().x;
            cords.y = arg0.getLocation().y;
            cords.z = arg0.getLocation().z;

            YamlWriter writer;

            File fold = new File(MyPlugin.SpawnFile);
            File fnew = new File(MyPlugin.SpawnFile);

            FileWriter sfw = null;

            try {
                fold.delete();

                sfw = new FileWriter(fnew, false);

                sfw.write("x: " + cords.x + "\n");
                sfw.write("y: " + cords.y + "\n");
                sfw.write("z: " + cords.z + "\n");
            } catch (Exception e) {
                arg0.sendMessage(ChatColor.RED + " ~ Could not set spawn.");
                System.out.println(" ~ Could not set spawn. User -> " + arg0.getName() + "\n" + "E: " + e);
            } finally {
                if (sfw != null) {
                    try {
                        sfw.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                arg0.sendMessage(ChatColor.GRAY + " ~ Spawn set");
            }
        }
    }

    @Override
    public boolean hasPermissionToUse(MC_Player arg0) {
        boolean perm = true;

        if(arg0 == null)
            perm = false;
        else
            if (!arg0.isOp())
                perm = false;

        return perm;
    }
}
