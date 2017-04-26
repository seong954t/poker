package com.edu.cnu.poker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by cse on 2017-04-17.
 */
public class EvaluatorTest {

	@Test
	public void SUIT가_5개가동일하면_플러쉬다() {
		Evaluator evaluator = new Evaluator();
		List<Card> cardList = Arrays.asList(new Card(1, Suit.CLUBS), new Card(4, Suit.CLUBS), new Card(8, Suit.CLUBS),
				new Card(13, Suit.CLUBS), new Card(2, Suit.CLUBS));
		String result = evaluator.evaluate(cardList);
		assertThat(result, is("FLUSH"));
	}

	@Test
	public void 무늬는_다르고_A_부터_5가_연속인_백스트레이트이다() {
		Evaluator evaluator = new Evaluator();
		List<Card> cardList = Arrays.asList(new Card(1, Suit.CLUBS), new Card(2, Suit.HEARTS),
				new Card(3, Suit.DIAMONDS), new Card(4, Suit.SPADES), new Card(5, Suit.CLUBS));
		String result = evaluator.evaluate(cardList);
		assertThat(result, is("BACKSTRAIGHT"));
	}

	@Test
	public void 마운틴과_백스트레이트_제외_스트레이트이다() {
		Evaluator evaluator = new Evaluator();
		List<Card> cardList = Arrays.asList(new Card(4, Suit.CLUBS), new Card(5, Suit.HEARTS), new Card(6, Suit.SPADES),
				new Card(7, Suit.CLUBS), new Card(8, Suit.DIAMONDS));
		String result = evaluator.evaluate(cardList);
		assertThat(result, is("STRAIGHT"));
	}
}