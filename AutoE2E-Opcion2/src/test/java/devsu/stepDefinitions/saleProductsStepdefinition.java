package devsu.stepDefinitions;

import devsu.questions.commonQuestions;
import devsu.tasks.sdAgregaProductosTask;
import devsu.tasks.sdRealizaCheckoutTask;
import devsu.ui.sdCheckoutPage;
import devsu.utils.commonUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;

import static devsu.ui.sdInventoryPage.getCartProduct;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class saleProductsStepdefinition {

    List<String> productos;
    String firstName, lastName, postalCode;

    @And("agrega los siguientes productos al carrito:")
    public void agregaProductosAlCarrito(DataTable dataTable) {
        this.productos = dataTable.asList(String.class).subList(1, dataTable.asList(String.class).size());

        OnStage.theActorInTheSpotlight().attemptsTo(
                sdAgregaProductosTask.adding(productos)
        );
    }

    @Then("debería visualizarse los productos en el carrito")
    public void deberiaVisualizarseLosProductosEnElCarrito() {
        productos.forEach(producto -> {
                    OnStage.theActorInTheSpotlight().should(
                            seeThat("El producto " + producto + " está visible en el carrito",
                                    commonQuestions.validaObjetoSeEncuentreEnPagina(getCartProduct(producto)))
                    );
                    OnStage.theActorInTheSpotlight().should(
                            seeThat("El nombre del producto " + producto + " está correcto",
                                    commonQuestions.obtieneTextoDeUnObjeto(getCartProduct(producto)), equalTo(producto))
                    );

                }

        );
    }

    @And("^se completa el proceso de llenado de formulario con los inputs (.*), (.*) y (.*)$")
    public void seCompletaElProcesoDeLlenadoDeFormulario(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;

        OnStage.theActorInTheSpotlight().attemptsTo(
                sdRealizaCheckoutTask.sending(firstName, lastName, postalCode)
        );
    }

    @And("debería permitir realizar la confirmación de la orden")
    public void deberiaPermitirRealizarLaConfirmacionDeLaOrden() {

        OnStage.theActorInTheSpotlight().should(
                seeThat("El título de checkout: Overview está visible",
                        commonQuestions.validaObjetoSeEncuentreEnPagina(sdCheckoutPage.TITLE_CHECKOUT_OVERVIEW))
        );

        OnStage.theActorInTheSpotlight().should(
                seeThat("El título de checkout: Overview es correcto",
                        commonQuestions.obtieneTextoDeUnObjeto(sdCheckoutPage.TITLE_CHECKOUT_OVERVIEW), equalTo("Checkout: Overview"))
        );

        OnStage.theActorInTheSpotlight().should(
                seeThat("El botón de finalizar orden está visible",
                        commonQuestions.validaObjetoSeEncuentreEnPagina(sdCheckoutPage.FINISH_BUTTON))
        );
    }
}
