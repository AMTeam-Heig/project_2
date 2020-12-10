package ch.heigvd.amt.stackovergoat.gamification;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Badge {

    private LocalDate obtainedOnDate;
    private String description;
    private String name;

}
