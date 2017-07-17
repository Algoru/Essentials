package Essentials;


import PluginReference.*;

import java.util.List;

public class Night implements MC_Command {
    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "night";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/night" + ChatColor.WHITE + " -- It was all dark";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if (arg1.length > 1)
            arg0.sendMessage(this.getHelpLine(arg0));
        else
            MyPlugin.server.executeCommand("time set night");
    }

    @Override
    public boolean hasPermissionToUse(MC_Player arg0) {
        return arg0 == null || arg0.isOp() || arg0.hasPermission("essentials.night.use") ? true : false;
    }
}
