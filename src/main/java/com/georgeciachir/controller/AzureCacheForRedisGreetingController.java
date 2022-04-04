package com.georgeciachir.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class AzureCacheForRedisGreetingController {

    private static final Logger LOG = LoggerFactory.getLogger(AzureCacheForRedisGreetingController.class);

    @Autowired
    private StringRedisTemplate template;

    @GetMapping(value = "/hello/{name}")
    public String sayHello(@PathVariable String name) {
        LOG.info("Greeting [{}]", name);

        ValueOperations<String, String> ops = template.opsForValue();

        String key = "greeting " + name;
        if (template.hasKey(key)) {
            LOG.info("Retrieving data from the cache");
            return ops.get(key);
        }

        String response = String.format("Hello %s !", name);
        addDataToTheDBExpensiveOperation(response);

        LOG.info("Now adding data to the cache");
        ops.set(key, response, 3, TimeUnit.SECONDS);

        return response;
    }

    private void addDataToTheDBExpensiveOperation(String response) {
        LOG.info("Doing some expensive network calls and adding data to some DB");
    }
}
