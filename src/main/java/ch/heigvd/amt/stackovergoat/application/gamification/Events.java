package ch.heigvd.amt.stackovergoat.application.gamification;

public enum Events {
    ADD_COMMENT("add_comment"),
    ADD_QUESTION("add_question"),
    ADD_COMMENTARY("add_commentary"),
    ADD_UPVOTE("add_upvote"),
    ADD_DOWNVOTE("add_downvote"),
    ADD_ANSWER("add_answer");

    Events(String eventName){
        this.eventName = eventName;
    }

    private String eventName;

    public String toString(){
        return eventName;
    }

}
