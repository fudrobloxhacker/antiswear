package wtf.pathos.antiswear.services;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;

public class OpenAIFilter {
    private final OpenAiService service;

    public OpenAIFilter(String apiKey) {
        this.service = new OpenAiService(apiKey, Duration.ofSeconds(30));
    }

    public boolean containsProfanity(String message) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("Does the following message contain any profanity or inappropriate language? Respond with 'yes' or 'no':\n\n" + message)
                .maxTokens(1)
                .temperature(0.0)
                .build();

        String response = service.createCompletion(completionRequest).getChoices().get(0).getText().trim().toLowerCase();
        return response.equals("yes");
    }
}