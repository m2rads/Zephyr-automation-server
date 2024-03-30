package ca.bestbuy.zephyrtestautomation.service;

import ca.bestbuy.zephyrtestautomation.request.GPTRequest;
import ca.bestbuy.zephyrtestautomation.response.GPTResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GPTService {

    @Value("${OPEN_API_KEY}")
    private String OPEN_API_KEY;

    @Value("${OPEN_API_URL}")
    private String OPEN_API_URL;

    public String generateGPTTestStep(String gwt){
        List<GPTRequest.ChatMessage> messages = new ArrayList<>();
        messages.add(new GPTRequest.ChatMessage("system", "You are a helpful assistant."));
        messages.add(new GPTRequest.ChatMessage("user", gwt));

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
            final StringEntity entity = new StringEntity(body);
            httpPost.setEntity(entity);

            try (CloseableHttpClient httpClient = HttpClients.custom().build();
                 CloseableHttpResponse response = httpClient.execute(httpPost)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println("Response body: " + responseBody);
                GPTResponse gptResponse = objectMapper.readValue(responseBody, GPTResponse.class);
                return gptResponse.getChoices().get(0).getMessage().getContent();

            } catch (Exception e) {
                return "Error";
            }
        } catch (Exception e) {
            return "Error";
        }

    }
}
