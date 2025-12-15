package devsu.tasks;

import devsu.ui.sdLoginPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class sdIniciaSesionTask implements Task {

    private final String username;
    private final String password;

    public sdIniciaSesionTask(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Performable sending(String username, String password) {
        return instrumented(sdIniciaSesionTask.class, username, password);
    }

    @Override
    @Step("{0} inicia sesión correctamente: ")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(username).into(sdLoginPage.USERNAME_FIELD));
        actor.attemptsTo(Enter.theValue(password).into(sdLoginPage.PASSWORD_FIELD));
        actor.attemptsTo(Click.on(sdLoginPage.LOGIN_BUTTON));
    }

}
