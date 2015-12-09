package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Nick implements MC_Command {
    public Nick() {

    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("nickname");
    }

    @Override
    public String getCommandName() {
        return "nick";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/nick <player> <nick|off>" + ChatColor.WHITE + " -- Change your display name";
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
            if(arg0.hasPermission("essentials.nickname.other")) {
                switch(arg1.length) {
                    case 1:
                        if(arg1[0].equals("off")) {
                            arg0.setCustomName(arg0.getName());
                            arg0.sendMessage(ChatColor.AQUA + " ~ Your nickname has been restored");
                        } else {
                            arg0.setCustomName(arg1[0]);
                            arg0.sendMessage(ChatColor.AQUA + " ~ You've changed your name to " + ChatColor.DARK_RED + arg1[0]);
                        }
                        break;
                    case 2:
                        MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

                        if(arg1[0].equals(other_player.getName())) {
                            if(other_player != null) {
                                if(arg1[1].equals("off")) {
                                    other_player.setCustomName(other_player.getName());
                                    arg0.sendMessage(ChatColor.AQUA + " ~ Your nickname has been restored");
                                } else {
                                    other_player.setCustomName(arg1[1]);
                                    other_player.sendMessage(ChatColor.AQUA + " ~ Your nickname has been changed to " + ChatColor.DARK_RED + other_player.getCustomName());
                                }
                            } else
                                arg0.sendMessage(ChatColor.RED + " ~ Player <" + arg1[0] + "> not found");
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
            perm = true;
        } else {
            if (arg0.isOp()) {
                perm = true;
            } else {
                if (arg0.hasPermission("essentials.nickname.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
