
package ru.yandex;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.steps.Creds;
import ru.yandex.steps.User;
import static ru.yandex.steps.ConfigConst.*;

@RunWith(Parameterized.class)
public class UserLoginNegativeParameterizedTest {
    User user;
    Creds creds;
    String userToken;
    public UserLoginNegativeParameterizedTest (User user,Creds creds){
        this.user = user;
        this.creds = creds;
    }



    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][]{
                {USER,CREDS_EMAIL_NULL},
                {USER,CREDS_PASSWORD_NULL},
                {USER,CREDS_EMAIL_BAD},
                {USER,CREDS_PASSWORD_BAD}

        };
    }



    @Test
    @DisplayName("Залогиниться с неверными данными")
    @Description("Вы не можете залогиниться, если:" +
            "1.Отсутствует  email" +
            "2.Отсутствует пароль" +
            "3.Неверный email" +
            "4.Неверный пароль")
    public void loginCourierWithBadCreds() {
        userToken = user.createUser();
        user.loginUserWrong(creds);
    }

    @After
    public void tearDown() {
        user.deleteUser(userToken);
    }
}

