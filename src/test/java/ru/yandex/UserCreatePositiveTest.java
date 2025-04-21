package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.steps.User;
import ru.yandex.steps.Profile;


public class UserCreatePositiveTest {
    Profile profile;
    User user;
    String userToken;


    @Before
    public void setUp() {
        profile = new Profile();
        user = new User(profile.getName(), profile.getEmail(), profile.getPassword());
        user.loginUserFail();

    }

    @After
    public void tearDown() {
        user.deleteUser(userToken);
    }

    @Test
    @DisplayName("Создать пользователя")
    @Description("вы можете создать нового пользователя")
    public void createUserPositive() {
        userToken = user.createUser();
    }
}


