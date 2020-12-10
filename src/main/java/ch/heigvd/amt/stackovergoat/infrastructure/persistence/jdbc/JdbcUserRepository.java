package ch.heigvd.amt.stackovergoat.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stackovergoat.application.user.ProposeUserCommand;
import ch.heigvd.amt.stackovergoat.application.user.UsersQuery;
import ch.heigvd.amt.stackovergoat.domain.user.IUserRepository;
import ch.heigvd.amt.stackovergoat.domain.user.User;
import ch.heigvd.amt.stackovergoat.domain.user.UserId;
import ch.heigvd.amt.stackovergoat.infrastructure.persistence.exception.IntegrityConstraintViolationException;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ApplicationScoped
@Named("JdbcUserRepository")
public class JdbcUserRepository implements IUserRepository {

    @Resource(lookup = "jdbc/StackOverflowDS")
    DataSource dataSource;

    public JdbcUserRepository(){}

    public JdbcUserRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Collection<User> find(UsersQuery query) {
        Collection<User> matchingEntities = findAll().stream()
                .filter(u -> u.getUsername().equals(u.getUsername())) // TODO : use the query to filter the list
                .collect(Collectors.toList());

        return matchingEntities;
    }

    @Override
    public Optional<User> findByUsername(String username) {

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM User WHERE username = ?");
            sql.setString(1, username);
            ResultSet res = sql.executeQuery();

            while(res.next())
            {
                UserId id = new UserId(res.getString("idUser"));
                String firstname = res.getString("firstname");
                String lastname = res.getString("lastname");
                String email = res.getString("email");
                String passwd = res.getString("password");

                User submittedUser =
                        User.builder()
                                .id(id)
                                .username(username)
                                .email(email)
                                .firstname(firstname)
                                .lastname(lastname)
                                .encryptedPassword(passwd)
                                .build();
                connection.close();
                return Optional.of(submittedUser);
            }

        } catch (SQLException e) {
            //traitement de l'exception
            throw new IllegalArgumentException(e);
        }
        return Optional.empty();
    }

    @Override
    public void save(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("INSERT INTO User (idUser, username, lastname, firstname, email, password) VALUES (?,?,?,?,?,?)");
            sql.setString(1, user.getId().asString());
            sql.setString(2, user.getUsername());
            sql.setString(3, user.getLastname());
            sql.setString(4, user.getFirstname());
            sql.setString(5, user.getEmail());
            sql.setString(6, user.getEncryptedPassword());

            int nbRow = sql.executeUpdate();

            connection.close();

            if(nbRow > 1){
                throw new IllegalArgumentException("Task went wrong");
            }
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
    }
    @Override
    public void remove(UserId id) {
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("DELETE FROM User WHERE idUser = ?");
            sql.setString(1, id.asString());
            int nbRow = sql.executeUpdate();
            connection.close();
        }catch(SQLException e){
            throw new IllegalArgumentException(e);
        }
    }


    @Override
    public Optional<User> findById(UserId id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM User WHERE idUser = ?");
            sql.setString(1, id.asString());
            ResultSet res = sql.executeQuery();

            while(res.next())
            {
                String username =  res.getString("username");
                String firstname = res.getString("firstname");
                String lastname = res.getString("lastname");
                String email = res.getString("email");
                String passwd = res.getString("password");

                User submittedUser =

                        User.builder()
                                .id(id)
                                .username(username)
                                .email(email)
                                .firstname(firstname)
                                .lastname(lastname)
                                .encryptedPassword(passwd)
                                .build();
                return Optional.of(submittedUser);
            }

        } catch (SQLException e) {
            //traitement de l'exception
            throw new IllegalArgumentException(e);
        }
        return Optional.empty();
    }

    @Override
    public Collection<User> findAll() {
        List<User> matchingEntities = new LinkedList<User>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM User");
            ResultSet res = sql.executeQuery();
            while (res.next()){
                UserId id = new UserId(res.getString("idUser"));
                String username = res.getString("username");
                String firstname = res.getString("firstname");
                String lastname = res.getString("lastname");
                String email = res.getString("email");
                String passwd = res.getString("password");

                User submittedUser =
                        User.builder()
                                .id(id)
                                .username(username)
                                .email(email)
                                .firstname(firstname)
                                .lastname(lastname)
                                .encryptedPassword(passwd)
                                .build();
                matchingEntities.add(submittedUser);
            }
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
        return matchingEntities;
    }

    @Override
    public void changePassword(String username, String newClearTextPassword) {

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("UPDATE User SET password = ? WHERE username = ?");
            sql.setString(1, BCrypt.hashpw(newClearTextPassword, BCrypt.gensalt()));
            sql.setString(2, username);
            sql.executeUpdate();
            System.out.println("Request sent with new pass " + newClearTextPassword);
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void updateProfile(String username, String lastname, String firstname, String email) throws IntegrityConstraintViolationException {
        try {
            if((lastname == null || lastname == "")  && (firstname == null || firstname == "") && (email == null || email == "")){
                return;
            }
            String statement = "UPDATE User SET ";
            if(lastname != null && !lastname.isEmpty()){
                statement += "lastname = ?";
                if((firstname != null && !firstname.isEmpty()) || (email != null && !email.isEmpty())){
                    statement += ", ";
                }
            }
            if(firstname != null && !firstname.isEmpty()){
                statement += "firstname = ?";
                if(email != null && !email.isEmpty()){
                    statement += ", ";
                }
            }
            if(email != null && !email.isEmpty()){
                statement += "email = ?";
            }
            statement += " WHERE username = ?";

            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement(statement);
            if((lastname == null || lastname.isEmpty()) && (firstname == null || firstname.isEmpty()) && (email != null && !email.isEmpty())){
                sql.setString(1, email);
                sql.setString(2, username);
            }else if((lastname == null || lastname.isEmpty()) && (firstname != null && !firstname.isEmpty()) && (email == null || email.isEmpty())){
                sql.setString(1, firstname);
                sql.setString(2, username);
            }else if((lastname == null || lastname.isEmpty()) && (firstname != null && !firstname.isEmpty()) && (email != null && !email.isEmpty())){
                sql.setString(1, firstname);
                sql.setString(2, email);
                sql.setString(3, username);
            }else if((lastname != null && !lastname.isEmpty()) && (firstname == null || firstname.isEmpty()) && (email == null || email.isEmpty())){
                sql.setString(1, lastname);
                sql.setString(2, username);
            }else if((lastname != null && !lastname.isEmpty()) && (firstname == null || firstname.isEmpty()) && (email != null && !email.isEmpty())){
                sql.setString(1, lastname);
                sql.setString(2, email);
                sql.setString(3, username);
            }else if((lastname != null && !lastname.isEmpty()) && (firstname != null && !firstname.isEmpty()) && (email == null || email.isEmpty())){
                sql.setString(1, lastname);
                sql.setString(2, firstname);
                sql.setString(3, username);
            }else{
                sql.setString(1, lastname);
                sql.setString(2, firstname);
                sql.setString(3, email);
                sql.setString(4, username);
            }

            sql.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public int getSize() {
     int nb=0;
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("   SELECT COUNT(*) FROM User");
            ResultSet res = sql.executeQuery();
            while (res.next()) {
                nb += res.getInt(1);
            }
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
        return nb;
    }

}
