package ca.bestbuy.zephyrtestautomation.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GPTResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<GPTChoice> choices;
    private Usage usage;
    private String system_fingerprint;

    public List<GPTChoice> getChoices() {
        return choices;
    }

    @Data
    public static class GPTChoice {
        private int index;
        private ChatMessage message;
        private Object logprobs;
        private String finish_reason;

        public ChatMessage getMessage() {
            return message;
        }
    }

    @Data
    public static class ChatMessage {
        private String role;
        private String content;

        public String getContent() {
            return content;
        }
    }

    @Data
    public static class Usage {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;
    }
}
