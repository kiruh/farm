package models;

import java.util.*;

/**
 * 
 */
public class Cow {

    /**
     * Default constructor
     */
    public Cow() {
    }

    /**
     * 
     */
    public int id;

    /**
     * 
     */
    public String name;

    /**
     * 
     */
    public Date dateOfBirth;

    /**
     * 
     */
    public Gender gender;

    /**
     * 
     */
    public int getAge() {
        long timeBetween = new Date().getTime() - this.dateOfBirth.getTime();
        double yearsBetween = timeBetween / 3.15576e+10;
        int age = (int) Math.floor(yearsBetween);

        return age;
    }

    /**
     * 
     */
    public Cow parentBull;

    /**
     * 
     */
    public Cow parentCow;

    /**
     * 
     */
    public Farm farm;

    /**
     * 
     */
    public ArrayList<Cow> children;

    /**
     * @param name
     * @param dateOfBirth
     * @param farm
     * @throws Exception
     */
    public Cow(String name, Date dateOfBirth, Farm farm, Gender gender) throws Exception {
        if (dateOfBirth.after(new Date())) {
            throw new Exception("Date of birth cannot be greater then today.");
        }

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.farm = farm;
        this.gender = gender;
    }

    private ArrayList<String> canBeBullParentTo(Cow child) {
        ArrayList<String> errors = new ArrayList<String>();

        if (!this.gender.equals(Gender.BULL)) {
            errors.add("Parent bull should be a bull");
        }

        if (this.getAge() < 2) {
            errors.add("Bull cannot be a parent until it's 2 y.o. or more");
        }

        return errors;
    }

    private ArrayList<String> canBeCowParentTo(Cow child) {
        ArrayList<String> errors = new ArrayList<String>();

        if (!this.gender.equals(Gender.COW)) {
            errors.add("Parent cow should be a cow");
        }

        if (this.getAge() < 2) {
            errors.add("Cow cannot be a parent until it's 2 y.o. or more");
        }

        int acceptableMonths = 10;
        int twinsMaxDiff = 24 * 60 * 60 * 1000; // one day

        if (this.children != null) {
            ArrayList<Cow> children = this.children;

            Cow error = children.stream().filter((Cow anotherChild) -> {
                Date anotherChildBirth = anotherChild.dateOfBirth;
                Date today = new Date();

                int m1 = anotherChildBirth.getYear() * 12 + anotherChildBirth.getMonth();
                int m2 = today.getYear() * 12 + today.getMonth();

                int diff = m2 - m1 + 1;

                if (diff > acceptableMonths)
                    return false;

                long timeBetween = Math.abs(anotherChildBirth.getTime() - child.dateOfBirth.getTime());

                if (timeBetween < twinsMaxDiff)
                    return false;

                return true;
            }).findAny().orElse(null);

            if (error != null) {
                errors.add("Cow can't give a birth if previous child was born within last " + acceptableMonths
                        + " months");
            }
        }

        return errors;
    }

    public void addChild(Cow child) throws Exception {
        if (this.children == null) {
            this.children = new ArrayList<Cow>();
        }

        ArrayList<String> errors = new ArrayList<String>();

        if (this.gender.equals(Gender.BULL)) {
            errors = this.canBeBullParentTo(child);
        } else if (this.gender.equals(Gender.COW)) {
            errors = this.canBeCowParentTo(child);
        }

        if (errors.size() != 0) {
            throw new Exception(errors.toArray().toString());
        }

        if (this.gender.equals(Gender.BULL)) {
            child.parentBull = this;
        } else if (this.gender.equals(Gender.COW)) {
            child.parentCow = this;
        }

        this.children.add(child);
    }

    /**
     * @param parentBull
     * @param parentCow
     * @throws Exception
     */
    public void addParents(Cow parentBull, Cow parentCow) throws Exception {
        ArrayList<String> errors;

        errors = parentBull.canBeBullParentTo(this);
        if (errors.size() != 0) {
            throw new Exception(errors.toArray().toString());
        }

        errors = parentCow.canBeCowParentTo(this);
        if (errors.size() != 0) {
            throw new Exception(errors.toArray().toString());
        }

        this.parentBull = parentBull;
        this.parentCow = parentCow;

        parentBull.addChild(this);
        parentCow.addChild(this);
    }
}
