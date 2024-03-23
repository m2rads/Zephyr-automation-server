package ca.bestbuy.zephyrtestautomation.response;

import ca.bestbuy.zephyrtestautomation.model.GPTChoice;
import lombok.Data;
import java.util.List;

@Data
public class GPTResponse {
    private List<GPTChoice> choices;

    public List<GPTChoice> getChoices() {
        return choices;
    }
}
