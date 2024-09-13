package wtf.pathos.antiswear.models;

public class FilterResult {
    private final String originalMessage;
    private final String filteredMessage;
    private final boolean containsProfanity;

    public FilterResult(String originalMessage, String filteredMessage, boolean containsProfanity) {
        this.originalMessage = originalMessage;
        this.filteredMessage = filteredMessage;
        this.containsProfanity = containsProfanity;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public String getFilteredMessage() {
        return filteredMessage;
    }

    public boolean containsProfanity() {
        return containsProfanity;
    }
}