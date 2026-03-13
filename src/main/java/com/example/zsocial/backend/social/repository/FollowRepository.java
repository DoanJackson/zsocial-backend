package com.example.zsocial.backend.social.repository;

import com.example.zsocial.backend.social.model.Follow;
import com.example.zsocial.backend.social.model.FollowId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {


    @Query("SELECT f FROM Follow f WHERE (f.follower.id = :currentUserId AND f.following.id = :targetUserId) OR (f.follower.id = :targetUserId AND f.following.id = :currentUserId)")
    List<Follow> findRelationshipBetweenUsers(@Param("currentUserId") Long currentUserId, @Param("targetUserId") Long targetUserId);

}
