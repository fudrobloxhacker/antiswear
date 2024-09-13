package wtf.pathos.antiswear.services;

import wtf.pathos.antiswear.AntiSwearPlugin;
import wtf.pathos.antiswear.models.FilterResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwearFilter {
    private final AntiSwearPlugin plugin;
    private final OpenAIFilter openAIFilter;

    public SwearFilter(AntiSwearPlugin plugin) {
        this.plugin = plugin;
        String openAiKey = plugin.getConfigManager().getOpenAiKey();
        this.openAIFilter = openAiKey != null && !openAiKey.isEmpty() ? new OpenAIFilter(openAiKey) : null;
    }

    public FilterResult filterMessage(String message) {
        String filteredMessage = message;
        boolean containsProfanity = false;

        for (String word : plugin.getWordListManager().getBannedWords()) {
            if (containsWordIgnoreCase(filteredMessage, word)) {
                filteredMessage = replaceWordIgnoreCase(filteredMessage, word, getCensoredWord(word));
                containsProfanity = true;
            }
        }

        if (plugin.getConfigManager().isRegexEnabled()) {
            for (String regex : plugin.getWordListManager().getRegexPatterns()) {
                Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(filteredMessage);
                if (matcher.find()) {
                    filteredMessage = matcher.replaceAll(match -> getCensoredWord(match.group()));
                    containsProfanity = true;
                }
            }
        }

        if (!containsProfanity && openAIFilter != null) {
            containsProfanity = openAIFilter.containsProfanity(message);
            if (containsProfanity) {
                filteredMessage = getCensoredWord(message);
            }
        }

        return new FilterResult(message, filteredMessage, containsProfanity);
    }

    private String getCensoredWord(String word) {
        return plugin.getConfigManager().getCensorCharacter().repeat(word.length());
    }

    private boolean containsWordIgnoreCase(String message, String word) {
        return message.toLowerCase().contains(word.toLowerCase());
    }

    private String replaceWordIgnoreCase(String message, String word, String replacement) {
        return message.replaceAll("(?i)" + Pattern.quote(word), replacement);
    }
}