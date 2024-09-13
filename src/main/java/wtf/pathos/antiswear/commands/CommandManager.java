package wtf.pathos.antiswear.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import wtf.pathos.antiswear.AntiSwearPlugin;
import wtf.pathos.antiswear.commands.subcommands.AddWordSubCommand;
import wtf.pathos.antiswear.commands.subcommands.ReloadSubCommand;
import wtf.pathos.antiswear.commands.subcommands.RemoveWordSubCommand;
import wtf.pathos.antiswear.utils.MessageUtils;
import wtf.pathos.antiswear.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager implements CommandExecutor, TabCompleter {
    private final AntiSwearPlugin plugin;
    private final Map<String, SubCommand> subCommands = new HashMap<>();

    public CommandManager(AntiSwearPlugin plugin) {
        this.plugin = plugin;
        registerSubCommands();
    }

    private void registerSubCommands() {
        subCommands.put("reload", new ReloadSubCommand(plugin));
        subCommands.put("add", new AddWordSubCommand(plugin));
        subCommands.put("remove", new RemoveWordSubCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            MessageUtils.sendMessage(sender,"Usage: /antiswear <subcommand> [args]");
            return true;
        }

        String subCommandName = args[0].toLowerCase();
        SubCommand subCommand = subCommands.get(subCommandName);

        if (subCommand == null) {
            MessageUtils.sendMessage(sender,"Unknown subcommand. Use /antiswear for help.");
            return true;
        }

        if (!PermissionUtils.hasPermission(sender, subCommand.getPermission())) {
            MessageUtils.sendMessage(sender, "You don't have permission to use this command.");
            return true;
        }

        String[] subCommandArgs = new String[args.length - 1];
        System.arraycopy(args, 1, subCommandArgs, 0, args.length - 1);

        return subCommand.execute(sender, subCommandArgs);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>(subCommands.keySet());
            completions.removeIf(subCmd -> !sender.hasPermission(subCommands.get(subCmd).getPermission()));
            return completions;
        }

        if (args.length > 1) {
            SubCommand subCommand = subCommands.get(args[0].toLowerCase());
            if (subCommand != null) {
                return subCommand.tabComplete(sender, args);
            }
        }

        return new ArrayList<>();
    }
}