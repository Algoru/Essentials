package Essentials;

import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Player;

import java.util.List;

public class Fly implements MC_Command {
    public Fly() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "fly";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/fly <player>" + ChatColor.WHITE + " -- Allows for creative mode fly";
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
            if(arg0.isAllowedFlight()) {
                arg0.setAllowFlight(false);
                arg0.setFlying(false);
                arg0.sendMessage(ChatColor.GOLD + " ~ Fly disabled");
            } else {
                arg0.setAllowFlight(true);
                arg0.setFlying(true);
                arg0.sendMessage(ChatColor.AQUA + " ~ Fly enabled");
            }
        }

        if(arg1.length > 1)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

            if(arg0.hasPermission("essentials.give_fly.others")) {
                if(other_player != null) {
                    if(other_player.isAllowedFlight()) {
                        arg0.setAllowFlight(false);
                        arg0.setFlying(false);
                        arg0.sendMessage(ChatColor.GOLD + " ~ Fly disabled");
                    } else {
                        arg0.setAllowFlight(true);
                        arg0.setFlying(true);
                        arg0.sendMessage(ChatColor.AQUA + " ~ Fly enabled");
                    }
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
                if (arg0.hasPermission("essentials.fly.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
