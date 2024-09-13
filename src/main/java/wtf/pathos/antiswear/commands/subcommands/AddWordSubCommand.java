package wtf.pathos.antiswear.commands.subcommands;

import org.bukkit.command.CommandSender;
import wtf.pathos.antiswear.AntiSwearPlugin;
import wtf.pathos.antiswear.commands.SubCommand;
import wtf.pathos.antiswear.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

public class AddWordSubCommand implements SubCommand {
    private final AntiSwearPlugin plugin;

    public AddWordSubCommand(AntiSwearPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            MessageUtils.sendMessage(sender,"Usage: /antiswear add <word>");
            return true;
        }

        String word = args[0].toLowerCase();
        plugin.getWordListManager().addBannedWord(word);
        MessageUtils.sendMessage(sender,"Added '" + word + "' to the banned word list.");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public String getPermission() {
        return "antiswear.add";
    }
}