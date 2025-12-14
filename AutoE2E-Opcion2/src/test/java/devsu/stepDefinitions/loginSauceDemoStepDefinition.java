package devsu.stepDefinitions;

import devsu.questions.commonQuestions;
import devsu.tasks.sdIniciaSesionTask;
import devsu.ui.sdLoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class loginSauceDemoStepDefinition {

    @And("^el usuario inicia sesión con el usuario (.*) y contraseña (.*)$")
    public void ingresarNombreDeUsuario(String username, String pass) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                sdIniciaSesionTask.sending(username,pass)
        );
    }

    @Then("el usuario debería ser redirigido a la página de productos")
    public void el_usuario_debería_ser_redirigido_a_la_página_de_productos() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El título de la página de productos es visible",
                        commonQuestions.validaObjetoSeEncuentreEnPagina(sdLoginPage.PRODUCTS_TITLE))
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat("El título de la página de productos correcto",
                        commonQuestions.obtieneTextoDeUnObjeto(sdLoginPage.PRODUCTS_TITLE), equalTo("Products"))
        );
    }
    @Then("el inventario de productos debería ser visible")
    public void el_inventario_de_productos_debería_ser_visible() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El inventario de productos es visible",
                        commonQuestions.validaObjetoSeEncuentreEnPagina(sdLoginPage.INVENTORY_SECTION))
        );
    }

}
