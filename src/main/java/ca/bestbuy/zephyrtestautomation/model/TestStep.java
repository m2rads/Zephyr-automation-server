package ca.bestbuy.zephyrtestautomation.model;

public record TestStep(
        Integer stepId,
        String step,
        String data,
        String expectedResult
) {
}
