/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitTests;

import gameModel.Facts;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * @author KCV
 */
public class FactsTest
{

	/**
	 * Test of getFacts method, of class Facts.
	 */
	@Test public void testGetFacts()
	{
		Facts facts = new Facts(1);
		String fact = facts.getFact("Rat").trim();

		assertThat(fact, anyOf(containsString(
				"These rats are food for the kiwis predators, keeping the population of predators high."),
				containsString("Norway rats are large rodents that may weigh in excess of 500 grams."),
				containsString("The Norway rat (Rattus norvegicus) is the largest of the two European rats."),
				containsString("Norway rats are large enough to kill nesting adult seabirds."),
				containsString("Norway rats prey on animals that live, roost or nest close to the ground.")));
	}

	@Test public void testGetFactsForUndefinedAnimal()
	{
		Facts facts = new Facts(1);
		String fact = facts.getFact("Crow");

		assertEquals(fact, "");
	}

	/**
	 * Test of getFacts method, of class Facts.
	 */
	@Test public void getDiscoveredFacts()
	{
		Facts facts = new Facts(1);
		facts.getFact("Rat");
		ArrayList<String> discoveredFacts = facts.getDiscoveredFacts();

		assertThat(discoveredFacts.get(0).trim(), anyOf(containsString(
				"These rats are food for the kiwis predators, keeping the population of predators high."),
				containsString("Norway rats are large rodents that may weigh in excess of 500 grams."),
				containsString("The Norway rat (Rattus norvegicus) is the largest of the two European rats."),
				containsString("Norway rats are large enough to kill nesting adult seabirds."),
				containsString("Norway rats prey on animals that live, roost or nest close to the ground.")));

	}
}
