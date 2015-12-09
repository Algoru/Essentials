package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Realname implements MC_Command {
    public Realname() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "realname";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/realname <player>" + ChatColor.WHITE + " -- Allows you to see a player's real username";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if(arg1.length == 0 || arg1.length > 1)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

            if(arg0.hasPermission("e_realname.other")) {
                if(other_player != null) {
                    if(other_player.hasCustomName())
                        arg0.sendMessage(ChatColor.DARK_RED + other_player.getCustomName() + ChatColor.GOLD + " real name is " + ChatColor.AQUA + other_player.getName());
                    else
                        arg0.sendMessage(ChatColor.DARK_RED + other_player.getName() + ChatColor.GOLD + " real name is " + ChatColor.AQUA + other_player.getName());
                } else
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
                if (arg0.hasPermission("e_realname.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
