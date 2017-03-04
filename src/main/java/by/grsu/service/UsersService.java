package by.grsu.service;

import by.grsu.entity.Users;

import java.util.List;

public interface UsersService {
    Users getByUsername(String username);
    List<Users> getByEmployerId(long id);
}
