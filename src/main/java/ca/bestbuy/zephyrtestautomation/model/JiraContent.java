package ca.bestbuy.zephyrtestautomation.model;


import lombok.Data;
@Data
public class JiraContent {
    private String jiraPrompt;

    public String getJiraPrompt() {
        return jiraPrompt;
    }

}
