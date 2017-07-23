package Essentials;

import PluginReference.*;

import java.util.Arrays;
import java.util.List;

public class Vanish implements MC_Command {
    @Override
    public List<String> getAliases() {
        return Arrays.asList("v");
    }

    @Override
    public String getCommandName() {
        return "vanish";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/vanish [player] [on|off]" + ChatColor.WHITE + " -- Hide yourself from other players";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player mc_player, String[] args) {
        boolean inState = mc_player.isInvisible();
        String state = inState ? "visible" : "invisible";
        MC_Player otherPlayer = null;

        switch (args.length) {
            case 0:
                mc_player.setInvisible(!inState);
                mc_player.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.RED + state);
                break;
            case 1:
                otherPlayer = MyPlugin.server.getOnlinePlayerByName(args[0]);
                if (otherPlayer != null) {
                    otherPlayer.setInvisible(!inState);
                    otherPlayer.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.RED + state);
                } else
                    mc_player.sendMessage(ChatColor.DARK_RED + "~ Player <" + args[0] + "> not found");
                break;
            case 2:
                otherPlayer = MyPlugin.server.getOnlinePlayerByName(args[0]);
                if (otherPlayer != null) {
                    if (args[1].equals("on")) {
                        otherPlayer.setInvisible(true);
                        otherPlayer.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.RED + "invisible");
                    } else if (args[1].equals("off")) {
                        otherPlayer.setInvisible(false);
                        otherPlayer.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.RED + "visible");
                    } else
                        mc_player.sendMessage(this.getHelpLine(mc_player));
                }
                break;
            default:
                mc_player.sendMessage(this.getHelpLine(mc_player));
        }
    }

    @Override
    public boolean hasPermissionToUse(MC_Player arg0) {
        boolean perm = false;
        if (arg0 == null)
            return perm;
        else {
            if (arg0.isOp() || arg0.hasPermission("essentials.vanish.use") || arg0.hasPermission("essentials.vanish.others"))
                perm = true;
        }

        return perm;
    }
}
