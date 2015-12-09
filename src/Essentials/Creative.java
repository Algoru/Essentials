package Essentials;

import PluginReference.*;

import java.util.List;

public class Creative implements MC_Command {
    public Creative() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "creative";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/creative <player>" + ChatColor.WHITE + " -- Changes gamemode to creative";
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
            arg0.setGameMode(MC_GameMode.CREATIVE);
            arg0.sendMessage(ChatColor.GOLD + " ~ Game mode changed to " + ChatColor.AQUA + "creative");
        }

        if(arg1.length > 1)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

            if(arg0.hasPermission("e_cm.other")) {
                if(other_player != null) {
                    other_player.setGameMode(MC_GameMode.CREATIVE);
                    other_player.sendMessage(ChatColor.GOLD + " ~ Game mode changed to " + ChatColor.AQUA + "creative");
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
                if (arg0.hasPermission("e_cm.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
