package models;

import java.util.*;

/**
 * 
 */
public class Farm {

    /**
     * Default constructor
     */
    public Farm() {
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
    public List<Owner> owners;

    /**
     * 
     */
    public List<Association> associations;

    /**
     * @param name
     */
    public Farm(String name) {
        this.name = name;
    }

    /**
     * @param owner
     * @return
     */
    public boolean addOwner(Owner owner) {
        // TODO implement here
        return false;
    }

    /**
     * @param owner
     * @return
     */
    public boolean removeOwner(Owner owner) {
        // TODO implement here
        return false;
    }

    /**
     * @param association
     * @return
     */
    public boolean addAssociation(Association association) {
        // TODO implement here
        return false;
    }

    /**
     * @param association
     * @return
     */
    public boolean removeAssociation(Association association) {
        // TODO implement here
        return false;
    }

}