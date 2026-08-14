package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.steps.User;
import ru.yandex.steps.Order;
import ru.yandex.steps.Profile;

import static ru.yandex.steps.ConfigConst.*;
public class OrderGetPositiveTest {
    Order order;
    User user;
    String userToken;
    Profile profile;


    @Before
    public void setUp() {
        order = new Order(ORDER);
        profile = new Profile();
        user = new User(profile.getName(), profile.getEmail(), profile.getPassword());

    }
    @After
    public void tearDown() {
        user.deleteUser(userToken);

    }
    @Test
    @DisplayName("Получить все заказы клиента")
    @Description("Вы можете получить все заказы клиента")
    public void getAllUserOrdersTest() {

        userToken = user.createUser();
        order.createOrder(userToken);
        order.getOrders(userToken);
    }

}
