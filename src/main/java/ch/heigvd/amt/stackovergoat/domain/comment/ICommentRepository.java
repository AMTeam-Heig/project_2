package ch.heigvd.amt.stackovergoat.domain.comment;

import ch.heigvd.amt.stackovergoat.application.comment.CommentsQuery;
import ch.heigvd.amt.stackovergoat.domain.IRepository;

import java.util.Collection;
import java.util.Optional;

public interface ICommentRepository extends IRepository<Comment, CommentId> {
    public Collection<Comment> find(CommentsQuery query);
    public Optional<Comment> findById(CommentId comment);
    public Collection<Comment> findAll();

}
