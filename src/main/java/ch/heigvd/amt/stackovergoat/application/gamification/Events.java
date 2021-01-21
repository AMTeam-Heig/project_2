package ch.heigvd.amt.stackovergoat.application.gamification;

public enum Events {
    ADD_COMMENT("add_comment"),
    ADD_QUESTION("add_question"),
    ADD_COMMENTARY("add_commentary"),
    ADD_VOTE("add_vote"),
    ADD_REPLY("add_reply");

    Events(String eventName){
        this.eventName = eventName;
    }

    public String eventName;

}
