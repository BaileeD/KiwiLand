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
 *
 * @author KCV
 */
public class Facts {
     
     private HashMap<String, ArrayList<String>> facts;
     private HashMap<String, ArrayList<String>> shownFacts;
     private HashMap<String, ArrayList<String>> discoveredFacts;
     private HashMap<String, Boolean> isAllFactsDiscovered;
     private Database db = new Database();
     
     public Facts()
     {
         facts = new HashMap<String, ArrayList<String>>();
         shownFacts = new HashMap<String, ArrayList<String>>();
         discoveredFacts = new HashMap<String, ArrayList<String>>();
         isAllFactsDiscovered = new HashMap<String, Boolean>();
         db.openConnection();
         ArrayList<String> occupants = db.getOccupants();
         for(String occupant : occupants)
         {
            populateFacts(occupant);
         }
         db.closeConnection();
     }
     
     private void populateFacts(String occupant)
     {
         facts.put(occupant, db.getFacts(occupant));
         shownFacts.put(occupant, new ArrayList<String>());
         discoveredFacts.put(occupant, new ArrayList<String>());
         isAllFactsDiscovered.put(occupant, false);
     }
     
     public String getFact(String occupant)
     {
         //Get a random fact from the list
         int random = new Random().nextInt(facts.get(occupant).size());
         String fact = facts.get(occupant).get(random);
         
         //add the fact to shownFacts list and remove from facts list
         //so that next time it won't get the same fact from facts list again
         shownFacts.get(occupant).add(fact);
         facts.get(occupant).remove(random);
         
         if(!isAllFactsDiscovered.get(occupant))
             discoveredFacts.get(occupant).add(fact);
         
         if(facts.get(occupant).isEmpty())
         {
            isAllFactsDiscovered.put(occupant, true);
            for(String fa : shownFacts.get(occupant))
            {
               facts.get(occupant).add(fa);
            }
            shownFacts.get(occupant).clear();
         }
         return fact;
     }
     
     public ArrayList<String> getDiscoveredFacts(String occupant)
     {
         return discoveredFacts.get(occupant);
     }
}
