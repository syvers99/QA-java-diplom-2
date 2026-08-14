package ru.yandex.steps;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
public class ConfigConst {

    public static final Profile profile = new Profile();
    public static final User USER = new User(profile.getName(), profile.getEmail(), profile.getPassword());
    public static final User PASSWORD_NULL = new User(profile.getName(), profile.getEmail(), null);
    public static final User EMAIL_NULL = new User(profile.getName(), null, profile.getPassword());
    public static final User NAME_NULL = new User(null, profile.getEmail(), profile.getPassword());
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String MESSAGE_ALREADY_EXISTS = "User already exists";
    public static final String REGISTER_PATH = "/api/auth/register";
    public static final String LOGIN_PATH = "/api/auth/login";
    public static final String USER_PATH = "/api/auth/user";
    public static final String ORDERS_PATH = "/api/orders";
    public static final String MESSAGE_BAD_PROFILE = "Email, password and name are required fields";
    public static final String MESSAGE_BAD_CREDS = "email or password are incorrect";
    public static final String MESSAGE_NO_AUTHORIZATION = "You should be authorised";
    public static final String FAILED = "failed";
    static BufferedReader br;
    static {
        try {
            br = new BufferedReader(new FileReader("src/main/resources/orderOne.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static OrderData ORDER = new Gson().fromJson(br, OrderData.class);
    public static final String[] GRAY_ING = {"61c0c5a71d1f82001bdaaa6d"};
    public static final String[] BLACK_GRAY_ING = {"61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa70"};
    public static final String[] GREEN_BAD_ING = {"61c0c5a71d1f82001bdaaa70","61c0c5a71d1f82001bdaaa69"};
    public static final String[] EMPTY_ING = {};
    public static final String[] NULL_ING = null;
    public static final String[] BAD_ING = {"61c0c5a71d1f82001bdaaa69"};
    public static final Creds CREDS_EMAIL_NULL = new Creds(null,USER.getPassword());
    public static final Creds CREDS_PASSWORD_NULL = new Creds(USER.getEmail(),null);
    public static final Creds CREDS_EMAIL_BAD = new Creds(new Profile().getEmail(), USER.getPassword());
    public static final Creds CREDS_PASSWORD_BAD = new Creds(USER.getEmail(), new Profile().getPassword());

}


