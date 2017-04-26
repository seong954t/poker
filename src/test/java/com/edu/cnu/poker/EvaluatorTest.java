package com.edu.cnu.poker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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
    public void 같은_숫자_4장이면_포카드이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(4,Suit.CLUBS),
                new Card(4,Suit.SPADES),
                new Card(4,Suit.CLUBS),
                new Card(4,Suit.DIAMONDS),
                new Card(2,Suit.HEARTS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("FOURCARD"));
    }

    @Test
    public void 같은_숫자_2장이_1쌍이면_원페어다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.CLUBS),
                new Card(2,Suit.HEARTS),
                new Card(2,Suit.DIAMONDS),
                new Card(3,Suit.SPADES),
                new Card(4,Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("ONEPAIR"));
    }

    @Test
    public void 같은_숫자_2장이_2쌍이면_투페어다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.CLUBS),
                new Card(2,Suit.HEARTS),
                new Card(2,Suit.DIAMONDS),
                new Card(3,Suit.SPADES),
                new Card(3,Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("TWOPAIR"));
    }

    @Test
    public void 같은_숫자가_3장이면_트리플이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.CLUBS),
                new Card(2,Suit.HEARTS),
                new Card(3,Suit.DIAMONDS),
                new Card(3,Suit.SPADES),
                new Card(3,Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("TRIPLE"));
    }

    @Test
    public void 다섯장의_카드_정방향_연결은_스트레이트이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(10,Suit.CLUBS),
                new Card(6,Suit.HEARTS),
                new Card(8,Suit.DIAMONDS),
                new Card(9,Suit.SPADES),
                new Card(7,Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("STRAIGHT"));
    }

    @Test
    public void 에이스가_14일때_플러쉬는_마운틴이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(13,Suit.CLUBS),
                new Card(12,Suit.HEARTS),
                new Card(1,Suit.DIAMONDS),
                new Card(10,Suit.SPADES),
                new Card(11,Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("MOUNTIN"));
    }

    @Test
    public void 에이스가_1일때_플러쉬는_백스트레이트이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(5,Suit.CLUBS),
                new Card(3,Suit.HEARTS),
                new Card(1,Suit.DIAMONDS),
                new Card(4,Suit.SPADES),
                new Card(2,Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("BACKSTRAIGHT"));
    }

    @Test
    public void 백스트레이트_모양이_같으면_백스트레이트플러쉬이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(5,Suit.CLUBS),
                new Card(3,Suit.CLUBS),
                new Card(1,Suit.CLUBS),
                new Card(4,Suit.CLUBS),
                new Card(2,Suit.CLUBS)
        );
        boolean result = evaluator.check_back_straight_flush(cardList);
        assertThat(result, is(true));
    }

    @Test
    public void 스트레이트_모양이_같으면_스트레이트플러쉬이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(7,Suit.SPADES),
                new Card(8,Suit.SPADES),
                new Card(9,Suit.SPADES),
                new Card(10,Suit.SPADES),
                new Card(11,Suit.SPADES)
        );
        Boolean result = evaluator.check_straight_flush(cardList);
        assertThat(result, is(true));
    }

    @Test
    public void 원페어_트리플은_풀하우스이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(5,Suit.CLUBS),
                new Card(7,Suit.HEARTS),
                new Card(5,Suit.DIAMONDS),
                new Card(7,Suit.SPADES),
                new Card(7,Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("FULLHOUSE"));
    }

    @Test
    public void 스트레이트플러쉬_모양이_같으면_로얄스트레이트플러쉬이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.DIAMONDS),
                new Card(13,Suit.DIAMONDS),
                new Card(12,Suit.DIAMONDS),
                new Card(11,Suit.DIAMONDS),
                new Card(10,Suit.DIAMONDS)
        );
        Boolean result = evaluator.check_royal_straight_flush(cardList);
        assertThat(result, is(true));
    }

}