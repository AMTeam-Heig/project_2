package ch.heigvd.amt.stackovergoat.application.identitymgmt;

import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.AuthentificationFailedException;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.login.RegistrationFailedException;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.profile.UpdateFailedException;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.profile.UpdatePasswordCommand;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.profile.UpdateProfileCommand;
import ch.heigvd.amt.stackovergoat.domain.user.IUserRepository;
import ch.heigvd.amt.stackovergoat.domain.user.User;

public class IdentityManagementFacade {

    private IUserRepository userRepository;

    public IdentityManagementFacade(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegisterCommand command) throws RegistrationFailedException {
        User existingUserWithSameUsername = userRepository.findByUsername(command.getUsername()).orElse(null);

        if(existingUserWithSameUsername != null) {
            throw new RegistrationFailedException("Username is already used");
        }

        try {
            User newUser = User.builder()
                    .username(command.getUsername())
                    .firstname(command.getFirstname())
                    .lastname(command.getLastname())
                    .email(command.getEmail())
                    .clearTextPassword(command.getClearTextPassword())
                    .build();
            userRepository.save(newUser);
        } catch (Exception e) {
            throw new RegistrationFailedException(e.getMessage());
        }
    }

    public CurrentUserDTO authenticate(AuthenticateCommand command) throws AuthentificationFailedException {
        User user = userRepository.findByUsername(command.getUsername())
                .orElseThrow(() -> new AuthentificationFailedException("User not found"));

        boolean success = user.authenticate(command.getClearTextPassword());
        if(!success) {
            throw new AuthentificationFailedException("Verification of credentials failed");
        }

        CurrentUserDTO currentUser = CurrentUserDTO.builder()
                .id(user.getId().asString())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();

        return currentUser;
    }

    public void changePassword(UpdatePasswordCommand command) throws UpdateFailedException {
        try {
            userRepository.changePassword(command.getUsername(), command.getNewClearTextPassword());
        } catch(Exception e){
            throw new UpdateFailedException(e.getMessage());
        }
    }

    public CurrentUserDTO updateProfile(UpdateProfileCommand command) throws UpdateFailedException {
        try {
            userRepository.updateProfile(command.getUsername(), command.getLastname(), command.getFirstname(), command.getEmail());

            User user = userRepository.findByUsername(command.getUsername())
                    .orElseThrow(() -> new UpdateFailedException("User not found"));

            CurrentUserDTO currentUser = CurrentUserDTO.builder()
                    .id(user.getId().asString())
                    .username(user.getUsername())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .build();

            return currentUser;

        } catch(Exception e){
            throw new UpdateFailedException(e.getMessage());
        }
    }
}
