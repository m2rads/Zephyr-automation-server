package ca.bestbuy.zephyrtestautomation.controller;

import ca.bestbuy.zephyrtestautomation.model.GWTTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/gpt")
public class ChatGPTController {

    @PostMapping("/test-step")
    public String testStep(@RequestBody GWTTable gwtTable) {
        System.out.println("gwt" + gwtTable);

        return "Something ";
    }
}
