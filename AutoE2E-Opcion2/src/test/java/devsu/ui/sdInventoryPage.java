package devsu.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;


public class sdInventoryPage {


    public static Target CART_ICON = Target.the("Icono del carrito de compras")
            .locatedBy("//a[@class='shopping_cart_link']");
    public static Target getProductAddButton(String productName) {
        return Target.the("Botón del producto: " + productName)
                .located(By.id("add-to-cart-{0}".replace("{0}", productName.toLowerCase().replace(" ", "-")))
                        );
    }

    public static Target getProductPrice(String productName) {
        return Target.the("Precio del producto: " + productName)
                .locatedBy("//div[@data-test='inventory-item-name' and normalize-space(text())='{0}']/ancestor::div[@data-test='inventory-item']//div[@data-test='inventory-item-price']"
                        .replace("{0}", productName));
    }


    public static Target getCartProduct(String productName) {
        return Target.the("Product en el carrito: " + productName)
                .locatedBy("//div[@class='inventory_item_name' and normalize-space(text())='{0}']"
                        .replace("{0}", productName));
    }

}
