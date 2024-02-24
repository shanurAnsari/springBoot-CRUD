package com.demo.user.usermanagement;

import com.demo.user.usermanagement.user.User;
import com.demo.user.usermanagement.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired private UserRepository repo;

    @Test
    public void testAddNewUser(){
        User user = new User();
        user.setEmail("Mak@email.com");
        user.setPassword("password");
        user.setFirstname("Mak");
        user.setLastname("Devid");

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
        Assertions.assertThat(savedUser.getEmail()).isNotNull();
    }

    @Test
    public void testListAllUsers() {
        Iterable<User> users = repo.findAll();

        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdateUser() {
        Integer userId = 1;
        Optional<User> user = repo.findById(userId);

        User updatedUser = user.get();
        updatedUser.setPassword("updatedPassword");
        User savedUser = repo.save(updatedUser);
        Assertions.assertThat(savedUser.getPassword()).isEqualTo("updatedPassword");

    }

    @Test
    public void testGetUser() {
        Integer userId = 2;
        Optional<User> user = repo.findById(userId);
        Assertions.assertThat(user).isPresent();
        System.out.println(user);
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 3;
        repo.deleteById(userId);
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
