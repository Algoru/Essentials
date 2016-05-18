package Essentials;



import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Player;

import java.util.Arrays;
import java.util.List;


public class Afk implements MC_Command {
    @Override
    public List<String> getAliases() {
        return Arrays.asList("away");
    }

    @Override
    public String getCommandName() {
        return "afk";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/afk [player]" + ChatColor.WHITE + " -- Toggles your AFK status";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        // TODO: Add an alert when the user is no longer AFK
        Utils u = new Utils();

        if (arg1.length == 0) {
            if (arg0 == null)
                System.out.println(" ~ You must be a player to do that !");
            else {
                u.SendMessageToAllServer(ChatColor.GRAY + arg0.getName() + " is now AFK");
            }
        } else {
            if (arg1.length > 1) {
                if (arg0 == null)
                    System.out.println(this.getHelpLine(arg0));
                else
                    arg0.sendMessage(this.getHelpLine(arg0));
            } else {
                MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

                if (arg0.hasPermission("essentials.afk.other")) {
                    u.SendMessageToAllServer(ChatColor.GRAY + other_player.getName() + " is now AFK");
                } else {
                    if (arg0 == null)
                        System.out.println(" ~ You don't have permission to do that !");
                    else
                        arg0.sendMessage(ChatColor.RED + " ~ You don't have permission to do that !");
                }
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
                if (arg0.hasPermission("essentials.afk.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
