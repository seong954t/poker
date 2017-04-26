package com.edu.cnu.poker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cse on 2017-04-17.
 */
public class Evaluator {
    public String evaluate(List<Card> cardList) {
        Collections.sort(cardList);
        if (checkfourcard(cardList)) {
            return "FOURCARD";
        } else if (FLUSH(cardList)) {
            return "FLUSH";
        } else if(checkmountain(cardList)){
            return "MOUNTAIN";
        }
        else if (STRAIGHT(cardList)) {
            if (cardList.get(0).getRank() == 1) {
                return "BACKSTRAIGHT";
            }
            return "STRAIGHT";
        }
        return "NOTHING";
    }

    public boolean checkfourcard(List<Card> cardList) {
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
        for (Card card : cardList) {
            if (numMap.containsKey(card.getRank())) {
                Integer count = numMap.get(card.getRank());
                count = new Integer(count.intValue() + 1);
                numMap.put(card.getRank(), count);
            } else {
                numMap.put(card.getRank(), new Integer(1));
            }
        }
        for (Integer key : numMap.keySet()) {
            if (numMap.get(key) == 4) {
                return true;
            }
        }
        return false;
    }
}


