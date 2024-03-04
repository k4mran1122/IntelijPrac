package cucumber;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class TheUser extends Actor {

    private static List<TheUser> actors = new ArrayList<>();

    @Managed
    private WebDriver driver;

    public TheUser(String actorName) {
        super(actorName);
        this.whoCan(BrowseTheWeb.with(this.driver));
    }

    public static TheUser create(String actorName) {
        TheUser actor = new TheUser(actorName);
        actors.add(actor);
        return actor;
    }

    public static TheUser get(String actorName) {
        return actors.stream()
                .filter(actor -> actor.getName().equals(actorName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Actor not found: " + actorName));
    }

    public static List<TheUser> getAllActors() {
        return actors;
    }
}

