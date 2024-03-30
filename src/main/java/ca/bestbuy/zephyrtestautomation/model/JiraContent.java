package ca.bestbuy.zephyrtestautomation.model;


import lombok.Data;
@Data
public class JiraContent {
    private String jiraDescription;

    public String getJiraDescription() {
        return jiraDescription;
    }

}
