package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Skull implements MC_Command {
    public Skull() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "skull";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.BLUE + "[USE] " + ChatColor.GOLD + "/skull <owner>";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if(arg1.length == 0 || arg1.length > 1)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            MC_ItemStack new_skull = MyPlugin.server.createItemStack(MC_ItemType.SKELETON_SKULL, 1, 3);
            new_skull.setSkullOwner(arg1[0]);
            arg0.getWorld().dropItem(new_skull, arg0.getLocation(), arg0.getName());
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
                if (arg0.hasPermission("e_spawnskull.use")) {
                    perm = true;
                }
            }
        }

        return perm;
    }
}
