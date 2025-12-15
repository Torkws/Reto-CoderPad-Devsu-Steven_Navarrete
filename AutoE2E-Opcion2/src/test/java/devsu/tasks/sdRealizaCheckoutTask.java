package devsu.tasks;

import devsu.ui.sdCheckoutPage;
import devsu.utils.commonUtils;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;


import static net.serenitybdd.screenplay.Tasks.instrumented;

public class sdRealizaCheckoutTask implements Task {
    private final String firstName;
    private final String lastName;
    private final String postalCode;

    public sdRealizaCheckoutTask(String firstName, String lastName, String postalCode) {
        this.firstName = firstName.equalsIgnoreCase("random") ? commonUtils.GenerarDataMock("firstName") : firstName;
        this.lastName = lastName.equalsIgnoreCase("random") ? commonUtils.GenerarDataMock("lastName") : lastName;
        this.postalCode = postalCode.equalsIgnoreCase("random") ? commonUtils.GenerarDataMock("postalCode") : postalCode;
    }

    public static Performable sending(String firstName, String lastName, String postalCode) {
        return instrumented(sdRealizaCheckoutTask.class, firstName, lastName, postalCode);
    }

    @Override
    @Step("{0} realiza el checkout con los datos: #firstName, #lastName, #postalCode")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(sdCheckoutPage.CHECKOUT_BUTTON)
        );
        actor.attemptsTo(
                Enter.theValue(firstName).into(sdCheckoutPage.FIRSTNAME_FIELD)
        );
        actor.attemptsTo(
                Enter.theValue(lastName).into(sdCheckoutPage.LASTNAME_FIELD)
        );
        actor.attemptsTo(
                Enter.theValue(postalCode).into(sdCheckoutPage.ZIP_FIELD)
        );
        actor.attemptsTo(
                Click.on(sdCheckoutPage.CONTINUE_BUTTON)
        );




    }
}
