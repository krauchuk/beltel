package by.grsu.service.Impl;

import by.grsu.entity.Users;
import by.grsu.repository.UsersRepository;
import by.grsu.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImp implements UsersService {
    @Autowired
    UsersRepository service;

    public Users getByUsername(String username) {
        return service.getUserByUsername(username);
    }
}
