package ca.bestbuy.zephyrtestautomation.controller;

import ca.bestbuy.zephyrtestautomation.model.JiraContent;
import ca.bestbuy.zephyrtestautomation.service.GPTService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/gpt")
public class ChatGPTController {

    private final GPTService gptService;

    public ChatGPTController(GPTService gptService){
        this.gptService = gptService;
    }

    @PostMapping("/test-step")
    public Map<String, Object> testStep(@RequestBody Map<String, String> requestBody) {
        System.out.println(requestBody.get("jiraPrompt"));
        return gptService.generateGPTTestStep(requestBody);
    }
}
