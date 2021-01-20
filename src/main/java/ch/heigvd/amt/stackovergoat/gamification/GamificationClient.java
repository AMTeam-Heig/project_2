package ch.heigvd.amt.stackovergoat.gamification;

import ch.heig.amt.gamification.api.DefaultApi;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

import java.util.List;

public class GamificationClient {
    /*private DefaultApi api;

    public void callapi(){
        ch.heig.amt.gamification.api.dto.NewApplication gamification = new ch.heig.amt.gamification.api.dto.NewApplication();
        gamification.setName("GamificationEngine");
        try {
            api.createApplication(gamification);
        }
    }


    /*private enum Endpoint {
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
            //Unirest.config().setDefaultBasicAuth("X-API-KEY", currentApplication.getApiKey());
        } catch (UnirestException e) {
            Unirest.shutDown();
            e.printStackTrace();
        }
    }*/

    /**
     *
     * @param name
     * @return
     */
    /*public Application createApplication(String name) {
        return Unirest.post(Endpoint.APPLICATIONS.getUrlValue())
                .body(new NewApplication(name))
                .asObject(Application.class).getBody();
    }

    /**
     *
     * @param name
     * @return
     */
    /*public Application getApplication(String name) {
        return Unirest.get(Endpoint.APPLICATIONS.getUrlValue() + "/" + name)
                .asObject(Application.class).getBody();
    }

    public List<Application> getApplications() {
        return Unirest.get(Endpoint.APPLICATIONS.getUrlValue())
                .asObject(new GenericType<List<Application>>() {}).getBody();
    }

    /*
     * BADGES
     */
    /*public List<Badge> getBadges() {
        return Unirest.get(Endpoint.BADGES.getUrlValue())
                .asObject(new GenericType<List<Badge>>() {}).getBody();
    }

    public Badge getBadge(String name) {
        return Unirest.get(Endpoint.BADGES.getUrlValue() + "/" + name)
                .asObject(Badge.class).getBody();
    }

    public Badge createBadge(Badge badge) {
        return Unirest.post(Endpoint.BADGES.getUrlValue())
                .body(badge)
                .asObject(Badge.class).getBody();
    }

    /*
     * EVENTS
     */
    /*public List<Event> getEvents() {
        return Unirest.get(Endpoint.EVENTS.getUrlValue())
                .asObject(new GenericType<List<Event>>() {}).getBody();
    }

    public Event getEvent(String name) {
        return Unirest.get(Endpoint.EVENTS.getUrlValue() + "/" + name)
                .asObject(Event.class).getBody();
    }

    public Event createEvent(Event event) {
        return Unirest.post(Endpoint.EVENTS.getUrlValue())
                .body(event)
                .asObject(Event.class).getBody();
    }

    /*
     * RULES
     */
    /*public List<Rule> getRules() {
        return Unirest.get(Endpoint.RULES.getUrlValue())
                .asObject(new GenericType<List<Rule>>() {}).getBody();
    }

    public Rule getRule(String name) {
        return Unirest.get(Endpoint.RULES.getUrlValue() + "/" + name)
                .asObject(Rule.class).getBody();
    }

    public Rule createRule(Event event) {
        return Unirest.post(Endpoint.RULES.getUrlValue())
                .body(event)
                .asObject(Rule.class).getBody();
    }

    /*
     * USERS
     */
    /*public List<GamificationUser> getUsers() {
        return Unirest.get(Endpoint.USERS.getUrlValue())
                .asObject(new GenericType<List<GamificationUser>>() {}).getBody();
    }

    public GamificationUser getUser(String name) {
        return Unirest.get(Endpoint.USERS.getUrlValue() + "/" + name)
                .asObject(GamificationUser.class).getBody();
    }

    public GamificationUser deleteUser(String name) {
        return Unirest.get(Endpoint.USERS.getUrlValue() + "/" + name)
                .asObject(GamificationUser.class).getBody();
    }

    public GamificationUser createUser(Event event) {
        return Unirest.post(Endpoint.USERS.getUrlValue())
                .body(event)
                .asObject(GamificationUser.class).getBody();
    }

    /*
     * TOP_N
     */
    /*public List<GamificationUser> getTopNUsers(int n) {
        return Unirest.get(Endpoint.TOP_N.getUrlValue() + "/" + n)
                .asObject(new GenericType<List<GamificationUser>>() {}).getBody();
    }

    /*
     * USER_EVOLUTION
     */
    /*public List<UserEvolution> getUserEvolution(String name) {
        return Unirest.get(Endpoint.USERS_EVOLUTION.getUrlValue() + "/" + name)
                .asObject(new GenericType<List<UserEvolution>>() {}).getBody();
    }*/
}
