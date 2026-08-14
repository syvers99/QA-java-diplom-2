package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.steps.Order;
import ru.yandex.steps.Profile;
import ru.yandex.steps.User;
import static ru.yandex.steps.ConfigConst.*;


@RunWith(Parameterized.class)
public class OrderCreatePositiveTest {
    String[] ingredients;
    Order order;
    String userToken;
    User user;
    Profile profile;

    public OrderCreatePositiveTest(String[] ingredients){
        this.ingredients = ingredients;
    }


    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][]{
                {GRAY_ING},
                {BLACK_GRAY_ING},
                {GREEN_BAD_ING},
        };
    }

    @Test
    @DisplayName("Создать заказ с ингредиентами")
    @Description("1.Вы можете создать заказ с одним ингредиентом;" +
            "2.Вы можете создать заказ с несколькими ингредиентами;" +
            "3.Вы можете создать заказ с несколькими ингредиентами,даже если один из них не существует;")
    public void createOrderPositiveTest() {
        // задать ингредиенты
        ORDER.setIngredients(ingredients);
        order = new Order(ORDER);
        profile = new Profile();
        user = new User(profile.getName(), profile.getEmail(), profile.getPassword());
        userToken = user.createUser();
        // сoздать  заказ
        order.createOrder(userToken);
        // получить заказ
        order.getOrders(userToken);



    }
    @After
    public void tearDown() {
        user.deleteUser(userToken);
    }
}
