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
    public void handleCommand(MC_Player mc_player, String[] args) {
        switch (args.length) {
            case 0:
                break;
            case 1:
                break;
            default:
                mc_player.sendMessage(this.getHelpLine(mc_player));
        }
    }

    @Override
    public boolean hasPermissionToUse(MC_Player arg0) {
        boolean perm = false;

        if (arg0 == null)
            perm = false;
        else
            if (arg0.isOp() || arg0.hasPermission("essentials.afk.use") || arg0.hasPermission("essentials.afk.others"))
                perm = true;

        return perm;
    }
}
