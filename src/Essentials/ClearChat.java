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
        if(arg1.length > 1)
            if(arg0 == null)
                System.out.println(this.getHelpLine(arg0));
            else
                arg0.sendMessage(this.getHelpLine(arg0));
        else if(arg1.length == 0) {
            if(arg0 == null)
                System.out.println(" ~ You must be a player to do that !");
            else {
                arg0.sendMessage(ChatColor.GOLD + " ~ Chat cleaned");
                for(int i = 0; i <= 18; i++)
                    arg0.sendMessage(" ");
            }
        } else {
            if(arg1.length == 1) {
                MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);
                // TODO: This doesn't works good in the terminal... It doesn't regocnized <player> parameter
                if(arg0.hasPermission("essentials.clearchat.other")) {
                    if(other_player != null) {
                        other_player.sendMessage(ChatColor.GOLD + " ~ Chat cleaned");
                        for(int i = 0; i < 18; i++)
                            other_player.sendMessage(" ");
                    } else {
                        if(arg0 == null)
                            System.out.println(" ~ Player <" + arg1[0] + "> not found");
                        else
                            arg0.sendMessage(ChatColor.RED + " ~ Player <" + arg1[0] + "> not found");
                    }
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
                if (arg0.hasPermission("essentials.clearchat.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}