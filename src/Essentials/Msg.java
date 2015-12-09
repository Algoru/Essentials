package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Msg implements MC_Command {
    public Msg() {

    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("tell", "m", "t", "whisper");
    }

    @Override
    public String getCommandName() {
        return "msg";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/msg <player> <message>" + ChatColor.WHITE + " -- This allows you to private message another player";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if(arg1.length == 0 || arg1.length > 2)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

            if(other_player != null) {
                other_player.sendMessage(ChatColor.GOLD + "[" + ChatColor.DARK_RED + arg0.getCustomName() + ChatColor.GOLD + " -> " + other_player.getCustomName() + ChatColor.GOLD + "] " + ChatColor.WHITE + arg1[1]);
            } else
                arg0.sendMessage(ChatColor.RED + " ~ Player <" + arg1[0] + "> not found");
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
                if (arg0.hasPermission("essentials.msg.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
