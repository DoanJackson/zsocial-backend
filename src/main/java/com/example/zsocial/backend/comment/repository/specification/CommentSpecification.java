package com.example.zsocial.backend.comment.repository.specification;

import com.example.zsocial.backend.comment.model.Comment;
import org.springframework.data.jpa.domain.Specification;

public class CommentSpecification {

    public static Specification<Comment> filterByPostId(Long postId) {
        return (root, query, criteriaBuilder) -> {
            if (postId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("post").get("id"), postId);
        };
    }

}
