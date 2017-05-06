/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class represents facts of Kiwi or Predators
 *
 * @author Chaitanya Varma
 * @version April 2017
 */
public class Facts {

    private HashMap<String, ArrayList<String>> facts;
    private HashMap<String, ArrayList<String>> shownFacts;
    private HashMap<String, ArrayList<String>> discoveredFacts;
    private HashMap<String, Boolean> isAllFactsDiscovered;
    private Database db = new Database();

    /**
     * Constructor for populating facts for Kiwi or Predator
     */
    public Facts() {
        facts = new HashMap<String, ArrayList<String>>();
        shownFacts = new HashMap<String, ArrayList<String>>();
        discoveredFacts = new HashMap<String, ArrayList<String>>();
        isAllFactsDiscovered = new HashMap<String, Boolean>();
        db.openConnection();
        ArrayList<String> occupants = db.getOccupants();
        for (String occupant : occupants) {
            populateFacts(occupant);
        }
        db.closeConnection();
    }

    /**
     * Populates facts
     *
     * @param occupant occupant type like Kiwi or Rat etc
     */
    private void populateFacts(String occupant) {
        facts.put(occupant, db.getFacts(occupant));
        shownFacts.put(occupant, new ArrayList<String>());
        discoveredFacts.put(occupant, new ArrayList<String>());
        isAllFactsDiscovered.put(occupant, false);
    }

    /**
     * Gets a random fact from the list
     *
     * @param occupant occupant type like Kiwi or Rat etc
     */
    public String getFact(String occupant) {
        //Get a random fact from the list
        if (facts.containsKey(occupant)) {
            int random = new Random().nextInt(facts.get(occupant).size());
            String fact = facts.get(occupant).get(random);

            //add the fact to shownFacts list and remove from facts list
            //so that next time it won't get the same fact from facts list again
            shownFacts.get(occupant).add(fact);
            facts.get(occupant).remove(random);

            if (!isAllFactsDiscovered.get(occupant)) {
                discoveredFacts.get(occupant).add(fact);
            }

            if (facts.get(occupant).isEmpty()) {
                isAllFactsDiscovered.put(occupant, true);
                for (String fa : shownFacts.get(occupant)) {
                    facts.get(occupant).add(fa);
                }
                shownFacts.get(occupant).clear();
            }
            return fact;
        } else {
            return "";
        }
    }

    /**
     * Gets all facts that are already shown to user
     *
     */
    public ArrayList<String> getDiscoveredFacts() {
        ArrayList<String> allDiscoveredFacts = new ArrayList<String>();
        for (String key : discoveredFacts.keySet()) {
            ArrayList<String> facts = discoveredFacts.get(key);
            for (String fact : facts) {
                allDiscoveredFacts.add(fact);
            }
        }
        return allDiscoveredFacts;
    }
}
