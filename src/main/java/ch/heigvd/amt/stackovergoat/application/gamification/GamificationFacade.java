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
        try {
            defaultApi.createBadge(defaultApi.getApplication(System.getenv("GAMIFICATION_SERVER_NAME")).getApiKey(), firstQuestionBadge());
            defaultApi.createBadge(defaultApi.getApplication(System.getenv("GAMIFICATION_SERVER_NAME")).getApiKey(), firstAnswerBadge());
            defaultApi.createBadge(defaultApi.getApplication(System.getenv("GAMIFICATION_SERVER_NAME")).getApiKey(), firstCommentBadge());
            defaultApi.createBadge(defaultApi.getApplication(System.getenv("GAMIFICATION_SERVER_NAME")).getApiKey(), firstUpvoteBadge());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Badge firstQuestionBadge(){
        Badge firstQuestionBadge = new Badge();
        firstQuestionBadge.name(Badges.FIRST_QUESTION.toString())
                .description("Badge for posting a question");
        return firstQuestionBadge;
    }

    private Badge firstCommentBadge(){
        Badge firstCommentBadge = new Badge();
        firstCommentBadge.name(Badges.FIRST_COMMENT.toString())
                .description("Badge for posting a comment");
        return firstCommentBadge;
    }

    private Badge firstAnswerBadge(){
        Badge firstAnswerBadge = new Badge();
        firstAnswerBadge.name(Badges.FIRST_ANSWER.toString())
                .description("Badge for posting an answer");
        return firstAnswerBadge;
    }

    private Badge firstUpvoteBadge(){
        Badge firstUpvoteBadge = new Badge();
        firstUpvoteBadge.name(Badges.FIRST_UPVOTE.toString())
                .description("Badge for posting an upvote");
        return firstUpvoteBadge;
    }

    private void createRules(){
        try {
            defaultApi.createRule(defaultApi.getApplication(System.getenv("GAMIFICATION_SERVER_NAME")).getApiKey(), firstQuestionRule());
            defaultApi.createRule(defaultApi.getApplication(System.getenv("GAMIFICATION_SERVER_NAME")).getApiKey(), firstAnswerRule());
            defaultApi.createRule(defaultApi.getApplication(System.getenv("GAMIFICATION_SERVER_NAME")).getApiKey(), firstCommentRule());
            defaultApi.createRule(defaultApi.getApplication(System.getenv("GAMIFICATION_SERVER_NAME")).getApiKey(), firstUpvoteRule());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Rule firstQuestionRule(){
        Rule questionBadgeRule = new Rule();
        questionBadgeRule
                .badgeName(Badges.FIRST_QUESTION.toString())
                .eventName(Events.ADD_QUESTION.toString())
                .definition("The user posted his first question")
                .reputation("")
                .points(0);
        return questionBadgeRule;
    }

    private Rule firstCommentRule(){
        Rule firstCommentRule = new Rule();
        firstCommentRule
                .badgeName(Badges.FIRST_COMMENT.toString())
                .eventName(Events.ADD_COMMENT.toString())
                .definition("The user posted his first comment")
                .reputation("")
                .points(3);
        return firstCommentRule;
    }

    private Rule firstAnswerRule(){
        Rule firstAnswerRule = new Rule();
        firstAnswerRule
                .badgeName(Badges.FIRST_ANSWER.toString())
                .eventName(Events.ADD_ANSWER.toString())
                .definition("The user posted his first answer")
                .reputation("")
                .points(5);
        return firstAnswerRule;
    }

    private Rule firstUpvoteRule(){
        Rule firstUpvoteRule = new Rule();
        firstUpvoteRule
                .badgeName(Badges.FIRST_UPVOTE.toString())
                .eventName(Events.ADD_UPVOTE.toString())
                .definition("The user gave his first upvote")
                .reputation("")
                .points(2);
        return firstUpvoteRule;
    }
}
