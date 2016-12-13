package Essentials;


import PluginReference.*;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class Day implements MC_Command {
    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "day";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/day" + ChatColor.WHITE + " -- The light was made";
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
            MyPlugin.server.executeCommand("time set 0");
    }

    @Override
    public boolean hasPermissionToUse(MC_Player arg0) {
        return arg0.isOp() || arg0 == null ? true : false;
    }
}
