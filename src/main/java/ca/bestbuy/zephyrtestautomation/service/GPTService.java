package ca.bestbuy.zephyrtestautomation.service;

import ca.bestbuy.zephyrtestautomation.request.GPTRequest;
import ca.bestbuy.zephyrtestautomation.response.GPTResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GPTService {

    @Value("${OPEN_API_KEY}")
    private String OPEN_API_KEY;

    @Value("${OPEN_API_URL}")
    private String OPEN_API_URL;

    public Map<String, Object> generateGPTTestStep(Map<String, String> requestBody){
        List<GPTRequest.ChatMessage> messages = new ArrayList<>();
        messages.add(new GPTRequest.ChatMessage("system", "You are a helpful assistant."));
        messages.add(new GPTRequest.ChatMessage("user", requestBody.get("jiraPrompt")));

        // Create request
        GPTRequest gptRequest = new GPTRequest();
        gptRequest.setModel("gpt-3.5-turbo");
        gptRequest.setMessages(messages);
        gptRequest.setTemperature(1.0); // Set temperature

        // make a post request to openai gpt
        HttpPost httpPost = new HttpPost(OPEN_API_URL);
        httpPost.setHeader("Authorization", "Bearer " + OPEN_API_KEY);
        httpPost.setHeader("Content-Type", "application/json");

        Gson gson = new Gson();
        String body = gson.toJson(gptRequest);

        System.out.println("Request body: " + body);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            final StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            try (CloseableHttpClient httpClient = HttpClients.custom().build();
                 CloseableHttpResponse response = httpClient.execute(httpPost)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                GPTResponse gptResponse = objectMapper.readValue(responseBody, GPTResponse.class);
                String generatedTestStep = gptResponse.getChoices().get(0).getMessage().getContent();
                System.out.println("Generated Test Step: " + generatedTestStep);
                // Create a map to store the response data
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("generatedTestStep", generatedTestStep);
                responseData.put("status", "success");
                return responseData;

            } catch (Exception e) {
                // If an error occurs during HTTP request execution
                System.out.println("Error During HTTP: " + e.getMessage());
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("status", "error");
                errorResponse.put("message", e.getMessage());
                return errorResponse;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            // If an error occurs during request setup
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return errorResponse;
        }
    }
}
