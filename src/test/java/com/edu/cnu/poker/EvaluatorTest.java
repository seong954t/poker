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
    public void 무늬는_다르고_A_부터_5가_연속인_백스트레이트이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(2, Suit.HEARTS),
                new Card(3, Suit.DIAMONDS),
                new Card(4, Suit.SPADES),
                new Card(5, Suit.CLUBS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("BACKSTRAIGHT"));
    }

    @Test
    public void 마운틴과_백스트레이트_제외_스트레이트이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(4, Suit.CLUBS),
                new Card(5, Suit.HEARTS),
                new Card(6, Suit.SPADES),
                new Card(7, Suit.CLUBS),
                new Card(8, Suit.DIAMONDS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("STRAIGHT"));
    }

    @Test
    public void SUIT가_5개가동일하면_플러쉬다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(4, Suit.CLUBS),
                new Card(8, Suit.CLUBS),
                new Card(13, Suit.CLUBS),
                new Card(2, Suit.CLUBS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("FLUSH"));
    }

    @Test
    public void 같은_숫자_4장이면_포카드이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(4, Suit.CLUBS),
                new Card(4, Suit.SPADES),
                new Card(4, Suit.HEARTS),
                new Card(4, Suit.DIAMONDS),
                new Card(2, Suit.HEARTS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("FOURCARD"));
    }

    @Test
    public void 처음이_10인_스트레이트_마운틴이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(10, Suit.SPADES),
                new Card(11, Suit.HEARTS),
                new Card(12, Suit.DIAMONDS),
                new Card(13, Suit.HEARTS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("MOUNTAIN"));
    }

    @Test
    public void 같은_숫자_2장이_1쌍이면_원페어다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(7, Suit.CLUBS),
                new Card(2, Suit.HEARTS),
                new Card(8, Suit.DIAMONDS),
                new Card(10, Suit.SPADES),
                new Card(8, Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("ONEPAIR"));
    }

    @Test
    public void 스트레이트플러쉬_모양이_같으면_로얄스트레이트플러쉬이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.DIAMONDS),
                new Card(13, Suit.DIAMONDS),
                new Card(12, Suit.DIAMONDS),
                new Card(11, Suit.DIAMONDS),
                new Card(10, Suit.DIAMONDS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("ROYALSTRAIGHTFLUSH"));
    }

    @Test
    public void 스트레이트_모양이_같으면_스트레이트플러쉬이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(7, Suit.SPADES),
                new Card(8, Suit.SPADES),
                new Card(9, Suit.SPADES),
                new Card(10, Suit.SPADES),
                new Card(11, Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("STRAIGHTFLUSH"));
    }

    @Test
    public void 백스트레이트_모양이_같으면_백스트레이트플러쉬이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(5, Suit.CLUBS),
                new Card(3, Suit.CLUBS),
                new Card(1, Suit.CLUBS),
                new Card(4, Suit.CLUBS),
                new Card(2, Suit.CLUBS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("BACKSTRAIGHTFLUSH"));
    }

    @Test
    public void 같은_숫자_2장이_2쌍이면_투페어다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(2, Suit.CLUBS),
                new Card(2, Suit.HEARTS),
                new Card(3, Suit.DIAMONDS),
                new Card(4, Suit.SPADES),
                new Card(3, Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("TWOPAIR"));
    }

    @Test
    public void 같은_숫자가_3장이면_트리플이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(3, Suit.CLUBS),
                new Card(2, Suit.HEARTS),
                new Card(7, Suit.DIAMONDS),
                new Card(3, Suit.SPADES),
                new Card(3, Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("TRIPLE"));
    }

    @Test
    public void 원페어_트리플은_풀하우스이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(5, Suit.CLUBS),
                new Card(7, Suit.HEARTS),
                new Card(5, Suit.DIAMONDS),
                new Card(7, Suit.SPADES),
                new Card(7, Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("FULLHOUSE"));
    }
}