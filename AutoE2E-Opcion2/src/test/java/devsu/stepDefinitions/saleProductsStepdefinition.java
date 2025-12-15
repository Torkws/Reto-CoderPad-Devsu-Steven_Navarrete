package devsu.stepDefinitions;

import devsu.questions.commonQuestions;
import devsu.tasks.sdAgregaProductosTask;
import devsu.tasks.sdRealizaCheckoutTask;
import devsu.ui.sdCheckoutPage;
import devsu.ui.sdInventoryPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.ArrayList;
import java.util.List;

import static devsu.ui.sdInventoryPage.getCartProduct;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class saleProductsStepdefinition {

    List<String> productos;
    List<String[]> productInventoryList = new ArrayList<>();
    List<String[]> productCheckoutList = new ArrayList<>();

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


    @Then("^debería mostrar un mensaje de error en el formulario de checkout: (.*)$")
    public void deberiaMostrarUnMensajeDeErrorEnElFormularioDeCheckout(String messageErrorExpected) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El mensaje de error en el formulario de checkout es correcto",
                        commonQuestions.obtieneTextoDeUnObjeto(sdCheckoutPage.ERROR_MESSAGE_CHECKOUT), equalTo(messageErrorExpected))
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







    @And("se procesa la confirmación de la compra")
    public void seProcesaLaConfirmacionDeLaCompra() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Click.on(sdCheckoutPage.FINISH_BUTTON)
        );
    }

    @And("debería visualizar la confirmación de la compra")
    public void deberiaVisualizarLaConfirmacionDeLaCompra() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El título de orden completada está visible",
                        commonQuestions.validaObjetoSeEncuentreEnPagina(sdCheckoutPage.TITLE_ORDER_COMPLETE))
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat("El título de orden completada está visible",
                        commonQuestions.obtieneTextoDeUnObjeto(sdCheckoutPage.TITLE_ORDER_COMPLETE), equalTo("Checkout: Complete!"))
        );

        OnStage.theActorInTheSpotlight().should(
                seeThat("El mensaje de confirmación está visible",
                        commonQuestions.validaObjetoSeEncuentreEnPagina(sdCheckoutPage.CONFIRMATION_MESSAGE))
        );

        OnStage.theActorInTheSpotlight().should(
                seeThat("El botón de volver a productos está visible",
                        commonQuestions.validaObjetoSeEncuentreEnPagina(sdCheckoutPage.BACK_BUTTON))
        );

    }

    @And("^debería ver el mensaje de compra exitosa (.*)$")
    public void deberiaVerElMensaje(String mensajeEsperado) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El mensaje de confirmación es correcto",
                        commonQuestions.obtieneTextoDeUnObjeto(sdCheckoutPage.CONFIRMATION_MESSAGE), equalTo(mensajeEsperado))
        );
    }




}
