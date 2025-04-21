package ru.yandex;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import ru.yandex.steps.Client;
import ru.yandex.steps.User;
import ru.yandex.steps.Profile;


public class UserLoginNegativeTest extends Client {
    User user;
    Profile profile;



    @Before
    public void setUp() {
        profile = new Profile();
        user = new User(profile.getName(), profile.getEmail(), profile.getPassword());
    }



    //если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
    @Test
    @DisplayName("Залогиниться под несуществующим пользователем")
    @Description("Вы не можете залогиниться под несуществующим пользователем")
    public void loginCourierNegative() {
        user.loginUserFail();
    }


}