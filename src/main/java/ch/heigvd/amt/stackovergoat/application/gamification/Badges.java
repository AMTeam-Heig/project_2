package ch.heigvd.amt.stackovergoat.application.gamification;

public enum Badges {

    FIRST_QUESTION("first_question"),
    FIRST_UPVOTE("first_upvote"),
    FIRST_DOWNVOTE("first_downvote"),
    FIRST_ANSWER("first_answer"),
    FIRST_COMMENT("first_comment");

    Badges(String ruleName){
        this.ruleName = ruleName;
    }

    private String ruleName;

    public String toString(){
        return ruleName;
    }

}
