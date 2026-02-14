package com.gps.user.service;

import com.gps.user.dto.UserDTO;
import com.gps.user.entity.User;
import com.gps.user.repository.UserRepository;
import exception.BusinessException;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository repository;

    public Uni<UserDTO> create(UserDTO dto) {
        User user = new User();
        user.nombre = dto.nombre;
        user.correo = dto.correo;
        return repository.persist(user)
                .onItem().transform(p -> {
                    UserDTO created = new UserDTO();
                    created.nombre = p.nombre;
                    created.correo = p.correo;
                    return created;
                });
    }

    @WithSession
    public Uni<List<User>> findAll() {
        return repository.listAll();
    }


    public Multi<User> list() {
        return this.findAll()
                .onItem()
                .transformToMulti(list -> Multi.createFrom().iterable(list));
    }

    public Uni<User> get(Long id) {
        return repository.findById(id)
                .onItem().ifNull().failWith(() ->
                        new BusinessException(
                                404,
                                "USER_NOT_FOUND",
                                "User not found with id: " + id
                        )
                );
    }

    @WithTransaction
    public Uni<User> update(Long id, UserDTO user) {
        return repository.findById(id)
                .onItem().ifNull().failWith(() ->
                        new BusinessException(
                                404,
                                "USER_NOT_FOUND",
                                "User not found with id: " + id
                        )
                )
                .flatMap(existing -> {
                    existing.nombre = user.nombre;
                    existing.correo = user.correo;
                    return repository.persist(existing);
                });
    }

    @WithTransaction
    public Uni<Void> delete(Long id) {
        return repository.findById(id)
                .onItem().ifNull().failWith(() ->
                        new BusinessException(
                                404,
                                "USER_NOT_FOUND",
                                "User not found with id: " + id
                        )
                )
                .flatMap(user -> repository.delete(user));
    }

}