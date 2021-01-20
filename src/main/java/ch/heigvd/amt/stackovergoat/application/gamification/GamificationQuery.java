package ch.heigvd.amt.stackovergoat.application.gamification;

import ch.heig.amt.gamification.ApiException;
import ch.heig.amt.gamification.api.DefaultApi;
import ch.heig.amt.gamification.api.dto.Badge;
import ch.heig.amt.gamification.api.dto.Event;
import ch.heig.amt.gamification.api.dto.Rule;
import ch.heig.amt.gamification.api.dto.User;

public class GamificationQuery {

    DefaultApi defaultApi = DefaultApiSingleton.getInstance();

    String appName = System.getenv("GAMIFICATION_SERVER_NAME");
    String API_KEY = "";

    private void setApiKey(){
        try {
            API_KEY = defaultApi.getApplication(appName).getApiKey();

        } catch (ApiException apiException) {
            apiException.printStackTrace();
        }
    }

    public void createUser(User user){
        try {
            setApiKey();
            defaultApi.createUser(API_KEY, user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public User getUser(String username){
        User user = new User();
        try {
            setApiKey();
            user = defaultApi.getUser(API_KEY, username);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public void createBadge(Badge badge){
        try {
            setApiKey();
            defaultApi.createBadge(API_KEY, badge);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Badge getBadge(String badgeName){
        Badge badge = new Badge();
        try {
            setApiKey();
            badge = defaultApi.getBadge(API_KEY, badgeName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return badge;
    }

    public void createEvent(Event event){
        try {
            setApiKey();
            defaultApi.createEvent(API_KEY, event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Event getEvent(String eventname){
        Event event = new Event();
        try {
            setApiKey();
            event = defaultApi.getEvent(API_KEY, eventname);
        }catch (Exception e){
            e.printStackTrace();
        }
        return event;
    }

    public void createRule(Rule rule){
        try {
            setApiKey();
            defaultApi.createRule(API_KEY, rule);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Rule getRule(String ruleName){
        Rule rule = new Rule();
        try {
            setApiKey();
            rule = defaultApi.getRule(API_KEY, ruleName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rule;
    }

}
