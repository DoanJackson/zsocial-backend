package com.example.zsocial.backend.comment.repository;

import com.example.zsocial.backend.comment.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
    Optional<Comment> findByIdAndUserId(Long commentId, Long userId);

    boolean existsByParentCommentId(Long parentCommentId);

    Long countByPostId(Long postId);

    @Query("SELECT c.post.id, COUNT(c) FROM Comment c WHERE c.post.id in :ids AND c.deletedAt IS NULL GROUP BY c.post.id")
    List<Object[]> countByPostIds(List<Long> ids);

    @EntityGraph(attributePaths = {"user"})
    Page<Comment> findAllByPostIdAndLevel(Long postId, Integer level, Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    Page<Comment> findAllByParentCommentId(Long parentCommentId, Pageable pageable);

    @Query("SELECT c.parentComment.id, COUNT(c) FROM Comment c " +
            "WHERE c.parentComment.id IN :parentIds AND c.deletedAt IS NULL " +
            "GROUP BY c.parentComment.id")
    List<Object[]> countRepliesForParents(@Param("parentIds") List<Long> parentIds);
}
