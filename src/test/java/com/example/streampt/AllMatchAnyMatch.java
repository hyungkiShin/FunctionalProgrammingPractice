package com.example.streampt;

import com.example.entity.Order;
import com.example.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class AllMatchAnyMatch {

    List<Integer> numbers = Arrays.asList(3, -4, 2, 7, 9);
    List<User> users;
    List<Order> orders;
    LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

    @BeforeEach
    void setUp() {

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(false)
                .setCreatedAt(now.minusDays(2))
                .setEmailAddress("alice@123.com");
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setCreatedAt(now.minusHours(10))
                .setEmailAddress("bob@123.com");
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setCreatedAt(now.minusHours(1))
                .setEmailAddress("charlie@123.com");
        User user4 = new User()
                .setId(103)
                .setName("David")
                .setVerified(true)
                .setCreatedAt(now.minusHours(27))
                .setEmailAddress("david@123.com");
        users = Arrays.asList(user1, user2, user3, user4);

        Order order1 = new Order()
                .setId(1001L)
                .setAmount(BigDecimal.valueOf(2000))
                .setStatus(Order.OrderStatus.CREATED);
        Order order2 = new Order()
                .setId(1002L)
                .setAmount(BigDecimal.valueOf(4000))
                .setStatus(Order.OrderStatus.ERROR);
        Order order3 = new Order()
                .setId(1003L)
                .setAmount(BigDecimal.valueOf(7000))
                .setStatus(Order.OrderStatus.ERROR);
        Order order4 = new Order()
                .setId(1004L)
                .setAmount(BigDecimal.valueOf(5000))
                .setStatus(Order.OrderStatus.CREATED);

        orders = Arrays.asList(order1, order2, order3, order4);
    }
    
    @Test
    @DisplayName("모든 숫자가 양수인지")
    void 모든_숫자가_양수인지() {
        boolean allPostive = numbers.stream()
                        .allMatch(number -> number > 0);
        System.out.println("allPostive = " + allPostive);
    }
    
    @Test
    @DisplayName("모든 숫자가 음수인지")
    void 모든_숫자가_음수인지() {
        final boolean anyNegative = numbers.stream()
                .anyMatch(number -> number < 0);
        System.out.println("anyNegative = " + anyNegative);
    }
    
    @Test
    @DisplayName("전부 검증이 된 유저인지")
    void 전부_검증이_된_유저인지() {
        final boolean areAllUserVerfied = users.stream()
                .allMatch(User::isVerified);

        System.out.println("areAllUserVerfied = " + areAllUserVerfied);
    }
    
    @Test
    @DisplayName("에러상태인 유저가 하나라도 있는지")
    void 에러상태인_유저가_하나라도_있는지() {
        final boolean b = orders.stream()
                .anyMatch((or) -> or.getStatus() == Order.OrderStatus.ERROR);
        System.out.println("b = " + b);

    }

}
