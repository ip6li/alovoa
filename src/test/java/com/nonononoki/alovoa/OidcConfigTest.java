package com.nonononoki.alovoa;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


@SpringBootTest
class OidcConfigTest {
    static private final Logger logger = LoggerFactory.getLogger(OidcConfigTest.class);

    @Value("#{oidcConfig.provider}")
    private ArrayList<HashMap<String,String>> oidcProviderMap;

    @TestConfiguration
    @EnableConfigurationProperties(value = OidcConfig.class)
    static class TestConfig { }

    @BeforeEach
    void setup() {

    }

    @Test
    void getProvider() {
        logger.info("oidcProviderMap:");
        AtomicInteger count = new AtomicInteger();
        oidcProviderMap.forEach((e) -> {
            Assertions.assertNotNull(e);
            logger.info(String.format("  Provider index: %d", count.getAndIncrement()));
            e.forEach((k,v) -> {
                Assertions.assertNotNull(k);
                Assertions.assertNotNull(v);
                logger.info("    key: {}, value: {}", k, v);
            });
        });
    }

    @Test
    void findExistingProvider() {
        String providerName = "keycloak";
        boolean found = false;
        for (HashMap<String, String> stringStringHashMap : oidcProviderMap) {
            if (stringStringHashMap.get("name").equals(providerName)) {
                found = true;
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    void findNotExistingProvider() {
        String providerName = "kgnksdngsk";
        boolean found = false;
        for (HashMap<String, String> stringStringHashMap : oidcProviderMap) {
            if (stringStringHashMap.get("name").equals(providerName)) {
                found = true;
            }
        }
        Assertions.assertFalse(found);
    }

}
