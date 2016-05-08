package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Broadcast implements MC_Command {
    public Broadcast() {

    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("bcast");
    }

    @Override
    public String getCommandName() {
        return "broadcast";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/broadcast <message>" + ChatColor.WHITE + " -- Broadcast to all players";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if (arg1.length < 1)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            String msg = " ";

            for (int i = 0; i < arg1.length; i++)
                msg = msg + " " + arg1[i];

            for(int i = 0; i < MyPlugin.server.getPlayers().size(); i++) {
                MyPlugin.server.getPlayers().get(i).sendMessage(ChatColor.DARK_RED + "[Broadcast] " + ChatColor.GREEN + msg);
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
                if (arg0.hasPermission("essentials.broadcast.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
