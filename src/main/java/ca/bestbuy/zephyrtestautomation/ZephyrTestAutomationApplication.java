package ca.bestbuy.zephyrtestautomation;

import ca.bestbuy.automation.jiraintegration.JiraIntegration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ZephyrTestAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZephyrTestAutomationApplication.class, args);
	}

	@Bean
	public JiraIntegration jiraIntegration() {
		// If JiraIntegration requires any configuration, initialize it here
		return new JiraIntegration( );
	}

}
