package ch.heigvd.amt.stackovergoat.application.user;

import ch.heigvd.amt.stackovergoat.domain.question.QuestionId;
import ch.heigvd.amt.stackovergoat.domain.user.User;
import ch.heigvd.amt.stackovergoat.domain.user.IUserRepository;
import ch.heigvd.amt.stackovergoat.domain.user.UserId;
import ch.heigvd.amt.stackovergoat.infrastructure.persistence.exception.IntegrityConstraintViolationException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserFacade {
    private IUserRepository userRepository;

    public UserFacade(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void proposeUser(ProposeUserCommand command) {
        User submittedUser = User.builder()
                .username(command.getUsername())
                .email(command.getEmail())
                .firstname(command.getFirstname())
                .lastname(command.getLastname())
                .clearTextPassword(command.getClearTextPassword())
                .build();
        try {
            userRepository.save(submittedUser);
        } catch (IntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
    }

    public UsersDTO getUsers(UsersQuery query) {
        Collection<User> allUsers = userRepository.find(query);

        List<UsersDTO.UserDTO> allUsersDTO = allUsers.stream()
            .map(user -> UsersDTO.UserDTO.builder()
                .username(user.getUsername())
                .build())
            .collect(Collectors.toList());

        return UsersDTO.builder()
                .users(allUsersDTO)
                .build();
    }

    public void remove(String id) {
        userRepository.remove(new UserId(id));
    }
}
