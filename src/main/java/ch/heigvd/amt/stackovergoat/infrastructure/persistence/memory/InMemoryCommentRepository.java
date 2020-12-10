package ch.heigvd.amt.stackovergoat.infrastructure.persistence.memory;

import ch.heigvd.amt.stackovergoat.application.comment.CommentsQuery;
import ch.heigvd.amt.stackovergoat.domain.comment.Comment;
import ch.heigvd.amt.stackovergoat.domain.comment.CommentId;
import ch.heigvd.amt.stackovergoat.domain.comment.ICommentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryCommentRepository extends InMemoryRepository<Comment, CommentId> implements ICommentRepository {
    @Override
    public Collection<Comment> find(CommentsQuery query) {
        if (query == null) {
            return findAll();
        }
        boolean fromAuthor  = (!query.getAuthor().equals(""));
        boolean fromId      = (!query.getSubjectId().equals(""));
        boolean fromComment = (!query.getComment().equals(""));

        if (!(fromAuthor || fromId || fromComment)) {
            return findAll();
        }
        List<Comment> comments = findAll().stream()
                .filter(comment -> (
                        (fromAuthor && comment.getAuthor().equals(query.getAuthor()))              ||
                        (fromId     && comment.getSubjectId().equals(query.getSubjectId()))   ||
                        (fromComment   && comment.getComment().equals(query.getComment()))))
                .collect(Collectors.toList());
        return comments;
    }

}
