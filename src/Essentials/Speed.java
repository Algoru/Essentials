package Essentials;


import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Player;

import java.util.Arrays;
import java.util.List;

public class Speed implements MC_Command {
    @Override
    public List<String> getAliases() {
        return Arrays.asList("flyspeed", "walkspeed", "fspeed", "wspeed");
    }

    @Override
    public String getCommandName() {
        return "speed";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/speed <walk|fly> <speed> [player]" + ChatColor.WHITE + " -- This command allows you to change your players speed limits";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player mc_player, String[] args) {
        String wof = "";
        float speed = 0.1f;

        if (args.length == 1) {
            speed = Float.parseFloat(args[0]);
            mc_player.setWalkSpeed(speed);
            mc_player.setFlySpeed(speed);
            return;
        }

        MC_Player otherPlayer = null;

        switch (args.length) {
            case 2:
                wof = args[0];
                speed = Float.parseFloat(args[1]);

                switch (wof) {
                    case "walk":
                        mc_player.setWalkSpeed(speed);
                        break;
                    case "fly":
                        mc_player.setFlySpeed(speed);
                        break;
                    default:
                        mc_player.sendMessage(this.getHelpLine(mc_player));
                        break;
                }
                break;
            case 3:
                wof = args[0];
                speed = Float.parseFloat(args[1]);
                otherPlayer = MyPlugin.server.getOnlinePlayerByName(args[2]);
                break;
            default:
                mc_player.sendMessage(this.getHelpLine(mc_player));
                return;
        }

        if (!mc_player.hasPermission("essentials.speed.others")) {
            mc_player.sendMessage(ChatColor.RED + "You don't have permission to do that !");
            return;
        }

        if (otherPlayer != null) {
            switch (wof) {
                case "walk":
                    otherPlayer.setWalkSpeed(speed);
                    break;
                case "fly":
                    otherPlayer.setFlySpeed(speed);
                    break;
                default:
                    mc_player.sendMessage(this.getHelpLine(mc_player));
                    break;
            }
        } else
            mc_player.sendMessage(ChatColor.RED + "Player <" + args[2] + "> not found");
    }

    @Override
    public boolean hasPermissionToUse(MC_Player arg0) {
        boolean perm = false;

        if (arg0 == null)
            perm = false;
        else
            if (arg0.isOp() || arg0.hasPermission("essentials.speed.use"))
                perm = true;

        return perm;
    }
}
