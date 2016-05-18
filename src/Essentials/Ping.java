package Essentials;



import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Player;

import java.util.Arrays;
import java.util.List;


public class Ping implements MC_Command {
    @Override
    public List<String> getAliases() {
        return Arrays.asList("pong", "echo");
    }

    @Override
    public String getCommandName() {
        return "ping";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/ping [message]" + ChatColor.WHITE + " -- Get a PONG!";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if (arg1.length > 1) {
            String str = "";

            for(int i = 0; i < arg1.length; i++)
                str = str + " " + arg1[i];

            arg0.sendMessage(str);
        } else
            arg0.sendMessage("PONG!");
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
                if (arg0.hasPermission("essentials.ping.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
