package ch.heigvd.amt.stackovergoat.application.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Builder
@Getter
@EqualsAndHashCode
public class UsersQuery {

    @Builder.Default
    private boolean isUser = true;
}
