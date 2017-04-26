package com.edu.cnu.poker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cse on 2017-04-17.
 */
public class Evaluator {

    Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
    Map<Suit, Integer> tempMap = new HashMap<Suit, Integer>();

    public String evaluate(List<Card> cardList) {

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

        return "NOTHING";
    }

    public boolean checkOnepair(List<Card> cardList) {

        // 각 card의 rank 값을 비교하여 같은 rank가 몇 번씩 나오는지 count해서 numMap에 rank와 count 저장
        for (Card card : cardList) {
            if (numMap.containsKey(card.getRank())) {
                Integer count = numMap.get(card.getRank());
                count = new Integer(count.intValue() + 1);
                numMap.put(card.getRank(), count);
            } else {
                numMap.put(card.getRank(), new Integer(1));
            }
        }
        for (Integer num : numMap.keySet()) {
            if (numMap.get(num) == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTwopair(List<Card> cardList) {

        Integer onePairCount = 0; // 원페어가 몇 개 존재하는 지 count하는 변수

        // 각 card의 rank 값을 비교하여 같은 rank가 몇 번씩 나오는지 count해서 numMap에 rank와 count 저장
        for (Card card : cardList) {
            if (numMap.containsKey(card.getRank())) {
                Integer count = numMap.get(card.getRank());
                count = new Integer(count.intValue() + 1);
                numMap.put(card.getRank(), count);
            } else {
                numMap.put(card.getRank(), new Integer(1));
            }
        }
        for (Integer num : numMap.keySet()) {
            if (numMap.get(num) == 2) {
                onePairCount += 1;
            }
        }

        if (onePairCount == 2) {
            //원페어가 두 개 존재해야 투페어이다.
            return true;
        }

        return false;
    }

    public boolean checkTriple(List<Card> cardList) {

        for (Card card : cardList) {
            if (numMap.containsKey(card.getRank())) {
                Integer count = numMap.get(card.getRank());
                count = new Integer(count.intValue() + 1);
                numMap.put(card.getRank(), count);
            } else {
                numMap.put(card.getRank(), new Integer(1));
            }
        }

        for(Integer num : numMap.keySet()) {
            if(numMap.get(num) == 3) {
                return true;
            }
        }
        return false;
    }

}


