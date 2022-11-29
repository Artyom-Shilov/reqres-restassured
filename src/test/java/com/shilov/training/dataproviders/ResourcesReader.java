package com.shilov.training.dataproviders;

import com.shilov.training.models.Resource;
import com.shilov.training.validation.Validator;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@UtilityClass
public class ResourcesReader {

    private static final Properties RESOURCES_PROPERTIES = PropertiesLoader.loadProperties(
            "src/test/resources/resources.properties");

    private static final long CURRENT_RESOURCES_AMOUNT;

    static {
        CURRENT_RESOURCES_AMOUNT = RESOURCES_PROPERTIES.entrySet()
                .stream()
                .filter(e -> ((String)e.getKey()).contains("name"))
                .count();
    }

    public static Resource getResourceById(long id) {
        Validator.checkId(id, CURRENT_RESOURCES_AMOUNT);
        return Resource.builder()
                .id(id)
                .name(RESOURCES_PROPERTIES.getProperty("resource." + id + ".name"))
                .color(RESOURCES_PROPERTIES.getProperty("resource." + id + ".color"))
                .year(RESOURCES_PROPERTIES.getProperty("resource." + id + ".year"))
                .pantoneValue(RESOURCES_PROPERTIES.getProperty("resource." + id + ".pantoneValue"))
                .build();
    }

    public static List<Resource> getResourcesInRange(long from, long to) {
        Validator.checkIdInRange(from, to, CURRENT_RESOURCES_AMOUNT);
        List<Resource> resources = new ArrayList<>();
        for (long i = from; i <= to; i++ ) {
            resources.add(getResourceById(i));
        }
        return resources;
    }

    public static List<Resource> getAllResources() {
        List<Resource> resources = new ArrayList<>();
        for (long i = 1; i <= CURRENT_RESOURCES_AMOUNT; i++) {
            resources.add(getResourceById(i));
        }
        return resources;
    }
}
