package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Kickall implements MC_Command {
    public Kickall() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "kickall";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/kickall <reason>" + ChatColor.WHITE + " -- Kicks all players off the server";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if(arg1.length == 0)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            String reason = "";

            for(int i = 0; i < arg1.length; i++)
                reason = reason + " " + arg1[i];

            for(int i = 0; i < MyPlugin.server.getPlayers().size(); i++) {
                if(!(MyPlugin.server.getPlayers().get(i).isOp()) || !(MyPlugin.server.getPlayers().get(i).hasPermission("essentials.unkickable")))
                    MyPlugin.server.getPlayers().get(i).kick(reason);
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
                if (arg0.hasPermission("essentials.kickall.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
