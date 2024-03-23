package ca.bestbuy.zephyrtestautomation.service;

import ca.bestbuy.zephyrtestautomation.request.GPTRequest;
import ca.bestbuy.zephyrtestautomation.response.GPTResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GPTService {

    @Value("${OPEN_API_KEY}")
    private String OPEN_API_KEY;

    @Value("${OPEN_API_URL}")
    private String OPEN_API_URL;

    public String generateGPTTestStep(String gwt){
        GPTRequest gptRequest = new GPTRequest();
        gptRequest.setPrompt(gwt);


        // make a post request to openai gpt
        HttpPost httpPost = new HttpPost(OPEN_API_URL);
        httpPost.setHeader("Authorization ", "Bearer " + OPEN_API_KEY);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        String body = "";

        try {
            final StringEntity entity = new StringEntity(body);
            httpPost.setEntity(entity);

            try (CloseableHttpClient httpClient = HttpClients.custom().build();
                 CloseableHttpResponse response = httpClient.execute(httpPost)) {
                String responseBoyd = EntityUtils.toString(response.getEntity());
                GPTResponse gptResponse;
                return "Error";
//                return gptResponse.getChoices().get(0).getText();
            } catch (IOException e) {
                e.printStackTrace();
                return "Error";
            }
        } catch (Exception e) {
            return "Error";
        }

    }
}
