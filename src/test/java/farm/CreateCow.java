package farm;

import java.util.Calendar;
import java.util.Date;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import static org.junit.Assert.*;

import models.*;

public class CreateCow {
    private String name;
    private Farm farm;
    private Date dateOfBirth;
    private Gender gender;

    private Cow cow;
    private boolean errorWasRaised;

    @Given("^Cow properties without errors$")
    public void cow_properties_without_errors() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 11, 14);

        name = "Bessie";
        farm = new Farm("Big Bulgarian Farm");
        dateOfBirth = calendar.getTime();
        gender = Gender.COW;
    }

    @When("^Cow properties with invalid date$")
    public void cow_properties_with_invalid_date() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 11, 14);

        name = "Clarabelle";
        farm = new Farm("Big Bulgarian Farm");
        dateOfBirth = calendar.getTime();
        gender = Gender.COW;
    }

    @When("^Creating Cow Object$")
    public void creating_cow_object() {
        errorWasRaised = false;

        try {
            cow = new Cow(name, dateOfBirth, farm, gender);
        } catch (Exception e) {
            errorWasRaised = true;
        }
    }

    @Then("^Cow Object is returned$")
    public void cow_object_is_returned() {
        assertNotNull(cow);
    }

    @Then("^Exception is raised when creating cow$")
    public void exception_is_raised_when_creating_cow() {
        assertTrue(errorWasRaised);
    }
}