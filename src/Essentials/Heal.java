package Essentials;

import PluginReference.*;

import java.util.List;

public class Heal implements MC_Command {
    public Heal() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "heal";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/heal <player>" + ChatColor.WHITE + " -- Heal a player";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if(arg1.length == 0) {
            arg0.sendMessage(ChatColor.GOLD + " ~ You have been healed");
            arg0.setHealth(100);
        }

        if(arg1.length > 1)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

            if(arg0.hasPermission("essentials.heal.other")) {
                if(other_player != null) {
                    other_player.sendMessage(ChatColor.GOLD + " ~ You have been healed");
                    other_player.setHealth(100);
                } else
                    arg0.sendMessage(ChatColor.RED + " ~ Player <" + arg1[0] + "> not found");
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
                if (arg0.hasPermission("essentials.heal.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}