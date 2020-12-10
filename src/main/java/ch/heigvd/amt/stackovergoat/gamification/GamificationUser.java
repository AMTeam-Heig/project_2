package ch.heigvd.amt.stackovergoat.gamification;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class GamificationUser {
    private String reputation;
    private String birthdate;
    private String username;
    private int points;
    private int role;
}
