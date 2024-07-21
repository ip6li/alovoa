package com.nonononoki.alovoa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@ConfigurationProperties("oidc")
public class OidcConfig {
    private static final Logger logger = LoggerFactory.getLogger(OidcConfig.class);

    private final ArrayList<HashMap<String,String>> providerList = new ArrayList<>();

    public ArrayList<HashMap<String, String>> getProvider() {
        return providerList;
    }

    public int findProvider(String providerName) {
        for (int i = 0; i < providerList.size(); i++) {
            if (providerList.get(i).get("name").equals(providerName)) {
                return i;
            }
        }
        return -1;
    }
}
