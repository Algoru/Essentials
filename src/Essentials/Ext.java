package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Ext implements MC_Command {
    public Ext() {

    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("extinguish");
    }

    @Override
    public String getCommandName() {
        return "ext";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/ext <player>" + ChatColor.WHITE + " -- Extinguish a player";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if(arg1.length < 1 || arg1.length > 1) {
            if(arg0 == null)
                System.out.println(this.getHelpLine(arg0));
            else
                arg0.sendMessage(this.getHelpLine(arg0));
        } else {
            if(arg1.length == 1) {
                MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

                if(other_player != null) {
                    if(other_player.getFireTicks() > 0)
                        other_player.setFireTicks(0);
                    else
                        if(arg0 == null)
                            System.out.println(" ~ This player is not on fire");
                        else
                            arg0.sendMessage(ChatColor.RED + " ~ " + ChatColor.GOLD + "This player is not on fire");
                } else
                if(arg0 == null)
                    System.out.println(" ~ Player <" + arg1[0] + "> not found");
                else
                    arg0.sendMessage(ChatColor.RED + " ~ Player <" + arg1[0] + "> not found");
            }
        }
    }

    @Override
    public boolean hasPermissionToUse(MC_Player arg0) {
        boolean perm = false;

        if (arg0 == null) {
            perm = true;
        } else {
            if (arg0.isOp()) {
                perm = true;
            } else {
                if (arg0.hasPermission("essentials.ext.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
