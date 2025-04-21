package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.steps.User;
import ru.yandex.steps.*;


public class UserCreateNegativeTest {
    User user;
    User newUser;
    Profile profile;
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
    @DisplayName("Создать двух идентичных пользователей")
    @Description ("Вы не можете создать двух идентичных пользователей")
    public void createCourierDouble() {
        userToken = user.createUser();
        user.createUserFail(user.getProfile());
    }


    @Test
    @DisplayName("Создать пользователя с уже существующим email")
    @Description("Вы не можете создать пользователя с уже существующим email")
    public void createCourierDoubleEmail() {
        userToken = user.createUser();
        profile = new Profile();
        newUser = new User(profile.getName(), user.getEmail(), profile.getPassword());
        user.createUserFail(newUser.getProfile());

    }
}

