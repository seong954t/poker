package com.edu.cnu.poker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cse on 2017-04-17.
 */
public class Evaluator {
    public String evaluate(List<Card> cardList) {
        Map<Suit, Integer> tempMap = new HashMap<Suit, Integer>();

        for (Card card : cardList) {
            if (tempMap.containsKey(card.getSuit())) {
                Integer count = tempMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                tempMap.put(card.getSuit(), count);
            } else {
                tempMap.put(card.getSuit(), new Integer(1));
            }
        }

        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) == 5) {
                return "FLUSH";
            }
        }

        if (checkfourcard(cardList)) {
            return "FOURCARD";
        }
        return "NOTHING";
    }

    public boolean checkfourcard(List<Card> cardList) {
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
        for (Card card : cardList) {
            if(numMap.containsKey(card.getRank())){
                Integer count = numMap.get(card.getRank());
                count = new Integer(count.intValue() + 1);
                numMap.put(card.getRank(), count);
            } else {
                numMap.put(card.getRank(), new Integer(1));
            }
        }
        for (Integer key : numMap.keySet()){
            if(numMap.get(key) == 4){
                return true;
            }
        }
        return false;
    }
}


