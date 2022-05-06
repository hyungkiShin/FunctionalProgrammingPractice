package com.example.streampt;

import com.example.entity.Order;
import com.example.entity.OrderLine;
import com.example.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reduce {
    List<Integer> numbers = Arrays.asList(1, 4, -2, -5, 3);
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
                .setFriendUserIds(Arrays.asList(201,202,203,204))
                .setEmailAddress("alice@123.com");
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setCreatedAt(now.minusHours(10))
                .setFriendUserIds(Arrays.asList(204,205,206))
                .setEmailAddress("bob@123.com");
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setCreatedAt(now.minusHours(1))
                .setFriendUserIds(Arrays.asList(204,205,207))
                .setEmailAddress("charlie@123.com");

        users = Arrays.asList(user1, user2, user3);

        Order order1 = new Order()
                .setId(1001L)
                .setAmount(BigDecimal.valueOf(2000))
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))
                        ))
                .setStatus(Order.OrderStatus.CREATED);
        Order order2 = new Order()
                .setId(1002L)
                .setAmount(BigDecimal.valueOf(4000))
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(2000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(3000))
                ))
                .setStatus(Order.OrderStatus.ERROR);
        Order order3 = new Order()
                .setId(1003L)
                .setAmount(BigDecimal.valueOf(7000))
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))
                ))
                .setStatus(Order.OrderStatus.ERROR);

        orders = Arrays.asList(order1, order2, order3);
    }
    @Test
    @DisplayName("배열 요소의 합")
    void 배열_요소의_합() {
        Integer sum = numbers.stream().reduce((x, y) -> x + y).get();
        System.out.println("sum = " + sum);
    }

    @Test
    @DisplayName("min 구하기")
    void min_구하기() {
        final Integer min = numbers.stream().reduce((x, y) -> {
            if (x < y) {
                return x;
            }
            return y;
        }).get();
        System.out.println("min = " + min);
    }

    @Test
    @DisplayName("모든 합의 곱")
    void 모든_합의_곱() {
        final Integer product = numbers.stream().reduce(1, (x, y) -> x * y);
        System.out.println("numbers = " + product);
    }

    @Test
    @DisplayName("parse String number")
    void parse_String_number() {
        List<String> strings = Arrays.asList("3", "2", "5", "-4");
        Integer parseIntSum = strings.stream()
                .map(Integer::parseInt)
                .reduce(0, (x, y) -> x + y);
        System.out.println("reduce = " + parseIntSum);
    }

    @Test
    @DisplayName("parse String number")
    void parse_String_number2() {
        List<String> strings = Arrays.asList("3", "2", "5", "-4");
        Integer reduce = strings.stream()
                .reduce(0, (number, str) -> number + Integer.parseInt(str), (num1, num2) -> num1 + num2);
        System.out.println("reduce = " + reduce);
    }

    @Test
    @DisplayName("users")
    void users() {
        final Integer usersfriendsSum = users.stream().map(User::getFriendUserIds)
                .map(List::size)
                .reduce(0, (x, y) -> x + y);
        System.out.println("usersfriendsSum = " + usersfriendsSum);
    }
    
    @Test
    @DisplayName("find the sum of amount")
    void find_the_sum_of_amount() {
        final BigDecimal sumOfAmount = orders.stream()
                .map(Order::getOrderLines)
                .flatMap(List::stream)
                .map(OrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("sumOfAmount = " + sumOfAmount);
    }
}
