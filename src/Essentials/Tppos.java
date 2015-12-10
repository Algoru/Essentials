package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Tppos implements MC_Command {
    public Tppos() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "tppos";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/tppos [player] <x> <y> <z>" + ChatColor.WHITE + " -- Teleports you directly to a location";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if(arg1.length < 3 || arg1.length > 4)
            if(arg0 == null)
                System.out.println(this.getHelpLine(arg0));
            else
                arg0.sendMessage(this.getHelpLine(arg0));
        else {
            if(arg1.length == 3) {
                MC_Location newLocation = new MC_Location(Double.parseDouble(arg1[0]), Double.parseDouble(arg1[1]), Double.parseDouble(arg1[2]), arg0.getWorld().getDimension());
                arg0.sendMessage(ChatColor.GOLD + "Teleporting to " + ChatColor.DARK_RED + arg1[0] + " " + arg1[1] + " " + arg1[2]);
                arg0.teleport(newLocation);
            } else if(arg1.length == 4) {
                MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

                if(other_player != null) {
                    MC_Location newLocation = new MC_Location(Double.parseDouble(arg1[1]), Double.parseDouble(arg1[2]), Double.parseDouble(arg1[3]), other_player.getWorld().getDimension());
                    other_player.sendMessage(ChatColor.GOLD + "Teleporting to " + ChatColor.DARK_RED + arg1[1] + " " + arg1[2] + " " + arg1[3]);
                    other_player.teleport(newLocation);
                } else {
                    if(arg0 == null)
                        System.out.println(" ~ Player <" + arg1[0] + "> not found");
                    else
                        arg0.sendMessage(ChatColor.RED + " ~ Player <" + arg1[0] + "> not found");
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
                if (arg0.hasPermission("essentials.tppos.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
