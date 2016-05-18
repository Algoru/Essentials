package Essentials;

import PluginReference.ChatColor;

public class Utils {
    public void SendMessageToAllServer(String msg) {
        for(int i = 0; i < MyPlugin.server.getPlayers().size(); i++) {
            MyPlugin.server.getPlayers().get(i).sendMessage(msg);
        }
    }
}
