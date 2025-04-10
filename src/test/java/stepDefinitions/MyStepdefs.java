package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {

    private WebDriver driver;
    private String actual;

    private void sendKeys(By by, String text) {
        (new WebDriverWait(driver, Duration.ofSeconds(5))).
                until(ExpectedConditions.presenceOfElementLocated(by));

        driver.findElement(by).sendKeys(text);
    }

    @Given("I navigate to the site")
    public void iNavigateToTheSite() {
        System.out.println("Starting...");
        driver = new FirefoxDriver();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        driver.manage().window().maximize();
    }

    @Given("I open a {} and navigate to the site")
    public void iOpenAAndNavigateToTheSite(String browser) {
        System.out.println("Starting...");
        if(browser.equals("firefox")) {
            driver = new FirefoxDriver();
            driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
            driver.manage().window().maximize();

        } else if(browser.equals("edge")) {
            driver = new EdgeDriver();
            driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
            driver.manage().window().maximize();
        }
    }

    @When("I have selected my date of birth {string}")
    public void iHaveSelectedMyDateOfBirth(String birthday) {
        System.out.println("Filling in fields...");
        sendKeys(By.id("dp"), birthday);

    }

    @When("I have entered my name {string}")
    public void iHaveEnteredMyName(String name) {
        sendKeys(By.name("Forename"),name);

    }

    @When("I have entered my lastname {string}")
    public void iHaveEnteredMyLastName(String lastname) {
        sendKeys(By.name("Surname"),lastname);
    }

    @When("I have entered my email {string}")
    public void iHaveEnteredMyEmail(String firstEmail) {
        sendKeys(By.name("EmailAddress"),firstEmail);
    }

    @When("I have confirmed my email {string}")
    public void iHaveConfirmedMyEmail(String secondEmail) {
        sendKeys(By.name("ConfirmEmailAddress"),secondEmail);
    }

    @When("I have entered my password {string}")
    public void iHaveEnteredMyPassword(String firstPassword) {
        sendKeys(By.name("Password"),firstPassword);
    }

    @When("I have confirmed my password {string}")
    public void iHaveConfirmedMyPassword(String secondPassword) {
        sendKeys(By.name("ConfirmPassword"),secondPassword);
    }

    @When("I have checked all boxes")
    public void iHaveCheckedAllBoxes() {
        System.out.println("Checking boxes...");
        WebElement tos = driver.findElement(By.cssSelector("label[for='sign_up_25']"));
        tos.click();
        WebElement confirmAge = driver.findElement(By.cssSelector("label[for='sign_up_26']"));
        confirmAge.click();
        WebElement ethics = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div/div/form/div[11]/div/div[7]/label/span[3]"));
        ethics.click();
    }

    @When("I click confirm")
    public void iClickConfirm() {
        System.out.println("Confirming...");
        WebElement confirmAndJoin = driver.findElement(By.className("btn"));
        confirmAndJoin.click();
    }

    @Then("I create my account")
    public void iCreateMyAccount() {
        System.out.println("Account created");
        WebElement confirmation = driver.findElement(By.cssSelector("h2[class='bold  gray  text-center  margin-bottom-40']"));

        String actual = confirmation.getText();

        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
        assertEquals(expected, actual);
    }


    @When("I enter my {}")
    public void iEnterMy(String lastName) {
        sendKeys(By.name("Surname"),lastName);
    }

    @When("I write my {}")
    public void iWriteMy(String emailAddress) {
        sendKeys(By.name("EmailAddress"),emailAddress);
    }

    @When("I confirm my {}")
    public void iConfirmMy(String secondEmailAddress) {
        sendKeys(By.name("ConfirmEmailAddress"),secondEmailAddress);
    }

    @When("I rewrite {}")
    public void iRewrite(String secondPassword) {
        sendKeys(By.name("ConfirmPassword"),secondPassword);
    }

    @When("I check {}")
    public void iCheck(String boxes) {
        Actions actions = new Actions(driver);
        if(boxes.equals("all")){
            System.out.println("Checking ALL boxes...");

            actions.moveByOffset(200, 150).click().perform();
            WebElement tos = driver.findElement(By.cssSelector("label[for='sign_up_25']"));
            tos.click();
            WebElement confirmAge = driver.findElement(By.cssSelector("label[for='sign_up_26']"));
            confirmAge.click();
            WebElement ethics = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div/div/form/div[11]/div/div[7]/label/span[3]"));
            ethics.click();

        } else {
            System.out.println("Checking boxes...");
            WebElement confirmAge = driver.findElement(By.cssSelector("label[for='sign_up_26']"));
            confirmAge.click();
            WebElement ethics = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div/div/form/div[11]/div/div[7]/label/span[3]"));
            ethics.click();
        }

    }

    @Then("I get the {} message I {}")
    public void iGetTheMessage(String error, String expected) {

        if(error.equals("name")) {
            WebElement errorMessage = driver.findElement(By.cssSelector("span[for='member_lastname']"));
            actual = errorMessage.getText();

        } else if(error.equals("password")) {
            WebElement errorMessage = driver.findElement(By.cssSelector("span[for='signupunlicenced_confirmpassword']"));
            actual = errorMessage.getText();

        } else if(error.equals("ToS")) {
            WebElement errorMessage = driver.findElement(By.cssSelector("span[for='TermsAccept']"));
            actual = errorMessage.getText();
        }


        assertEquals(expected, actual);
        System.out.println("--DONE--");
    }

}
