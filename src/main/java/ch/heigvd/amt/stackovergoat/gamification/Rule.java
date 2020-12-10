package ch.heigvd.amt.stackovergoat.gamification;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Rule {
    private String name;
    private String badgeName;
    private String eventName;
    private String definition;
    private String reputation;
    private int points;
}
