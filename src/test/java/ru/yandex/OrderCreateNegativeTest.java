package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.yandex.steps.User;
import ru.yandex.steps.Order;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.yandex.steps.Profile;

import static ru.yandex.steps.ConfigConst.*;


@RunWith(Parameterized.class)
public class OrderCreateNegativeTest {
    String[] ingredients;
    Order order;
    String userToken;
    User user;
    Profile profile;

    public OrderCreateNegativeTest(String[] ingredients){
        this.ingredients = ingredients;
    }


    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][]{
                {BAD_ING},
                {EMPTY_ING},
                {NULL_ING},
        };
    }

    @Test
    @DisplayName("Заказ с невалидными ингредиентами")
    @Description("1.Вы не можете сделать заказ с несуществующим ингредиентом;\n" +
            "2.Вы не можете сделать заказ без ингредиентов;\n" +
            "3.Вы не можете сделать заказ без параметра ingredients;")
    public void createOrderPositiveTest() {
        // задать ингредиенты
        ORDER.setIngredients(ingredients);
        order = new Order(ORDER);
        profile = new Profile();
        user = new User(profile.getName(), profile.getEmail(), profile.getPassword());
        userToken = user.createUser();
        // сoздать  заказ
        order.createOrderFail(userToken);
        // получить заказы клиента
        order.getOrdersNo(userToken);

    }
    @After
    public void tearDown() {
        user.deleteUser(userToken);
    }
}
