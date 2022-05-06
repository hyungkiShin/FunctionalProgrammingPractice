package com.example.streampt;

import com.example.entity.Order;
import com.example.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class MinMaxCount {

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

    @Test
    @DisplayName("최근 24 시간 이내에 가입한 유저들중 Email 이 검증되지 않은 유저")
    void 가입_24시간전_이메일검증되지_않은_유저찾기() {
        long unverifiedUsersIn24Hrs = users.stream()
                .filter(user -> user.getCreatedAt().isAfter(now.minusDays(1)))
                .filter(user -> !user.isVerified())
                .count();
        System.out.println("count = " + unverifiedUsersIn24Hrs);
    }
    
    @Test
    @DisplayName("가장 비싼 주문을 한 유저")
    void 에러상태이면서_가장_비싼_주문을_한_유저() {
        Order order = orders.stream()
                .filter((u1) -> u1.getStatus() == Order.OrderStatus.ERROR)
                .max((o1, o2) -> o1.getAmount().compareTo(o2.getAmount())).get();
        System.out.println("order = " + order);
    }

    @Test
    @DisplayName("가장비싼 주문을 한 유저")
    void 가장비싼_주문을_한_유저() {
        final BigDecimal bigDecimal = orders.stream()
                .filter(order -> order.getStatus() == Order.OrderStatus.ERROR)
                .map(Order::getAmount)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        System.out.println("bigDecimal = " + bigDecimal);
    }
}
