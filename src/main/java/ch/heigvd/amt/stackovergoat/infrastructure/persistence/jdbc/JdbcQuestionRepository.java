package ch.heigvd.amt.stackovergoat.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stackovergoat.application.question.QuestionsQuery;
import ch.heigvd.amt.stackovergoat.domain.question.IQuestionRepository;
import ch.heigvd.amt.stackovergoat.domain.question.Question;
import ch.heigvd.amt.stackovergoat.domain.question.QuestionId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Named("JdbcQuestionRepository")
public class JdbcQuestionRepository implements IQuestionRepository {
    @Resource(lookup = "jdbc/StackOverflowDS")
    DataSource dataSource;

    public JdbcQuestionRepository(){}

    public JdbcQuestionRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Collection<Question> find(QuestionsQuery query) {
        if (query == null) {
            return findAll();
        }
        boolean fromAuthor  = (!query.getAuthor().equals(""));
        boolean fromId      = (!query.getIdQuestion().equals(""));
        boolean fromText    = (!query.getText().equals(""));
        boolean fromWords   = (!query.getWords().equals(""));

        if (!(fromAuthor || fromId || fromText || fromWords)) {
            return findAll();
        }
        List<Question> questions = findAll().stream()
                .filter(question -> (
                        (fromAuthor && question.getAuthor().equals(query.getAuthor()))              ||
                        (fromId     && question.getId().asString().equals(query.getIdQuestion()))   ||
                        (fromText   && question.getText().equals(query.getText()))                  ||
                        (fromWords  && question.containsWords(query.getWords()))))
                .collect(Collectors.toList());
        return questions;
    }

    @Override
    public Collection<Question> findAll() {
        List<Question> questions = new LinkedList<>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM Question");
            ResultSet resultSet = sql.executeQuery();

            while (resultSet.next()) {
                questions.add(getQuestion(resultSet, connection));
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return questions;
    }

    @Override
    public void save(Question entity) {
        String authorId = "";
        try {
            Connection connection = dataSource.getConnection();


            PreparedStatement userSql = connection.prepareStatement("SELECT * FROM User WHERE username = ?");
            userSql.setString(1, entity.getAuthor());

            ResultSet resultSetUser = userSql.executeQuery();
            if(resultSetUser.next()) {
                authorId = resultSetUser.getString("idUser");
            }else{
                throw new IllegalArgumentException("insert  went wrong");
            }
            //if(resultSetUser.getFetchSize() == 1) {
                //authorId = resultSetUser.getString("idUser");
           // }

            PreparedStatement sql = connection.prepareStatement("INSERT INTO Question (idQuestion, text, author) VALUES (?,?,?)");
            sql.setString(1, entity.getId().asString());
            sql.setString(2, entity.getText());


            sql.setString(3, authorId);

            int nbRow = sql.executeUpdate();
            connection.close();

            if(nbRow > 1){
                throw new IllegalArgumentException("Task went wrong");
            }

        } catch (SQLException e){
            throw new IllegalArgumentException(entity.getAuthor());
        }
    }

    @Override
    public void remove(QuestionId id) {
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("DELETE FROM Question WHERE idQuestion = ?");
            sql.setString(1, id.asString());
            int nbRow = sql.executeUpdate();
            connection.close();
        }catch(SQLException e){
            throw new IllegalArgumentException(e);
        }
    }


    @Override
    public Optional<Question> findById(QuestionId question) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM Question WHERE idQuestion = ?");
            sql.setString(1, question.asString());
            ResultSet res = sql.executeQuery();

            while(res.next()) {
                return Optional.of(getQuestion(res, connection));
            }

        } catch (SQLException e) {
            //traitement de l'exception
            throw new IllegalArgumentException(e);
        }
        return Optional.empty();
    }

    private Question getQuestion(ResultSet resultSet, Connection connection) throws SQLException {
        QuestionId questionId = new QuestionId(resultSet.getString("idQuestion"));
        String userId = resultSet.getString("author");
        String text = resultSet.getString("text");
        String author = "";

        PreparedStatement userSql = connection.prepareStatement("SELECT * FROM User WHERE idUser = ?");
        userSql.setString(1, userId);
        ResultSet resultSetUser = userSql.executeQuery();
        if (resultSetUser.next()) {
            author = resultSetUser.getString("username");
        }else{
            throw  new IllegalArgumentException("here your error");
        }

        Question submittedQuestion = Question.builder()
                .id(questionId)
                .author(author)
                .text(text)
                .build();
        return submittedQuestion;
    }
    @Override
    public int getSize() {
        int nbRow=0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("   SELECT COUNT(*) FROM Question");
            ResultSet resQ = sql.executeQuery();
            while (resQ.next()) {
                nbRow += resQ.getInt(1);
            }
        }catch (SQLException e){
            throw new IllegalArgumentException(String.valueOf(nbRow));
        }
        return nbRow;
    }
}
