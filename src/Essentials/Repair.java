package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Repair implements MC_Command {
    public Repair() {

    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("fix");
    }

    @Override
    public String getCommandName() {
        return "repair";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.BLUE + "[USE] " + ChatColor.GOLD + "/repair [all|hand] <player>";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if(arg1.length > 2)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            if(arg0.hasPermission("e_repair.other")) {
                switch (arg1.length) {
                    case 0:
                        arg0.getItemInHand().setDamage(0);
                        break;
                    case 1:
                        if(arg1[0].equals("hand"))
                            arg0.getItemInHand().setDamage(0);
                        else if(arg1[0].equals("all")) {
                            for(int i = 0; i < arg0.getInventory().size(); i++)
                                arg0.getInventory().get(i).setDamage(0);
                        } else
                            arg0.sendMessage(this.getHelpLine(arg0));
                        break;
                    case 2:
                        MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[1]);

                        if(arg1[0].equals("hand"))
                            other_player.getItemInHand().setDamage(0);
                        else if(arg1[0].equals("all")) {
                            for(int i = 0; i < other_player.getInventory().size(); i++)
                                other_player.getInventory().get(i).setDamage(0);
                        } else
                            arg0.sendMessage(this.getHelpLine(arg0));
                        break;
                    default:
                        arg0.sendMessage(this.getHelpLine(arg0));
                        break;
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
                if (arg0.hasPermission("e_repair.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
