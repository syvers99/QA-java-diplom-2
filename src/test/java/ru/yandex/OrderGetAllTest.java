package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.steps.Order;
import ru.yandex.steps.User;
import ru.yandex.steps.Profile;

import static ru.yandex.steps.ConfigConst.BLACK_GRAY_ING;
import static ru.yandex.steps.ConfigConst.ORDER;


public class OrderGetAllTest {
    Profile profile;
    User user;
    String userToken;
    Order order;


    @Before
    public void setUp() {
        profile = new Profile();
        user = new User(profile.getName(), profile.getEmail(), profile.getPassword());
        user.loginUserFail();
        userToken = user.createUser();

    }

    @After
    public void tearDown() {
        user.deleteUser(userToken);
    }

    @Test
    @DisplayName("Получить все заказы")
    @Description("Вы можете получить все заказы в системе без авторизации")
    public void getAllOrdersTest() {
        //получить все заказы
        ORDER.setIngredients(BLACK_GRAY_ING);
        order = new Order(ORDER);
        order.getAllOrders();
    }
}

