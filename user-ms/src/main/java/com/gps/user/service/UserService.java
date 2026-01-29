package com.gps.user.service;

import com.gps.user.dto.CreateUserDTO;
import com.gps.user.dto.UpdateUserDTO;
import com.gps.user.entity.User;
import com.gps.user.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User create(CreateUserDTO dto) {
        User user = new User();
        user.nombre = dto.nombre;
        user.correo = dto.correo;
        user.status = "ACTIVE";
        repository.persist(user);
        return user;
    }

    public List<User> findAll() {
        return repository.listAll();
    }

    public User findById(Long id) {
        User user = repository.findById(id);
        if (user == null) {
            throw new NotFoundException("User not found with id: " + id);
        }
        return user;
    }

    @Transactional
    public User update(Long id, UpdateUserDTO dto) {
        User user = repository.findById(id);
        if (user == null) {
            throw new NotFoundException("User not found with id: " + id);
        }

        if (dto.nombre != null) user.nombre = dto.nombre;
        if (dto.correo != null) user.correo = dto.correo;
        if (dto.status != null) user.status = dto.status;

        repository.persist(user);
        return user;
    }

    @Transactional
    public void delete(Long id) {
        User user = repository.findById(id);
        if (user == null) {
            throw new NotFoundException("User not found with id: " + id);
        }
        repository.delete(user);
    }
}