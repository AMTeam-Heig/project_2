package ch.heigvd.amt.stackovergoat.domain.user;

import ch.heigvd.amt.stackovergoat.domain.IEntity;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class User implements IEntity<User, UserId> {

    private UserId id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;

    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

    public boolean authenticate(String clearTextPassword) {
        return BCrypt.checkpw(clearTextPassword, encryptedPassword);
    }

    @Override
    public User deepClone() {
        return this.toBuilder()
                .id(new UserId(id.asString()))
                .build();
    }

    public String getUsername() {
        return username;
    }

    public static class UserBuilder {
        public UserBuilder clearTextPassword(String clearTextPassword) {
            if (clearTextPassword == null || clearTextPassword.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Password is mandatory.");
            }
            // TODO : chiffrage
            encryptedPassword = BCrypt.hashpw(clearTextPassword, BCrypt.gensalt());
            return this;
        }

        public User build() {
            if(id == null) {
                id = new UserId();
            }

            if(username == null || username.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Username is mandatory.");
            }
            if(encryptedPassword == null || encryptedPassword.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Password is mandatory.");
            }
            if(firstname == null || firstname.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Firstname is mandatory.");
            }
            if(lastname == null || lastname.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Lastname is mandatory.");
            }
            if(email == null || email.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Email is mandatory.");
            }

            User newUser = new User(id, firstname, lastname, username, email, encryptedPassword);
            return newUser;
        }
    }
}
