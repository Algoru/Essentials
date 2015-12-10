package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Tphere implements MC_Command {
    public Tphere() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "tphere";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/tphere <player to teleport to YOUR SELF>" + ChatColor.WHITE + " -- Teleport player directly to you";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if(arg1.length < 1 || arg1.length > 1)
            if(arg0 == null)
                System.out.println(this.getHelpLine(arg0));
            else
                arg0.sendMessage(this.getHelpLine(arg0));
        else {
            if(arg1.length == 1) {
                MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

                if(other_player != null) {
                    if(arg0 == null)
                        System.out.println(" ~ You must be a player to do that !");
                    else {
                        MC_Location currentLocation = arg0.getLocation();
                        other_player.teleport(currentLocation);
                        other_player.sendMessage(ChatColor.GOLD + " ~ " + ChatColor.DARK_RED + arg1[0] + ChatColor.GOLD + " has teleported you to him");
                    }
                } else {
                    if(arg0 == null)
                        System.out.println(" ~ Player <" + arg1[0] + "> not found");
                    else
                        arg0.sendMessage(ChatColor.RED + " ~ Player <" + arg1[0] + "> not found");
                }
            }
        }
    }

    @Override
    public boolean hasPermissionToUse(MC_Player arg0) {
        boolean perm = false;

        if (arg0 == null) {
            perm = false;
        } else {
            if (arg0.isOp()) {
                perm = true;
            } else {
                if (arg0.hasPermission("essentials.tphere.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
