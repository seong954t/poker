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
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.CLUBS),
                new Card(4,Suit.CLUBS),
                new Card(8,Suit.CLUBS),
                new Card(13,Suit.CLUBS),
                new Card(2,Suit.CLUBS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("FLUSH"));
    }

    @Test
    public void 같은_숫자_2장이_1쌍이면_원페어다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(7,Suit.CLUBS),
                new Card(2,Suit.HEARTS),
                new Card(8,Suit.DIAMONDS),
                new Card(10,Suit.SPADES),
                new Card(8,Suit.SPADES)
        );
        boolean result = evaluator.checkOnepair(cardList);
        assertThat(result, is(true));
    }

    @Test
    public void 같은_숫자_2장이_2쌍이면_투페어다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(2,Suit.CLUBS),
                new Card(2,Suit.HEARTS),
                new Card(3,Suit.DIAMONDS),
                new Card(4,Suit.SPADES),
                new Card(3,Suit.SPADES)
        );
        boolean result = evaluator.checkTwopair(cardList);
        assertThat(result, is(true));
    }

    @Test
    public void 같은_숫자가_3장이면_트리플이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(3,Suit.CLUBS),
                new Card(2,Suit.HEARTS),
                new Card(7,Suit.DIAMONDS),
                new Card(3,Suit.SPADES),
                new Card(3,Suit.SPADES)
        );
        boolean result = evaluator.checkTriple(cardList);
        assertThat(result, is(true));
    }

}