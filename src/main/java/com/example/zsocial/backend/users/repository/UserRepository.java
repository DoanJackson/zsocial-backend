package com.example.zsocial.backend.users.repository;

import com.example.zsocial.backend.users.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = {"avatar"})
    Page<User> findByFullNameContaining(String fullName, Pageable pageable);

    @EntityGraph(attributePaths = {"avatar", "cover"})
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findUserProfileById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE User u SET u.followingCount = u.followingCount + 1 WHERE u.id = :id")
    void incrementFollowingCount(@Param("id") Long id);

    @Modifying
    @Query("UPDATE User u SET u.followerCount = u.followerCount + 1 WHERE u.id = :id")
    void incrementFollowerCount(@Param("id") Long id);

    @Modifying
    @Query("UPDATE User u SET u.followingCount = u.followingCount - 1 WHERE u.id = :id")
    void decrementFollowingCount(@Param("id") Long id);

    @Modifying
    @Query("UPDATE User u SET u.followerCount = u.followerCount - 1 WHERE u.id = :id")
    void decrementFollowerCount(@Param("id") Long id);
}
