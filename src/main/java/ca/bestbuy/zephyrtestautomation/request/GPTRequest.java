package ca.bestbuy.zephyrtestautomation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GPTRequest {
    @JsonProperty("model")
    private String model;

    @JsonProperty("messages")
    private List<ChatMessage> messages;

    @JsonProperty("temperature")
    private double temperature;

    public void setModel(String s) {
        this.model = s;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public void setTemperature(double v) {
        this.temperature = v;
    }

    @Data
    public static class ChatMessage {
        @JsonProperty("role")
        private String role;

        @JsonProperty("content")
        private String content;

        public ChatMessage(String system, String s) {
            this.role = system;
            this.content = s;
        }
    }
}
