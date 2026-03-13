package com.example.zsocial.backend.users.repository;

import com.example.zsocial.backend.users.model.UserOAuthToken;
import com.example.zsocial.backend.users.model.enums.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<UserOAuthToken, Long> {
    Optional<UserOAuthToken> findByUserIdAndProvider(Long userId, ProviderType provider);
}
