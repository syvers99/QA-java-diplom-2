package ru.yandex;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.steps.User;
import ru.yandex.steps.Profile;


import static org.junit.Assert.*;
import static ru.yandex.steps.ConfigConst.FAILED;


public class UserLoginPositiveTest {
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
    @DisplayName("Залогинить пользователя")
    @Description("Если вы зарегистрированы, вы можете успешно залогиниться")
    public void loginUserThereIsToken() {
        assertNotEquals(FAILED,user.loginUser());
    }
}
