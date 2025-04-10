package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {

    private WebDriver driver;
    private String actual;


    @Given("I navigate to the site")
    public void iNavigateToTheSite() {
        System.out.println("Starting...");
        driver = new FirefoxDriver();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        driver.manage().window().maximize();
    }

    @Given("I have selected my date of birth {string}")
    public void iHaveSelectedMyDateOfBirth(String birthday) {
        System.out.println("Filling in fields...");
        WebElement date = driver.findElement(By.id("dp"));
        date.sendKeys(birthday);

    }

    @When("I have entered my name {string}")
    public void iHaveEnteredMyName(String name) {
        //System.out.println(name);
        WebElement firstName = driver.findElement(By.name("Forename"));
        firstName.sendKeys(name);

    }

    @When("I have entered my lastname {string}")
    public void iHaveEnteredMyLastName(String lastname) {
        //System.out.println(lastname);
        WebElement surName = driver.findElement(By.name("Surname"));
        surName.sendKeys(lastname);
    }

    @When("I have entered my email {string}")
    public void iHaveEnteredMyEmail(String firstEmail) {
        //System.out.println(email);
        WebElement emailAddress = driver.findElement(By.name("EmailAddress"));
        emailAddress.sendKeys(firstEmail);
    }

    @When("I have confirmed my email {string}")
    public void iHaveConfirmedMyEmail(String secondEmail) {
        //System.out.println(email);
        WebElement confirmEmail = driver.findElement(By.name("ConfirmEmailAddress"));
        confirmEmail.sendKeys(secondEmail);
    }

    @When("I have entered my password {string}")
    public void iHaveEnteredMyPassword(String firstPassword) {
        //System.out.println(password);
        WebElement enterPassword = driver.findElement(By.name("Password"));
        enterPassword.sendKeys(firstPassword);
    }

    @When("I have confirmed my password {string}")
    public void iHaveConfirmedMyPassword(String secondPassword) {
        //System.out.println(password);
        WebElement samePassword = driver.findElement(By.name("ConfirmPassword"));
        samePassword.sendKeys(secondPassword);
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
        WebElement surName = driver.findElement(By.name("Surname"));
        surName.sendKeys(lastName);
    }

    @When("I write my {}")
    public void iWriteMy(String emailAddress) {
        WebElement email = driver.findElement(By.name("EmailAddress"));
        email.sendKeys(emailAddress);
        
    }

    @When("I confirm my {}")
    public void iConfirmMy(String secondEmailAddress) {
        WebElement confirmEmail = driver.findElement(By.name("ConfirmEmailAddress"));
        confirmEmail.sendKeys(secondEmailAddress);
    }

    @When("I rewrite {}")
    public void iRewrite(String secondPassword) {
        WebElement samePassword = driver.findElement(By.name("ConfirmPassword"));
        samePassword.sendKeys(secondPassword);
        
    }

    @When("I check {}")
    public void iCheck(String boxes) {
        if(boxes.equals("all")){
            System.out.println("Checking ALL boxes...");
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
