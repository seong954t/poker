package com.edu.cnu.poker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cse on 2017-04-17.
 */
public class Evaluator {
	public String evaluate(List<Card> cardList) {
    if (checkfourcard(cardList)) {
            return "FOURCARD";
    }
		else if (FLUSH(cardList)) {
			return "FLUSH";
		} 
    else if (STRAIGHT(cardList)) {
			if (cardList.get(0).getRank() == 1) {
				return "BACKSTRAIGHT";
			}
			return "STRAIGHT";
		}
    else if (checkTwopair(cardList)) {
      return "TWOPAIR";
    } 
    else if (checkOnepair(cardList)) {
      return "ONEPAIR";
    } 
    else if (checkTriple(cardList)) {
      return "TRIPLE";
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

    public boolean checkOnepair(List<Card> cardList) {
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
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

        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();

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

        for(Integer num : numMap.keySet()) {
            if(numMap.get(num) == 3) {
                return true;
            }
        }
        return false;
    }

}


