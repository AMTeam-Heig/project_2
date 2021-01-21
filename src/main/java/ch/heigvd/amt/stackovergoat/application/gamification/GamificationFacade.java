package ch.heigvd.amt.stackovergoat.application.gamification;

import ch.heig.amt.gamification.api.DefaultApi;
import ch.heig.amt.gamification.api.dto.Badge;
import ch.heig.amt.gamification.api.dto.NewApplication;
import ch.heig.amt.gamification.api.dto.Rule;

import java.time.LocalDate;


public class GamificationFacade {

    private DefaultApi defaultApi;

    public GamificationFacade(String name){

        defaultApi = DefaultApiSingleton.getInstance();

        String defaultname = name;

        //Application application;
        NewApplication newApplication;

        //newApplication.setName(name);

        String url = System.getenv("GAMIFICATION_SERVER_URL");

        if(name.isEmpty()){
            defaultname = System.getenv("GAMIFICATION_SERVER_NAME");
        }

        if(url.isEmpty()){
            url = "http://192.168.99.100:9081";
        }

        defaultApi.getApiClient().setBasePath(url);

        newApplication = new NewApplication();
        newApplication.setName(defaultname);
        try {
            defaultApi.createApplication(newApplication);
            defaultApi.getApiClient().setApiKey(defaultApi.getApplication(defaultname).getApiKey());
        }catch (Exception e){
            e.printStackTrace();
        }

        createBadges();
        createRules();


    }


    private void createBadges(){
        Badge firstQuestionBadge = new Badge();
        firstQuestionBadge.name(Badges.FIRST_QUESTION.toString())
                .description("Badge for posting a question");
        try {
            defaultApi.createBadge(defaultApi.getApplication(System.getenv("GAMIFICATION_SERVER_NAME")).getApiKey(), firstQuestionBadge);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createRules(){
        Rule questionBadgeRule = new Rule();
        questionBadgeRule
                .badgeName(Badges.FIRST_QUESTION.toString())
                .eventName(Events.ADD_QUESTION.toString())
                .definition("The user posted his first question")
                .reputation("")
                .points(0);

        try {
            defaultApi.createRule(defaultApi.getApplication(System.getenv("GAMIFICATION_SERVER_NAME")).getApiKey(), questionBadgeRule);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
