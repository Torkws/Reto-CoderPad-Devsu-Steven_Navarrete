package devsu.stepDefinitions;

import devsu.tasks.sdNavegarPaginaTask;
import devsu.ui.sdLoginPage;
import io.cucumber.java.en.Given;

import net.serenitybdd.screenplay.actors.OnStage;

import static devsu.ui.sdLoginPage.*;
import static devsu.utils.commonUtils.*;


public class commonWebStepDefinition {
    @Given("que el (.*) navega a la página de inicio de sesión de Sauce Demo$")
    public void navegarAPaginaDeInicioDeSesion(String actor) {
        System.out.println("Navegando a la página de inicio de sesión de Sauce Demo...");
        sdLoginPage.actor = definirActor(actor);
        OnStage.theActorCalled(actor).attemptsTo(sdNavegarPaginaTask.sending(URL_SAUCE_DEMO));

    }




}
