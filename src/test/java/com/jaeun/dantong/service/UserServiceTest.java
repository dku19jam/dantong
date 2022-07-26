package com.jaeun.dantong.service;

import com.jaeun.dantong.domain.entity.User;
import com.jaeun.dantong.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void join() {
        User user = new User("email11", "255", "11", "11", 11);

        userRepository.save(user);
        Optional<User> findUser = userRepository.findByEmail("email11");

        Assertions.assertThat(user.getPassword()).isEqualTo(findUser.get().getPassword());
    }
}