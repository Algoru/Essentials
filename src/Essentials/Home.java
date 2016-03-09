package Essentials;


import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Location;
import PluginReference.MC_Player;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.util.List;
import java.util.Map;

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
        return ChatColor.GOLD + "/home" + ChatColor.WHITE + " -- Teleports you to the location of where you last used the /sethome command";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        YamlReader reader;

        if(arg1.length > 0)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            try {
                reader = new YamlReader(new FileReader("Config/homes.yml"));

                String userName = " ";
                double x = 0.0, y = 0.0, z = 0.0;

                while(true) {
                    try {
                        Map user = (Map)reader.read();

                        if(user.get("usr").equals(arg0.getName())) {
                            userName = (String)user.get("usr");
                            x = new Double((String)user.get("x"));
                            y = new Double((String)user.get("y"));
                            z = new Double((String)user.get("z"));
                            break;
                        } else
                            continue;
                    }catch(Exception e) {

                    }
                }

                if(userName.equals(" "))
                    arg0.sendMessage(ChatColor.RED + " ~ You don't have a home");
                else {
                    MC_Location newLocation = new MC_Location(x, y, z, arg0.getWorld().getDimension());
                    arg0.sendMessage(ChatColor.GOLD + " ~ Teleporting to " + ChatColor.RED + "home");
                    arg0.teleport(newLocation);
                }

                reader.close();
            } catch(Exception e) {
                System.out.println(e);
                System.out.println(" [*] There was an error during Config/homes.yml reading... Please check Config/homes.yml syntax.");
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
