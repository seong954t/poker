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
	public void SUIT��_5���������ϸ�_�÷�����() {
		Evaluator evaluator = new Evaluator();
		List<Card> cardList = Arrays.asList(new Card(1, Suit.CLUBS), new Card(4, Suit.CLUBS), new Card(8, Suit.CLUBS),
				new Card(13, Suit.CLUBS), new Card(2, Suit.CLUBS));
		String result = evaluator.evaluate(cardList);
		assertThat(result, is("FLUSH"));
	}

	@Test
	public void ���̴�_�ٸ���_A_����_5��_������_�齺Ʈ����Ʈ�̴�() {
		Evaluator evaluator = new Evaluator();
		List<Card> cardList = Arrays.asList(new Card(1, Suit.CLUBS), new Card(2, Suit.HEARTS),
				new Card(3, Suit.DIAMONDS), new Card(4, Suit.SPADES), new Card(5, Suit.CLUBS));
		String result = evaluator.evaluate(cardList);
		assertThat(result, is("BACKSTRAIGHT"));
	}

	@Test
	public void ����ƾ��_�齺Ʈ����Ʈ_����_��Ʈ����Ʈ�̴�() {
		Evaluator evaluator = new Evaluator();
		List<Card> cardList = Arrays.asList(new Card(4, Suit.CLUBS), new Card(5, Suit.HEARTS), new Card(6, Suit.SPADES),
				new Card(7, Suit.CLUBS), new Card(8, Suit.DIAMONDS));
		String result = evaluator.evaluate(cardList);
		assertThat(result, is("STRAIGHT"));
	}
}