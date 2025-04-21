package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.steps.User;
import ru.yandex.steps.Profile;


public class UserChangePositiveTest {
    Profile profile;
    User user;
    String userToken;


    @Before
    public void setUp() {
        profile = new Profile();
        user = new User(profile.getName(), profile.getEmail(), profile.getPassword());
        userToken = user.createUser();

    }

    @After
    public void tearDown() {
        user.deleteUser(userToken);
    }

    @Test
    @DisplayName("Изменить пользователя")
    @Description("Вы можете изменить данные пользователя")
    public void changeUserPositive() {
        user.setProfile(new Profile());
        user.changeUser(userToken);

    }
}
