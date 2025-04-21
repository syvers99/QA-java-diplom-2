package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.steps.Order;


import static ru.yandex.steps.ConfigConst.*;
public class OrderCreateNoAuthTest {
    Order order;



    @Test
    @DisplayName("Создать заказ без авторизации")
    @Description("Вы можете создать заказ без авторизации")
    public void createOrderNoAuth() {
        ORDER.setIngredients(GRAY_ING);
        order = new Order(ORDER);
        order.createOrderNoAuth();
    }

}

