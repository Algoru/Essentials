package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Tpall implements MC_Command {
    public Tpall() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "tpall";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/tpall [player]" + ChatColor.WHITE + " -- Requests all players online to teleport to you";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if (arg1.length > 1)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            if(arg1.length == 1) {
                MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

                if(other_player != null) {
                    if(arg0 == null)
                        System.out.println(" ~ You must be a player to do that !");
                    else {
                        other_player.teleport(arg0.getLocation());
                        other_player.sendMessage(ChatColor.GOLD + " ~ " + ChatColor.DARK_RED + arg0.getName() + ChatColor.GOLD + " has teleported you to him");
                    }
                } else {
                    if(arg0 == null)
                        System.out.println(" ~ Player <" + arg1[0] + "> not found");
                    else
                        arg0.sendMessage(ChatColor.RED + " ~ Player <" + arg1[0] + "> not found");
                }
            } else {
                for (int i = 0; i < MyPlugin.server.getPlayers().size(); i++) {
                    MyPlugin.server.getPlayers().get(i).teleport(arg0.getLocation());
                    MyPlugin.server.getPlayers().get(i).sendMessage(ChatColor.GOLD + " ~ " + ChatColor.DARK_RED + arg0.getName() + ChatColor.GOLD + " has teleported you to him");
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
                if (arg0.hasPermission("essentials.tpall.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
