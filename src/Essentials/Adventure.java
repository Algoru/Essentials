package Essentials;

import PluginReference.*;

import java.util.List;

public class Adventure implements MC_Command {
    public Adventure() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "adventure";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.BLUE + "[USE] " + ChatColor.GOLD + "/adventure <player>";
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
            arg0.setGameMode(MC_GameMode.SPECTATOR);
            arg0.sendMessage(ChatColor.GOLD + " ~ Game mode changed to " + ChatColor.GREEN + "adventure");
        }

        if(arg1.length > 1)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

            if(arg0.hasPermission("e_am.other")) {
                if(other_player != null) {
                    other_player.setGameMode(MC_GameMode.SPECTATOR);
                    other_player.sendMessage(ChatColor.GOLD + " ~ Game mode changed to " + ChatColor.GREEN + "adventure");
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
                if (arg0.hasPermission("e_am.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}