package wtf.pathos.antiswear.commands.subcommands;

import org.bukkit.command.CommandSender;
import wtf.pathos.antiswear.AntiSwearPlugin;
import wtf.pathos.antiswear.commands.SubCommand;
import wtf.pathos.antiswear.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

public class ReloadSubCommand implements SubCommand {
    private final AntiSwearPlugin plugin;

    public ReloadSubCommand(AntiSwearPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        plugin.getConfigManager().loadConfig();
        plugin.getWordListManager().loadWordList();
        MessageUtils.sendMessage(sender,"AntiSwear configuration reloaded.");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public String getPermission() {
        return "antiswear.reload";
    }
}