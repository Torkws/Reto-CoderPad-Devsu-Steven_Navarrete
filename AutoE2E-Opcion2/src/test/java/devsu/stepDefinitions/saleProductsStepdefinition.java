package devsu.stepDefinitions;

import devsu.questions.commonQuestions;
import devsu.tasks.sdAgregaProductosTask;
import devsu.ui.sdLoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.util.List;

import static devsu.ui.sdInventoryPage.getCartProduct;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class saleProductsStepdefinition {

    List<String> productos;

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
}
