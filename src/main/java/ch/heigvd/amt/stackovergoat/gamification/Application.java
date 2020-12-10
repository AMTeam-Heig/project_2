package ch.heigvd.amt.stackovergoat.gamification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class Application {
    private String apiKey;
    private String name;
}
