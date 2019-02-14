package models;

/**
 * 
 */
public class Owner {

    /**
     * Default constructor
     */
    public Owner() {
    }

    /**
     * 
     */
    public int id;

    /**
     * 
     */
    public String firstName;

    /**
     * 
     */
    public String lastName;

    /**
     * @param firstName
     * @param lastName
     */
    public Owner(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}