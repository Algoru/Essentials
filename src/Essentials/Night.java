package Essentials;


import PluginReference.*;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.util.List;
import java.util.Map;

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
            MyPlugin.server.executeCommand("time set 13500");
    }

    @Override
    public boolean hasPermissionToUse(MC_Player arg0) {
        return arg0.isOp() || arg0 == null ? true : false;
    }
}
