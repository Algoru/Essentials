package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Burn implements MC_Command {
    public Burn() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "burn";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/burn <player> <seconds>" + ChatColor.WHITE + " -- Sets a player on fire";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if(arg1.length < 2 || arg1.length > 2) {
            if(arg0 == null)
                System.out.println(this.getHelpLine(arg0));
            else
                arg0.sendMessage(this.getHelpLine(arg0));
        } else {
            if(arg1.length == 2) {
                MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

                if(other_player != null) {
                    // TODO: Those aren't exactly seconds...
                    other_player.setFireTicks((Integer.parseInt(arg1[1]) * 60) / 10);
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
                if (arg0.hasPermission("essentials.burn.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
