package devsu.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class sdNavegarPaginaTask implements Task {
    private final String url;

    public sdNavegarPaginaTask(String url) {
        this.url = url;
    }
    @Override
    @Step("{0} navega a la página: #url")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(url));
    }

    public static Performable sending(String url) {
        return instrumented(sdNavegarPaginaTask.class, url);
    }

}
