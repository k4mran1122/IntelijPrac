package cucumber;

import com.github.javafaker.Faker;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static cucumber.LoginUI.*;
import static cucumber.LoginUI.LOGOUT_BUTTON;

public class StepDefinations {
    private TheUser actor;
    private Map<String, LoginCredentials> actorCredentialsMap;
    private Queue<String> availableActorsQueue;
    private final Faker faker;
    private Queue<LoginCredentials> credentialsQueue;
    private WebDriver driver;
    private int scenarioCount;

    public StepDefinations() throws IOException {
        this.faker = new Faker();
    }
    @Before
    public void setUp() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/test/resources/Users.json");
        List<LoginCredentials> credentialsList = mapper.readValue(file, new TypeReference<List<LoginCredentials>>() {});

        credentialsQueue = new LinkedList<>(credentialsList);

        actorCredentialsMap = new HashMap<>();
        for (LoginCredentials credentials : credentialsList) {
            actorCredentialsMap.put(credentials.getName(), credentials);
        }
        availableActorsQueue = new LinkedList<>(actorCredentialsMap.keySet());
    }
    @Given("User login as a tenant")
    public void user_login_as_a_tenant() {
        String actorName = getNextAvailableActor();
        LoginCredentials credentials = actorCredentialsMap.get(actorName);
        actor = TheUser.create(actorName);
        actor.attemptsTo(Open.url("https://tme.qa.xcloudiq.com/login"),
                Enter.keyValues(credentials.getName()).into(EMAIL_INPUT_FIELD),
                Enter.keyValues(credentials.getPassword()).into(PASSWORD_INPUT_FIELD),
                Click.on(LOGIN_BUTTON),
                Open.url("https://zta-tme-2.qa.xcloudiq.com/zta"),
                Click.on(IAM_BUTTON),
                Click.on(USERS_BUTTON)
        );
    }
    @Then("User clicks on invite users")
    public void user_clicks_on_invite_users() {
        actor.attemptsTo(Click.on(INVITE_USERS_BUTTON));
    }
    @ParameterType("google user|local user|microsoft user")
    public String userType(String userType) {
        return generateRandomEmail(userType);
    }
    @Then("User invites {userType}")
    public void user_invites_userType(String userType) {
        String fakeEmail = userType;
        System.out.println("Inviting " + userType + " with email: " + fakeEmail);
        actor.attemptsTo(
                Enter.keyValues(fakeEmail).into(WRITE_EMAIL),
                Click.on(SEND_INVITE)
        );
    }
    @Then("User invites {userType} with UserGroup")
    public void user_invites_userType_with_UserGroup(String userType) {
        String fakeEmail = userType;
        System.out.println("Inviting " + userType + " with email: " + fakeEmail + " with UserGroup");
        actor.attemptsTo(
                Enter.keyValues(fakeEmail).into(WRITE_EMAIL),
                Enter.keyValues("iqK").into(USER_GROUP).thenHit(Keys.ENTER),
                Click.on(SEND_INVITE)
        );
    }
    @Then("Click on logout")
    public void click_on_logout(){
        actor.attemptsTo(Click.on(USER_INFO),
                Click.on(LOGOUT_BUTTON));
        scenarioCount++;
        if (scenarioCount == 6) {
            logoutUser();
            scenarioCount = 0;
        }
    }
    private void logoutUser(){
    }
    private synchronized String getNextAvailableActor() {
        synchronized (availableActorsQueue) {
            if (availableActorsQueue.isEmpty()) {
                throw new RuntimeException("No available actors with credentials");
            }
            return availableActorsQueue.poll();
        }
    }
    private String generateRandomEmail(String userType) {
        String domain = "";
        switch (userType.toLowerCase()) {
            case "google user":
                domain = "@gmail.com";
                break;
            case "local user":
                domain = "@" + faker.internet().domainWord() + ".com";
                break;
            case "microsoft user":
                domain = "@microsoft.com";
                break;
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
        String localPart = faker.internet().domainName().toLowerCase().replaceAll("\\W", "");
        return localPart + domain;
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
        }
    }
}