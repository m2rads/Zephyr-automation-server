package ca.bestbuy.zephyrtestautomation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GPTRequest {
    private final String model = "gpt-3.5-turbo-0125";
    private String prompt;
    private final int temprature = 1;
    @JsonProperty(value = "max_tokens")
    private int maxTokens = 500;

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
