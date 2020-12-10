package ch.heigvd.amt.stackovergoat.gamification;

import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class GamificationClient {
    private enum Endpoint {
        APPLICATIONS("applications"),
        BADGES("badges"),
        EVENTS("events"),
        RULES("rules"),
        TOP_N("topN-users"),
        USERS("users"),
        USERS_EVOLUTION("users-evolution");

        private final String urlValue;
        Endpoint(String urlValue) {
            this.urlValue = urlValue;
        }

        public String getUrlValue() {
            return urlValue;
        }
    }

    private String gamificationURL = "http://gamification:9081/";
    private Application currentApplication;

    public GamificationClient(String name) {
        Unirest.config()
                .defaultBaseUrl(gamificationURL)
                .addDefaultHeader("accept", "application/json")
                .addDefaultHeader("Content-Type", "application/json");

        try {
            currentApplication = createApplication(name);
            Unirest.config().addDefaultHeader("X-API-KEY", currentApplication.getApiKey());
            Unirest.config().setDefaultBasicAuth("X-API-KEY", currentApplication.getApiKey());
        } catch (UnirestException e) {
            Unirest.shutDown();
            e.printStackTrace();
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public Application createApplication(String name) {
        return Unirest.post(Endpoint.APPLICATIONS.getUrlValue())
                .body(new AppCreation(name))
                .asObject(Application.class).getBody();
    }

    /**
     *
     * @param name
     * @return
     */
    public Application getApplication(String name) {
        return Unirest.get(Endpoint.APPLICATIONS.getUrlValue() + "/" + name)
                .asObject(Application.class).getBody();
    }
}
