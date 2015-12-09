package Essentials;

import PluginReference.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class More implements MC_Command {
    public More() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "more";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/more" + ChatColor.WHITE + " -- Gives you a stack of item that you have in your hand";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if(arg1.length == 0)
            arg0.getItemInHand().setCount(64);
        else
            arg0.sendMessage(this.getHelpLine(arg0));
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
                if (arg0.hasPermission("e_more.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}