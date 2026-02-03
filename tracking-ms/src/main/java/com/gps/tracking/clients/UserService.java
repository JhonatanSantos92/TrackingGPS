package com.gps.tracking.clients;

import com.gps.tracking.dto.UserDTO;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class UserService {

    @Inject
    @RestClient
    UserClient userClient;

    @Timeout(3000)
    @Retry(maxRetries = 2, delay = 500)
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 10
    )
    @Fallback(fallbackMethod = "fallbackUser")
    public Uni<UserDTO> getById(Long id) {
        return userClient.getById(id);
    }

    public Uni<UserDTO> fallbackUser(Long id) {
        return Uni.createFrom().item(
                new UserDTO() {{
                    this.id = id;
                    this.nombre = "Usuario no disponible";
                    this.correo = "N/A";
                    this.status = "INACTIVO";
                }}
        );
    }
}
