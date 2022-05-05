package com.example.streampt;

import com.example.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
class StreamPtApplicationTests {
    List<User> users;
    @BeforeEach
    void setUp() {
        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(false)
                .setEmailAddress("alice@123.com");
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setEmailAddress("bob@123.com");
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setEmailAddress("charlie@123.com");
        users = Arrays.asList(user1, user2, user3);
    }

    @Test
    @DisplayName("max값 찾기")
    void max값_찾기() {
        Optional<Integer> max = Stream.of(5,3,6,2,1)
                .max(Integer::compareTo);
        System.out.println("max = " + max.get());
    }

    @Test
    @DisplayName("양수 찾기")
    void 양수_찾기() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("유저찾기")
    void 유저찾기() {
        final User firstUser = users.stream()
                .min((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .get();
        System.out.println("firstUser = " + firstUser);
    }


}
