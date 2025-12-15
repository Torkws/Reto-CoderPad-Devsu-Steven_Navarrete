package devsu.ui;

import net.serenitybdd.screenplay.targets.Target;

public class sdCheckoutPage {

    public static Target CHECKOUT_BUTTON = Target.the("Botón de Checkout")
            .locatedBy("//button[@id='checkout']");
    public static Target FIRSTNAME_FIELD = Target.the("")
            .locatedBy("//input[@id='first-name']");
    public static Target LASTNAME_FIELD = Target.the("")
            .locatedBy("//input[@id='last-name']");
    public static Target ZIP_FIELD = Target.the("")
            .locatedBy("//input[@id='postal-code']");
    public static Target CONTINUE_BUTTON = Target.the("")
            .locatedBy("//input[@id='continue']");

    public static Target TITLE_CHECKOUT_OVERVIEW = Target.the("Título de la página de resumen de checkout")
            .locatedBy("//span[@data-test='title']");
    public static Target FINISH_BUTTON = Target.the("Botón de finalizar orden")
            .locatedBy("//button[@id='finish']");
}
