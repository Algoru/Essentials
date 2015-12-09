package Essentials;

import PluginReference.*;

import java.awt.image.ColorConvertOp;
import java.util.List;

public class ClearChat implements MC_Command {
    public ClearChat() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "clearchat";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/clearchat <player>" + ChatColor.WHITE + " -- Clean chat.";
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
            arg0.sendMessage(ChatColor.GOLD + " ~ Chat cleaned");
            for(int i = 0; i <= 18; i++)
                arg0.sendMessage(" ");
        }

        if(arg1.length > 1)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

            if(arg0.hasPermission("e_ct.other")) {
                if(other_player != null) {
                    other_player.sendMessage(ChatColor.GOLD + " ~ Chat cleaned");
                    for(int i = 0; i <= 18; i++)
                        other_player.sendMessage(" ");
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
            perm = true;
        }

        return perm;
    }
}