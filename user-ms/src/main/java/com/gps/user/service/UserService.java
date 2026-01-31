package com.gps.user.service;

import com.gps.user.entity.User;
import exception.BusinessException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Transactional
    public User create(String nombre, String correo) {
        User user = new User();
        user.nombre = nombre;
        user.correo = correo;
        user.status = "ACTIVE";

        user.persist();
        return user;
    }

    public List<User> findAll() {
        return User.listAll();
    }

    public User findById(Long id) {
        User user = User.findById(id);
        if (user == null) {
            throw new BusinessException(404,
                    "USER_NOT_FOUND",
                    "User not found with id: " + id
            );
        }
        return user;
    }

    @Transactional
    public User update(Long id, String nombre, String correo, String status) {
        User user = User.findById(id);
        if (user == null) {
            throw new BusinessException(404,
                    "USER_NOT_FOUND",
                    "User not found with id: " + id
            );
        }

        if (nombre != null) user.nombre = nombre;
        if (correo != null) user.correo = correo;
        if (status != null) user.status = status;

        return user;
    }

    @Transactional
    public boolean delete(Long id) {
        User user = User.findById(id);
        if (user == null) {
            return false;
        }

        user.delete();
        return true;
    }
}