package wtf.pathos.antiswear.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import wtf.pathos.antiswear.AntiSwearPlugin;
import wtf.pathos.antiswear.models.FilterResult;
import wtf.pathos.antiswear.services.SwearFilter;
import wtf.pathos.antiswear.utils.PermissionUtils;

public class ChatListener implements Listener {
    private final AntiSwearPlugin plugin;

    public ChatListener(AntiSwearPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (PermissionUtils.hasPermission(player, "antiswear.bypass")) {
            return;
        }

        SwearFilter swearFilter = plugin.getSwearFilter();
        FilterResult result = swearFilter.filterMessage(event.getMessage());

        if (result.containsProfanity()) {
            event.setMessage(result.getFilteredMessage());

            if (plugin.getConfigManager().isLogEnabled()) {
                plugin.getLogger().info(player.getName() + " tried to use profanity: " + result.getOriginalMessage());
            }
        }
    }
}