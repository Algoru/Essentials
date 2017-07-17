package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Item implements MC_Command {
    @Override
    public List<String> getAliases() {
        return Arrays.asList("i");
    }

    @Override
    public String getCommandName() {
        return "item";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/item <item|item:damage value> [amount]" + ChatColor.WHITE + " -- Gives yourself a specified item";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        return null;
    }

    @Override
    public void handleCommand(MC_Player mc_player, String[] args) {
        String itemID;
        int amount = 64;

        switch (args.length) {
            case 0:
                mc_player.sendMessage(this.getHelpLine(mc_player));
                break;
            case 1:
                itemID = args[0];
                break;
            case 2:
                itemID = args[0];
                amount = Integer.parseInt(args[1]);
                break;
            case 3:
                break;
            default:
                mc_player.sendMessage(this.getHelpLine(mc_player));
                break;
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
                if (arg0.hasPermission("essentials.item.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}