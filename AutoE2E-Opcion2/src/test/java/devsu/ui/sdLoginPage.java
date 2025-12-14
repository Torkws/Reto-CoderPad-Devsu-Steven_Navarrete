package devsu.ui;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class sdLoginPage {

    public static Actor actor;
    public static final String URL_SAUCE_DEMO = "https://www.saucedemo.com/";

    public static final Target USERNAME_FIELD = Target.the("campo usuario")
            .located(By.id("user-name"));
    public static final Target PASSWORD_FIELD = Target.the("campo contraseña")
            .located(By.id("password"));
    public static final Target LOGIN_BUTTON = Target.the("boton Iniciar sesión")
            .located(By.id("login-button"));
    public static final Target PRODUCTS_TITLE = Target.the("título de la página de productos")
            .locatedBy(".title");
    public static final Target INVENTORY_SECTION = Target.the("sección de inventario")
            .located(By.id("inventory_container"));
}
