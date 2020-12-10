package ch.heigvd.amt.stackovergoat.application.statistics;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StatsDTO {
    int nbQuestion;
    int nbUser;
}