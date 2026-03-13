package com.example.zsocial.backend.users.repository.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.zsocial.backend.users.model.User;
import com.example.zsocial.backend.users.model.enums.RoleType;
import com.example.zsocial.backend.users.model.enums.UserStatus;

public class UserSpecification {
    public static Specification<User> filterByKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null) {
                return null;
            }
            String lowerKeyword = keyword.toLowerCase().trim();
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%" + lowerKeyword + "%");
        };
    }

    public static Specification<User> filterByStatus(List<UserStatus> statuses) {
        return (root, query, criteriaBuilder) -> {
            if (statuses == null || statuses.isEmpty()) {
                return null;
            }
            return criteriaBuilder.in(root.get("status")).value(statuses);
        };
    }

    public static Specification<User> filterByRole(List<RoleType> roles) {
        return (root, query, criteriaBuilder) -> {
            if (roles == null || roles.isEmpty()) {
                return null;
            }
            return criteriaBuilder.in(root.get("role")).value(roles);
        };
    }
}
