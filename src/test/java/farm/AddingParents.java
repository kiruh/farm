package farm;

import java.util.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import static org.junit.Assert.*;

import models.*;

public class AddingParents {
    private Farm farm = new Farm("Big Bulgarian Farm");

    private Cow child;
    private Cow parentBull;
    private Cow parentCow;

    private Date childBirthDate = new Date();

    private boolean errorWasRaised;

    @Given("^Young bull$")
    public void young_bull() throws Exception {
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.set(2018, 11, 14);
            parentBull = new Cow("Bully", calendar.getTime(), farm, Gender.BULL);
        } catch (Exception e) {
            throw new Exception("Not Handled!");
        }

        try {
            calendar.set(2016, 11, 14);
            parentCow = new Cow("Bessie", calendar.getTime(), farm, Gender.COW);
        } catch (Exception e) {
            throw new Exception("Not Handled!");
        }
    }

    @Given("^Young cow$")
    public void young_cow() throws Exception {
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.set(2015, 11, 14);
            parentBull = new Cow("Bully", calendar.getTime(), farm, Gender.BULL);
        } catch (Exception e) {
            throw new Exception("Not Handled!");
        }

        try {
            calendar.set(2017, 11, 14);
            parentCow = new Cow("Bessie", calendar.getTime(), farm, Gender.COW);
        } catch (Exception e) {
            throw new Exception("Not Handled!");
        }
    }

    @Given("^Cow that had given birth within last 10 months$")
    public void cow_that_had_given_birth_within_last_10_months() throws Exception {
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.set(2015, 11, 14);
            parentBull = new Cow("Bully", calendar.getTime(), farm, Gender.BULL);
        } catch (Exception e) {
            throw new Exception("Not Handled!");
        }

        try {
            calendar.set(2015, 11, 14);
            parentCow = new Cow("Bessie", calendar.getTime(), farm, Gender.COW);

            calendar.set(2019, 0, 5);
            Cow anotherChild = new Cow("Molly", calendar.getTime(), farm, Gender.COW);

            parentCow.addChild(anotherChild);
        } catch (Exception e) {
            throw new Exception("Not Handled!", e);
        }
    }

    @Given("^Cow that had given birth to twins$")
    public void cow_that_had_given_birth_to_twins() throws Exception {
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.set(2015, 11, 14);
            parentBull = new Cow("Bully", calendar.getTime(), farm, Gender.BULL);
        } catch (Exception e) {
            throw new Exception("Not Handled!");
        }

        try {
            calendar.set(2015, 11, 14);
            parentCow = new Cow("Bessie", calendar.getTime(), farm, Gender.COW);

            Cow anotherChild = new Cow("Dorothy", childBirthDate, farm, Gender.COW);
            parentCow.addChild(anotherChild);
        } catch (Exception e) {
            throw new Exception("Not Handled!", e);
        }
    }

    @Given("^Valid cow and bull$")
    public void valid_cow_and_bull() throws Exception {
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.set(2015, 11, 14);
            parentBull = new Cow("Bully", calendar.getTime(), farm, Gender.BULL);
        } catch (Exception e) {
            throw new Exception("Not Handled!");
        }

        try {
            calendar.set(2015, 11, 14);
            parentCow = new Cow("Bessie", calendar.getTime(), farm, Gender.COW);
        } catch (Exception e) {
            throw new Exception("Not Handled!", e);
        }
    }

    @When("^Adding parents to cow$")
    public void adding_parents_to_cow() throws Exception {
        errorWasRaised = false;

        try {
            child = new Cow("Bella", childBirthDate, farm, Gender.BULL);
        } catch (Exception e) {
            throw new Exception("Not Handled!");
        }

        try {
            child.addParents(parentBull, parentCow);
        } catch (Exception e) {
            errorWasRaised = true;
        }
    }

    @Then("^Exception is raised when adding parents$")
    public void exception_is_raised_when_adding_parents() {
        assertTrue(errorWasRaised);
    }

    @Then("^Parents are added$")
    public void parents_are_added() throws Exception {
        assertEquals(parentBull, child.parentBull);
        assertEquals(parentCow, child.parentCow);

        assertTrue(parentBull.children.contains(child));
        assertTrue(parentCow.children.contains(child));
    }
}