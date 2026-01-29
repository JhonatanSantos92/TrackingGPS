package com.gps.tracking.service;

import com.gps.tracking.clients.UserClient;
import com.gps.tracking.dto.UserDTO;
import com.gps.tracking.dto.UserFallbackDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class UserService {

    @Inject
    @RestClient
    UserClient userClient;

    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 10
    )
    @Fallback(fallbackMethod = "fallbackUser")
    public UserDTO getById(Long id) {
        return userClient.getById(id);
    }

    public UserDTO fallbackUser(Long id) {
        return UserFallbackDTO.empty(id);
    }
}
