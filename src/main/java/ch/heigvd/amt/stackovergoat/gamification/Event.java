package ch.heigvd.amt.stackovergoat.gamification;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Event {
    private String username;
    private String name;
    private int points;
}
