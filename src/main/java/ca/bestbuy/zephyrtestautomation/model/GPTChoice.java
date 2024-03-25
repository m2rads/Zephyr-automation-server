package ca.bestbuy.zephyrtestautomation.model;

import lombok.Data;

@Data
public class GPTChoice {
    private String text;

    public String getText() {
        return text;
    }
}
