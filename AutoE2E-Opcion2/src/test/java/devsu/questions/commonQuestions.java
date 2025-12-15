package devsu.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class commonQuestions {
    public static Question<Boolean> validaObjetoSeEncuentreEnPagina(Target target) {
        return actor ->
                the(target)
                        .answeredBy(actor)
                        .isVisible();
    }

    public static Question<String> obtieneTextoDeUnObjeto(Target target) {
        return actor ->
                the(target)
                        .answeredBy(actor)
                        .getText();
    }
}
