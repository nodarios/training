package com.example.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthenticationFailureService {

    private static final int MAX_ATTEMPT = 3;

    private final LoadingCache<String, Integer> attemptsCache;

    public AuthenticationFailureService() {
        attemptsCache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<>() {
                            @Override
                            public Integer load(final String key) {
                                return 0;
                            }
                        }
                );
    }

    public void registerFailedLogin(final String username) {
        log.error("Registering Failed Login {}", username);
        int attempts;
        try {
            attempts = attemptsCache.get(username);
        } catch (final ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(username, attempts);
    }

    public boolean isUserBlocked(String username) {
        try {
            return attemptsCache.get(username) >= MAX_ATTEMPT;
        } catch (final ExecutionException e) {
            return false;
        }
    }

    public Map<String, Integer> getBlockedUsers() {
        return attemptsCache
                .asMap()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= MAX_ATTEMPT)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
