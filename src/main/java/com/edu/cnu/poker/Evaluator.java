package com.edu.cnu.poker;

import java.util.Collections;
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

    public boolean STRAIGHT(List<Card> cardList) {
        int straight = 0;
        int saveRank = cardList.get(0).getRank() - 1;
        for (Card card : cardList) {
            if (saveRank+1 == card.getRank()) {
                straight++;
            }
            saveRank = card.getRank();
        }
        if(straight >= 5){
            return true;
        }
        return false;
    }

    public boolean FLUSH(List<Card> cardList) {
        Map<Suit, Integer> numMap = new HashMap<Suit, Integer>();
        for (Card card : cardList) {
            if (numMap.containsKey(card.getSuit())) {
                Integer count = numMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                numMap.put(card.getSuit(), count);
            } else {
                numMap.put(card.getSuit(), new Integer(1));
            }
        }
        for (Suit key : numMap.keySet()) {
            if (numMap.get(key) >= 5) {
                return true;
            }
        }
        return false;
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
            if (numMap.get(key) >= 4) {
                return true;
            }
        }
        return false;
    }

}


