package ch.heigvd.amt.stackovergoat.application.gamification;

public enum Badges {

    FIRST_QUESTION("first_question");

    Badges(String ruleName){
        this.ruleName = ruleName;
    }

    private String ruleName;

    public String toString(){
        return ruleName;
    }

}
