package com.example.zsocial.backend.social.service;

import com.example.zsocial.backend.social.dto.internal.RelationshipStatus;

public interface FollowService {

    void followUser(Long targetUserId);

    void unfollowUser(Long targetUserId);

    public RelationshipStatus getRelationShip(Long followerId, Long followingId);
}
