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
public class OrderGetNegativeTest {
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
    // получить все заказы
    @Test
    @DisplayName("Получить заказ с невалидным токеном")
    @Description("Вы не можете получить заказы клиента с невалидным токеном")
    public void getOrdersAllTest() {

        userToken = user.createUser();
        // сoздать и принять  заказ
        order.createOrder(userToken);
        order.getOrderFail(FAILED);
    }

}
