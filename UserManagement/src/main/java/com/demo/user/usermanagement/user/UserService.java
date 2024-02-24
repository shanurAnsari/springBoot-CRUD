package com.demo.user.usermanagement.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException("User not found.");
    }

    public void deleteUserById(Integer id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("User not found.");
        }
        userRepository.deleteById(id);
    }
}
