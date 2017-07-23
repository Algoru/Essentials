package Essentials;



import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Player;

import java.util.Arrays;
import java.util.List;


public class Helpop implements MC_Command {
    @Override
    public List<String> getAliases() {
        return Arrays.asList("amsg", "ac");
    }

    @Override
    public String getCommandName() {
        return "helpop";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/helpop <message>" + ChatColor.WHITE + " -- Requests help from online staff";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        return null;
    }

    @Override
    public void handleCommand(MC_Player mc_player, String[] args) {
        if (args.length > 0) {
            String message = "";
            int i = 0;

            for (i = 0; i < args.length; i++)
                message += " " + args[i];

            List<MC_Player> players = MyPlugin.server.getPlayers();
            int foundsOps = 0;

            for (i = 0; i < players.size(); i++) {
                if (players.get(i).isOp()) {
                    foundsOps++;
                    players.get(i).sendMessage(ChatColor.DARK_RED + "[HelpOp] " + mc_player.getName() + ChatColor.RESET + ": " + message);
                }
            }

            if (foundsOps < 1)
                mc_player.sendMessage(ChatColor.RED + "Sorry, there is no admin online");
        } else
            mc_player.sendMessage(this.getHelpLine(mc_player));
    }

    @Override
    public boolean hasPermissionToUse(MC_Player arg0) {
        boolean perm = false;

        if (arg0 == null)
            perm = false;
        else
            if (arg0.isOp() || arg0.hasPermission("essentials.helpop.use"))
                perm = true;

        return perm;
    }
}
