package wtf.pathos.antiswear.managers;

import org.bukkit.configuration.file.FileConfiguration;
import wtf.pathos.antiswear.AntiSwearPlugin;
import wtf.pathos.antiswear.annotations.ConfigValue;
import wtf.pathos.antiswear.processors.ConfigProcessor;

public class ConfigManager {
    private final AntiSwearPlugin plugin;

    @ConfigValue(path = "censor-character", defaultValue = "*")
    private String censorCharacter;

    @ConfigValue(path = "enable-logging", defaultValue = "true")
    private boolean logEnabled;

    @ConfigValue(path = "enable-regex", defaultValue = "true")
    private boolean regexEnabled;

    @ConfigValue(path = "openai-api-key", defaultValue = "")
    private String openAiKey;

    public ConfigManager(AntiSwearPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        ConfigProcessor.processConfig(this, config);
    }

    // Getters
    public String getCensorCharacter() {
        return censorCharacter;
    }

    public boolean isLogEnabled() {
        return logEnabled;
    }

    public boolean isRegexEnabled() {
        return regexEnabled;
    }

    public String getOpenAiKey() {return openAiKey;}
}