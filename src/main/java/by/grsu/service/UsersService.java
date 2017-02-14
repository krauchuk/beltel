package by.grsu.service;

import by.grsu.entity.Users;

public interface UsersService {
    Users getByUsername(String username);
}
