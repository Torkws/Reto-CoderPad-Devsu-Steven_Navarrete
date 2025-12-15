package devsu.tasks;

import devsu.ui.sdInventoryPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class sdAgregaProductosTask implements Task {

    private final List<String> products;

    public sdAgregaProductosTask(List<String> products) {
        this.products = products;
    }

    public static Performable adding(List<String> products) {
        return instrumented(sdAgregaProductosTask.class, products);
    }

    @Override
    @Step("{0} agrega productos y se redirige al carrito : #products")
    public <T extends Actor> void performAs(T actor) {
        products.forEach(product ->
                actor.attemptsTo(
                        Click.on(sdInventoryPage.getProductAddButton(product))
                )
        );

        actor.attemptsTo(
                Click.on(sdInventoryPage.CART_ICON)
        );
    }
}