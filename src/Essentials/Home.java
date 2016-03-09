package Essentials;


import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Player;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.util.List;

public class Home implements MC_Command {
    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "home";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/home [name]" + ChatColor.WHITE + " -- Teleports you to the location of where you last used the /sethome command";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if(arg1.length > 1)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            try {
                YamlReader reader = new YamlReader(new FileReader("Config/homes.yml"));
                reader.close();
            } catch(Exception e) {
                System.out.println(" [*] There was an error during homes.yml reading... Please restart server.");
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
