package stackovergoat.application;

import ch.heigvd.amt.stackovergoat.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.AuthentificationFailedException;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.login.RegistrationFailedException;
import ch.heigvd.amt.stackovergoat.domain.user.IUserRepository;
import ch.heigvd.amt.stackovergoat.infrastructure.persistence.memory.InMemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdentityManagementTest {
    private final String USERNAME = "Username";
    private final String FIRSTNAME = "Firstname";
    private final String LASTNAME = "Lastname";
    private final String EMAIL = "Email";
    private final String PASSWORD = "Password";

    private IUserRepository userRepository;
    private IdentityManagementFacade identityManagementFacade;
    private RegisterCommand registerCommand;

    @BeforeEach
    public void initialization() {
        this.userRepository = new InMemoryUserRepository();
        this.identityManagementFacade = new IdentityManagementFacade(userRepository);
        registerCommand = RegisterCommand.builder()
                .username(USERNAME)
                .email(EMAIL)
                .firstname(FIRSTNAME)
                .lastname(LASTNAME)
                .clearTextPassword(PASSWORD)
                .build();
    }

    @Nested
    public class Register {
        @Test
        public void shouldRegisterValidUser() {
            assertDoesNotThrow(() -> identityManagementFacade.register(registerCommand));
            assertTrue(userRepository.findByUsername(USERNAME).isPresent());
        }

        @Test
        public void shouldNotRegisterDuplicateUsername() throws RegistrationFailedException {
            identityManagementFacade.register(registerCommand);
            assertThrows(RegistrationFailedException.class, () ->
                    identityManagementFacade.register(registerCommand));
        }

        @Test
        public void shouldNotRegisterUserWithMissingInfo() {
            RegisterCommand registerCommand = RegisterCommand.builder()
                    .username(USERNAME)
                    .email(EMAIL)
                    .build();

            assertThrows(RegistrationFailedException.class, () ->
                    identityManagementFacade.register(registerCommand));
        }
    }

    @Nested
    public class Authenticate {
        private final AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
                .username(USERNAME)
                .clearTextPassword(PASSWORD)
                .build();

        @BeforeEach
        public void registerUser() throws RegistrationFailedException {
            identityManagementFacade.register(registerCommand);
        }

        @Test
        public void shouldAuthenticateWithRegisteredUser() {
            assertDoesNotThrow(() -> identityManagementFacade.authenticate(authenticateCommand));
        }

        @Test
        public void shouldReturnCorrectUserDTOOnAuthenticate() throws AuthentificationFailedException {
            CurrentUserDTO userDTO = identityManagementFacade.authenticate(authenticateCommand);
            assertEquals(userDTO.getUsername(), registerCommand.getUsername());
            assertEquals(userDTO.getEmail(), registerCommand.getEmail());
        }

        @Test
        public void shouldNotAuthenticateNonExistingUser() {
            AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
                    .username("Invisible")
                    .clearTextPassword("Person")
                    .build();

            assertThrows(AuthentificationFailedException.class,
                    () -> identityManagementFacade.authenticate(authenticateCommand));
        }

        @Test
        public void shouldNotAuthenticateWithWrongPassword() {
            AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
                    .username(registerCommand.getUsername())
                    .clearTextPassword("Invalid Password")
                    .build();

            assertThrows(AuthentificationFailedException.class, () -> identityManagementFacade.authenticate(authenticateCommand));
        }
    }
}
