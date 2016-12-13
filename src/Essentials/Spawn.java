package Essentials;


import PluginReference.ChatColor;
import PluginReference.MC_Command;
import PluginReference.MC_Location;
import PluginReference.MC_Player;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class Spawn implements MC_Command {
    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "spawn";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/spawn [player]" + ChatColor.WHITE + " -- Teleports to spawn";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    private void goSpawn(MC_Player player, boolean teleportedBy) {
        YamlReader reader;
        double x = 0.0, y = 0.0, z = 0.0;

        try {
            reader          = new YamlReader(new FileReader(MyPlugin.SpawnFile));

            Map spawnCoords = (Map)reader.read();

            x = new Double((String)spawnCoords.get("x"));
            y = new Double((String)spawnCoords.get("y"));
            z = new Double((String)spawnCoords.get("z"));

            System.out.println("x: " + x + "\ny:" + y + "\nz: " + z);
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + " ~ Could not get spawn location");
        }

        String tlpMsg = ChatColor.GOLD + " ~ Teleporing to spawn";

        if (teleportedBy)
            tlpMsg = ChatColor.GOLD + " ~ " + player.getName() + " has teleported you to spawn";

        MC_Location newLocation = new MC_Location(x, y, z, player.getWorld().getDimension());
        player.sendMessage(tlpMsg);
        player.teleport(newLocation);
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if (arg1.length > 0 && !arg0.isOp())
            arg0.sendMessage(ChatColor.RED + " ~ You cannot do that !");
        else if (arg1.length > 1)
            arg0.sendMessage(this.getHelpLine(arg0));
        else {
            if (arg1.length == 0)
                goSpawn(arg0, false);
            else {
                if (arg0.hasPermission("essentials.spawn.other")) {
                    MC_Player other_player = MyPlugin.server.getOnlinePlayerByName(arg1[0]);

                    if (other_player != null)
                        goSpawn(other_player, true);
                    else
                        arg0.sendMessage(ChatColor.RED + " ~ Player <" + arg1[0] + "> not found");
                }
            }
        }
    }

    @Override
    public boolean hasPermissionToUse(MC_Player arg0) {
        boolean perm = true;

        if (arg0 == null)
            perm = false;

        return perm;
    }
}
