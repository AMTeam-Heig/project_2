package stackovergoat.application;

import ch.heigvd.amt.stackovergoat.application.user.ProposeUserCommand;
import ch.heigvd.amt.stackovergoat.application.user.UserFacade;
import ch.heigvd.amt.stackovergoat.application.user.UsersDTO;
import ch.heigvd.amt.stackovergoat.application.user.UsersQuery;
import ch.heigvd.amt.stackovergoat.domain.user.IUserRepository;
import ch.heigvd.amt.stackovergoat.infrastructure.persistence.memory.InMemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private final String USERNAME = "Username";
    private final String FIRSTNAME = "Firstname";
    private final String LASTNAME = "Lastname";
    private final String EMAIL = "Email";
    private final String PASSWORD = "Password";

    private static IUserRepository userRepository;
    private static UserFacade userFacade;

    private final ProposeUserCommand proposeUserCommand = ProposeUserCommand.builder()
            .username(USERNAME)
            .email(EMAIL)
            .firstname(FIRSTNAME)
            .lastname(LASTNAME)
            .clearTextPassword(PASSWORD)
            .build();

    @BeforeEach
    public void initialization() {
        userRepository = new InMemoryUserRepository();
        userFacade = new UserFacade(userRepository);
    }

    @Test
    public void proposeCommandShouldContainCorrectUser() {
        assertEquals(FIRSTNAME, proposeUserCommand.getFirstname());
        assertEquals(LASTNAME, proposeUserCommand.getLastname());
        assertEquals(USERNAME, proposeUserCommand.getUsername());
        assertEquals(EMAIL, proposeUserCommand.getEmail());
        assertEquals(PASSWORD, proposeUserCommand.getClearTextPassword());
    }

    @Test
    public void getUsersShouldReturnANonEmptyCollection() {
        assertDoesNotThrow(() -> userFacade.proposeUser(proposeUserCommand));
    }

    @Test
    public void proposingAUserShouldAddItToTheList() {
        userFacade.proposeUser(proposeUserCommand);
        UsersQuery usersQuery = UsersQuery.builder()
                .isUser(true)
                .build();
        UsersDTO usersDTO = userFacade.getUsers(usersQuery);

        assertFalse(usersDTO.getUsers().isEmpty());
    }

    @Test
    public void userFacadeShouldStoreCorrectUser() {
        assertDoesNotThrow(() -> userFacade.proposeUser(proposeUserCommand));
        UsersQuery usersQuery = UsersQuery.builder()
                .isUser(true)
                .build();
        UsersDTO usersDTO = userFacade.getUsers(usersQuery);
        assertEquals(USERNAME, usersDTO.getUsers().get(0).getUsername());
    }
}
