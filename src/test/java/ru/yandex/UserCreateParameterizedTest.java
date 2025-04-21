package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.steps.User;
import static ru.yandex.steps.ConfigConst.*;

@RunWith(Parameterized.class)
public class UserCreateParameterizedTest {
    User user;
    public UserCreateParameterizedTest (User user){
        this.user = user;
    }

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);


    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][]{
                {PASSWORD_NULL},
                {EMAIL_NULL},
                {NAME_NULL}

        };
    }



    @Test
    @DisplayName("Создать пользователя без обязательного поля")
    @Description("Вы не можете создать пользователя если отсутствует хотя бы одно обязательное поле")
    public void createCourierWithoutField() {
        user.createUserBadRequest(user.getProfile());
    }
}


