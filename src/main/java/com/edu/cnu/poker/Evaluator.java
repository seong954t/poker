package com.edu.cnu.poker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cse on 2017-04-17.
 */
public class Evaluator {
	public String evaluate(List<Card> cardList) {
		if (FLUSH(cardList)) {
			return "FLUSH";
		} else if (STRAIGHT(cardList)) {
			if (cardList.get(0).getRank() == 1) {
				return "BACKSTRAIGHT";
			}
			return "STRAIGHT";
		}
		return "NOTHING";
	}

	public boolean STRAIGHT(List<Card> cardList) {
		return 4 == cardList.get(4).getRank() - cardList.get(0).getRank();
	}

	public boolean FLUSH(List<Card> cardList) {
		Suit suit = cardList.get(0).getSuit();
		for (Card card : cardList) {
			if (suit != card.getSuit()) {
				return false;
			}
		}
		return true;
	}

}
