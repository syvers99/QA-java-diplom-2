package ru.yandex.steps;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.*;
import static ru.yandex.steps.ConfigConst.*;
@AllArgsConstructor
public class Order extends Client {
    OrderData order;
    @Step("создать заказ")
    public void createOrder(String userToken) {
        spec()
                .header("Authorization", userToken)
                .body(order)
                .when()
                .post(ORDERS_PATH)
                .then().log().all()
                .statusCode(HttpURLConnection.HTTP_OK)
                .assertThat().body("order",notNullValue());
    }

    @Step("создать заказ без авторизации")
    public void createOrderNoAuth() {
        spec()
                .body(order)
                .when()
                .post(ORDERS_PATH)
                .then().log().all()
                .statusCode(HttpURLConnection.HTTP_OK)
                .assertThat().body("order",notNullValue());
    }

    @Step("получить заказы пользователя")
    public void getOrders(String userToken){
        spec()
                .header("Authorization", userToken)
                .when()
                .get(ORDERS_PATH)
                .then().log().all()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders.size()", greaterThan(0));
    }

    @Step("получить заказы (нет заказов)")
    public void getOrdersNo(String userToken){
        spec()
                .header("Authorization", userToken)
                .when()
                .get(ORDERS_PATH)
                .then().log().all()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders.size()", equalTo(0));


    }

    @Step("получить заказы пользователя (без авторизации)")
    public void getOrderFail(String userToken){
        spec()
                .header("Authorization", userToken)
                .when()
                .get(ORDERS_PATH)
                .then().statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .assertThat().body("message",is(MESSAGE_NO_AUTHORIZATION))
                .log().all();

    }




    @Step("получить все заказы в системе")
    public void getAllOrders(){
        spec()
                .get(ORDERS_PATH + "/all")
                .then().statusCode(HttpURLConnection.HTTP_OK)
                .assertThat().body("orders",notNullValue());

    }
    @Step("создать заказ (без ингредиентов)")
    public void createOrderFail (String userToken) {
         spec()
                .header("Authorization", userToken)
                .body(order)
                .when()
                .post(ORDERS_PATH)
                .then().statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .log().all()
                .assertThat().body("message", notNullValue());
    }

}


