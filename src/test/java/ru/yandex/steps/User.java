package ru.yandex.steps;


import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import lombok.Setter;

import java.net.HttpURLConnection;
import static org.hamcrest.Matchers.*;
import static ru.yandex.steps.ConfigConst.*;

@Getter
@Setter
public class User extends Client {
    private String name;
    private String email;
    private String password;
    private Profile profile;
    private Creds creds;

    public User(String firstName, String email, String password) {
        this.name = firstName;
        this.email = email;
        this.password = password;
        profile = new Profile(firstName, email, password);
        creds = new Creds(email, password);

    }


    @Step("создать пользователя")
    public String createUser() {
        try {
            return
                    create(profile)
                            .statusCode(HttpURLConnection.HTTP_OK)
                            .assertThat().body("success", is(true))
                            .extract()
                            .path("accessToken").toString();
        } catch (NullPointerException e) {
            return FAILED;
        }

    }

    @Step("залогинить пользователя")
    public String loginUser() {
        try {
            return
                    login(creds)
                            .statusCode(HttpURLConnection.HTTP_OK)
                            .assertThat().body("success", is(true))
                            .extract()
                            .path("accessToken").toString();
        } catch (NullPointerException e) {
            return FAILED;
        }
    }

    @Step("удалить пользователя")
    public void deleteUser(String userToken) {

        spec()
                .header("Authorization", userToken)
                .log().all()
                .when()
                .delete(USER_PATH)
                .then()
                .log().all()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED)
                .assertThat().body("success", is(true));

    }

    @Step("залогинить пользователя(пользователя нет в системе)")
    public void loginUserFail() {

        login(creds)
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .assertThat().body("message", is(MESSAGE_BAD_CREDS));


    }

    @Step("изменить пользователя")
    public void changeUser(String userToken) {
        change(userToken)
                .statusCode(HttpURLConnection.HTTP_OK)
                .assertThat().body("success", is(true));


    }

    @Step("изменить пользователя(без авторизации)")
    public void changeUserFail(String userToken) {
        change(userToken)
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .assertThat().body("message", is(MESSAGE_NO_AUTHORIZATION));

    }


    @Step("создать пользователя(пользователь уже существует)")
    public void createUserFail(Profile profile) {
        create(profile)
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .assertThat().body("message", is(MESSAGE_ALREADY_EXISTS));
    }


    @Step("создать пользователя (bad profile)")
    public void createUserBadRequest(Profile badProfile) {
        create(badProfile)
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .assertThat().body("message", is(MESSAGE_BAD_PROFILE));
    }

    @Step("залогинить пользователя(bad creds) ")
    public void loginUserWrong(Creds wrongCreds) {
        login(wrongCreds)
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .assertThat().body("message", is(MESSAGE_BAD_CREDS));
    }

    public ValidatableResponse login(Creds creds) {
        return spec()
                .body(creds)
                .log().all()
                .when()
                .post(LOGIN_PATH)
                .then().log().all();
    }

    public ValidatableResponse create(Profile profile) {
        return spec()
                .body(profile)
                .log().all()
                .when()
                .post(REGISTER_PATH)
                .then().log().all();
    }



    public ValidatableResponse change(String userToken) {
        return spec()
                .header("Authorization", userToken)
                .body(profile)
                .log().all()
                .when()
                .patch(USER_PATH)
                .then().log().all();
    }
}

