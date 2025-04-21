package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.steps.User;
import ru.yandex.steps.Profile;

import static ru.yandex.steps.ConfigConst.FAILED;


public class UserChangeNegativeTest {
    Profile profile;
    User user;
    String userToken;


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
    @DisplayName("Изменить пользователя с невалидным токеном")
    @Description("Вы не можете изменить данные пользователя используя невалидный токен")
    public void changeUserNegative() {
        user.setProfile(new Profile());
        user.changeUserFail(FAILED);
    }
}

