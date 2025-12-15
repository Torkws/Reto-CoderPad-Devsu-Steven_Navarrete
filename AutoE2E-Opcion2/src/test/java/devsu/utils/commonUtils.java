package devsu.utils;

import com.github.javafaker.Faker;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class commonUtils {

    public static Actor definirActor(String name) {
        Actor actor = theActorCalled(name);
        actor.can(BrowseTheWeb.with(ThucydidesWebDriverSupport.getDriver()));
        return actor;
    }

    public static String GenerarDataMock(String fieldName) {
        Faker faker = new Faker();
        switch (fieldName.toLowerCase()) {
            case "firstname":
                return faker.name().firstName();
            case "lastname":
                return faker.name().lastName();
            case "postalcode":
                return faker.address().zipCode();
            default:
                throw new IllegalArgumentException("Field name not recognized: " + fieldName);
        }
    }

}
