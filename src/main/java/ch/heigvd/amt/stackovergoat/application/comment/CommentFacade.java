package ch.heigvd.amt.stackovergoat.application.comment;

import ch.heigvd.amt.stackovergoat.domain.answer.AnswerId;
import ch.heigvd.amt.stackovergoat.domain.comment.Comment;
import ch.heigvd.amt.stackovergoat.domain.comment.CommentId;
import ch.heigvd.amt.stackovergoat.domain.comment.ICommentRepository;
import ch.heigvd.amt.stackovergoat.infrastructure.persistence.exception.IntegrityConstraintViolationException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CommentFacade {
    private ICommentRepository commentRepository;

    public CommentFacade(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void proposeComment(ProposeCommentCommand command) {
        if(command != null) {
            Comment submittedComment = Comment.builder()
                    .subjectId(command.getSubjectId())
                    .author(command.getAuthor())
                    .comment(command.getComment())
                    .isForAnswer(command.isForAnswer())
                    .build();
            try {
                commentRepository.save(submittedComment);
            } catch (IntegrityConstraintViolationException e) {
                e.printStackTrace();
            }
        }
    }

    public CommentsDTO getComments(CommentsQuery query) {
        Collection<Comment> allComments = commentRepository.find(query);

        List<CommentsDTO.CommentDTO> allCommentsDTO = allComments.stream()
                .map(comment -> CommentsDTO.CommentDTO.builder()
                        .idUser(comment.getUserId())
                        .idSubject(comment.getSubjectId())
                        .comment(comment.getComment())
                        .author(comment.getAuthor())
                        .id(comment.getId().asString())
                        .build())
                .collect(Collectors.toList());

        return CommentsDTO.builder()
                .comments(allCommentsDTO)
                .build();
    }

    public CommentsDTO getAllComments() {
        Collection<Comment> allComments = commentRepository.findAll();

        List<CommentsDTO.CommentDTO> allCommentsDTO = allComments.stream()
                .map(comment -> CommentsDTO.CommentDTO.builder()
                        .idUser(comment.getUserId())
                        .idSubject(comment.getSubjectId())
                        .comment(comment.getComment())
                        .author(comment.getAuthor())
                        .id(comment.getId().asString())
                        .build())
                .collect(Collectors.toList());

        return CommentsDTO.builder()
                .comments(allCommentsDTO)
                .build();
    }

    public void remove(String id) {
        commentRepository.remove(new CommentId(id));
    }
}
