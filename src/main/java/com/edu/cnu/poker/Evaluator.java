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

}


