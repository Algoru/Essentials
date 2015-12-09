package Essentials;

import PluginReference.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Rules implements MC_Command {
    public Rules() {

    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCommandName() {
        return "rules";
    }

    @Override
    public String getHelpLine(MC_Player arg0) {
        return ChatColor.GOLD + "/rules" + ChatColor.WHITE + " -- Displays the rules";
    }

    @Override
    public List<String> getTabCompletionList(MC_Player arg0, String[] arg1) {
        if (arg1.length == 1)
            return MyPlugin.server.getMatchingOnlinePlayerNames(arg1[0]);

        return null;
    }

    @Override
    public void handleCommand(MC_Player arg0, String[] arg1) {
        if(arg1.length > 0) {
            arg0.sendMessage(this.getHelpLine(arg0));
        } else {
            BufferedReader r_file = null;

            try {
                String Currentline;
                r_file = new BufferedReader(new FileReader("rules.txt"));

                arg0.sendMessage(ChatColor.GOLD + "======================= RULES =======================");

                while((Currentline = r_file.readLine()) != null) {
                    arg0.sendMessage(ChatColor.WHITE + Currentline);
                }

                arg0.sendMessage(ChatColor.GOLD + "=====================================================");

            } catch(Exception e) {
                arg0.sendMessage(ChatColor.RED + " ~ rules.txt not found");
            } finally {
                try {
                    if(r_file != null)
                        r_file.close();
                } catch(Exception e) {
                    System.out.println(" [*] Could not close rules.txt");
                }
            }

        }
    }

    @Override
    public boolean hasPermissionToUse(MC_Player arg0) {
        boolean perm = false;

        if (arg0 == null)
            perm = false;
        else
            perm = true;

        return perm;
    }
}