package ch.heigvd.amt.stackovergoat.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stackovergoat.application.comment.CommentsQuery;
import ch.heigvd.amt.stackovergoat.domain.comment.Comment;
import ch.heigvd.amt.stackovergoat.domain.comment.CommentId;
import ch.heigvd.amt.stackovergoat.domain.comment.ICommentRepository;

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
@Named("JdbcAnswerCommentRepository")
public class JdbcAnswerCommentRepository implements ICommentRepository {

    @Resource(lookup = "jdbc/StackOverflowDS")
    DataSource dataSource;

    public JdbcAnswerCommentRepository(){}

    public JdbcAnswerCommentRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Collection<Comment> find(CommentsQuery query) {
        if (query == null) {
            return findAll();
        }
        boolean fromAuthor  = (!query.getAuthor().equals(""));
        boolean fromSubjectId      = (!query.getSubjectId().equals(""));
        boolean fromText    = (!query.getComment().equals(""));

        if (!(fromAuthor || fromSubjectId || fromText)) {
            return findAll();
        }
        List<Comment> comments = findAll().stream()
                .filter(comment -> (
                        (fromAuthor     && comment.getAuthor().equals(query.getAuthor()))       ||
                        (fromSubjectId  && comment.getSubjectId().equals(query.getSubjectId())) ||
                        (fromText       && comment.getComment().equals(query.getComment()))))
                .collect(Collectors.toList());
        return comments;
    }

    @Override
    public Collection<Comment> findAll() {
        List<Comment> comments = new LinkedList<>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM User_comments_Answer");
            ResultSet resultSet = sql.executeQuery();

            while (resultSet.next()) {
                comments.add(getComment(resultSet, connection));
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return comments;
    }

    @Override
    public void save(Comment entity) {
        try {
            Connection connection = dataSource.getConnection();


            PreparedStatement userSql = connection.prepareStatement("SELECT * FROM User WHERE username = ?");
            userSql.setString(1, entity.getAuthor());
            String authorId = "";
            ResultSet resultSetUser = userSql.executeQuery();
            if(resultSetUser.next()) {
                authorId = resultSetUser.getString("idUser");
            }else{
                throw new IllegalArgumentException("insert  went wrong");
            }

            PreparedStatement queSql = connection.prepareStatement("SELECT * FROM Answer WHERE idAnswer = ?");
            queSql.setString(1, entity.getSubjectId());
            String answerId = "";
            ResultSet rs = queSql.executeQuery();
            if(rs.next()) {
                answerId = rs.getString("idAnswer");
            }else{
                throw new IllegalArgumentException("insert  went wrong");
            }

            PreparedStatement sql = connection.prepareStatement("INSERT INTO User_comments_Answer (idUser, idAnswer, comment, idComment) VALUES (?,?,?,?)");
            sql.setString(1, authorId);
            sql.setString(2, answerId);
            sql.setString(3, entity.getComment());
            sql.setString(4, entity.getId().asString());

            int nbRow = sql.executeUpdate();
            connection.close();

            if(nbRow > 1){
                throw new IllegalArgumentException("Task went wrong");
            }

        } catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void remove(CommentId id) {
        try{
            Connection connection = dataSource.getConnection();
            Optional<Comment> toDelete = findById(id);
            if (!toDelete.isEmpty()) {
                PreparedStatement sql = connection.prepareStatement("DELETE FROM User_comments_Answer WHERE idComment = ? AND idUser = ? AND idAnswer = ?");
                sql.setString(1, id.asString());
                sql.setString(2, toDelete.get().getUserId());
                sql.setString(3, toDelete.get().getSubjectId());
                int nbRow = sql.executeUpdate();
            }
            connection.close();
        }catch(SQLException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public int getSize() {
        return 0;
    }


    @Override
    public Optional<Comment> findById(CommentId comment) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM User_comments_Answer WHERE idComment = ?");
            sql.setString(1, comment.asString());
            ResultSet res = sql.executeQuery();

            while(res.next()) {
                return Optional.of(getComment(res, connection));
            }

        } catch (SQLException e) {
            // traitement de l'exception
            throw new IllegalArgumentException(e);
        }
        return Optional.empty();
    }

    private Comment getComment(ResultSet resultSet, Connection connection) throws SQLException {
        String userId = resultSet.getString("idUser");
        String id = resultSet.getString("idComment");
        String comment = resultSet.getString("comment");
        String answerId = resultSet.getString("idAnswer");
        String author = "";

        PreparedStatement userSql = connection.prepareStatement("SELECT * FROM User WHERE idUser = ?");
        userSql.setString(1, userId);
        ResultSet resultSetUser = userSql.executeQuery();
        if(resultSetUser.next()) {
            author = resultSetUser.getString("username");
        }

        Comment submittedComment = Comment.builder()
                .userId(userId)
                .id(new CommentId(id))
                .subjectId(answerId)
                .isForAnswer(true)
                .author(author)
                .comment(comment)
                .build();
        return submittedComment;
    }
}
