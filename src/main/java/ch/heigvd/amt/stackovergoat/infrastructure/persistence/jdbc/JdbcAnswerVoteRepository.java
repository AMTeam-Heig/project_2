package ch.heigvd.amt.stackovergoat.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stackovergoat.application.vote.VotesQuery;
import ch.heigvd.amt.stackovergoat.domain.vote.IVoteRepository;
import ch.heigvd.amt.stackovergoat.domain.vote.Vote;
import ch.heigvd.amt.stackovergoat.domain.vote.VoteId;
import ch.heigvd.amt.stackovergoat.infrastructure.persistence.exception.IntegrityConstraintViolationException;
import lombok.Builder;

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
@Named("JdbcAnswerVoteRepository")
public class JdbcAnswerVoteRepository implements IVoteRepository {

    @Resource(lookup = "jdbc/StackOverflowDS")
    DataSource dataSource;

    @Override
    public Collection<Vote> find(VotesQuery query) {

        if (query == null) {
            return findAll();
        }
        boolean fromAuthor    = (!query.getIdUser().equals(""));
        boolean fromSubjectId = (!query.getIdSubject().equals(""));

        if (!(fromAuthor || fromSubjectId)) {
            return findAll();
        }
        List<Vote> votes = findAll().stream()
                .filter(vote -> (
                        (fromAuthor     && vote.getUserId().equals(query.getIdUser()))       ||
                        (fromSubjectId  && vote.getSubjectId().equals(query.getIdSubject()))))
                .collect(Collectors.toList());
        return votes;
    }

    @Override
    public void save(Vote entity) throws IntegrityConstraintViolationException {
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement userSql = connection.prepareStatement("SELECT * FROM User WHERE idUser = ?");
            userSql.setString(1, entity.getUserId());
            ResultSet resultSetUser = userSql.executeQuery();
            if(!resultSetUser.next()) {
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

            PreparedStatement sql = connection.prepareStatement("INSERT INTO User_votes_for_Answer (idUser, idAnswer, isUpvote) VALUES (?,?,?)");
            sql.setString(1, entity.getUserId());
            sql.setString(2, answerId);
            sql.setString(3, entity.isUpVote() ? "true" : "false");

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
    public void remove(VoteId id) {
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("DELETE FROM User_votes_for_Answer WHERE idVote = ?");
            sql.setString(1, id.asString());
            int nbRow = sql.executeUpdate();
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
    public Optional<Vote> findById(VoteId voteId) {
        return Optional.empty();
    }

    @Override
    public Collection<Vote> findAll() {
        List<Vote> votes = new LinkedList<>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM User_votes_for_Answer");
            ResultSet resultSet = sql.executeQuery();

            while (resultSet.next()) {
                votes.add(getVote(resultSet, connection));
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return votes;
    }

    private Vote getVote(ResultSet resultSet, Connection connection) throws SQLException {
        String userId = resultSet.getString("idUser");
        boolean isUpvote = resultSet.getString("isUpvote").equals("true");
        String answerId = resultSet.getString("idAnswer");

        Vote submittedVote = Vote.builder()
                .subjectId(answerId)
                .isForAnswer(true)
                .userId(userId)
                .isUpVote(isUpvote)
                .id(new VoteId())
                .build();
        return submittedVote;
    }
}
