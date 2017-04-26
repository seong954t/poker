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
    if (check_royal_straight_flush(cardList)) {
      return "ROYALSTRAIGHTFLUSH";
    }
    else if (check_back_straight_flush(cardList)) {
      return "BACKSTRAIGHTFLUSH";
    }
    else if (check_straight_flush(cardList)) {
      return "STRAIGHTFLUSH";
    }
    else if (checkfourcard(cardList)) {
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
    else if (checkTriple(cardList)) {
      return "TRIPLE";
    }
    else if (checkTwopair(cardList)) {
      return "TWOPAIR";
    } 
    else if (checkOnepair(cardList)) {
      return "ONEPAIR";
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
            // numMap에서 같은 Rank의 카드 개수 산출
            if (numMap.containsKey(card.getRank())) {
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
  
    public boolean check_royal_straight_flush(List<Card> cardList){
        Map<Suit, Integer> tempMap = new HashMap<Suit, Integer>(); // 플러쉬
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>(); // 원페어, 투페어, 트리플, 풀하우스
        Map<Integer, Integer> strMap = new HashMap<Integer, Integer>(); // 스트레이트

        Integer straight = 0; // 스트레이트 제어 변수
        Integer backStraight = 0; // 백스트레이트 제어 변수
        Integer mountin = 0; // 마운틴 제어 변수
        Integer flush =0;


        Collections.sort(cardList); // cardList의 원소를 Rank 값 오름차순으로 정렬

        for (Card card : cardList) { // test 코드의 Card List에 card 들을 가져온다.
            // tempMap에서 같은 Suit의 카드 개수 산출
            if (tempMap.containsKey(card.getSuit())) {
                Integer count = tempMap.get(card.getSuit());
    
            // A가 1 또는 14가 될 때의 경우를 나눈 스트레이트 구상
        for (Integer ind = 0; ind < 5; ind++) {
            Integer count = cardList.get(ind).compareTo(cardList.get((ind + 1) % 5));

            if(cardList.get(ind).getRank() == 1) {
                if(ind == 0) {
                    if(cardList.get(4).getRank() == 13) {
                        mountin += 1;
                        strMap.put(14, -1);
                    } else if (cardList.get(4).getRank() == 5) {
                        backStraight += 1;
                        strMap.put(1, -1);
                    }
                }
            } else {
                strMap.put(cardList.get(ind).getRank(), count);
            }
        }

        for (Integer num : strMap.values()) {
            if(num == -1) {
                straight += 1;
            }
        }
        // 플러쉬 될 조건
        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) == 5) {
                flush += 1;
            }
        }

        if(straight==4 && mountin ==1 && flush ==1)
            return  true;
        else
            return false;

    }
          
    public boolean check_back_straight_flush(List<Card> cardList){
        Map<Suit, Integer> tempMap = new HashMap<Suit, Integer>(); // 플러쉬
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>(); // 원페어, 투페어, 트리플, 풀하우스
        Map<Integer, Integer> strMap = new HashMap<Integer, Integer>(); // 스트레이트

        Integer straight = 0; // 스트레이트 제어 변수
        Integer backStraight = 0; // 백스트레이트 제어 변수
        Integer flush = 0; // 플러쉬 제어 변수

        Collections.sort(cardList); // cardList의 원소를 Rank 값 오름차순으로 정렬

        for (Card card : cardList) { // test 코드의 Card List에 card 들을 가져온다.
            // tempMap에서 같은 Suit의 카드 개수 산출
            if (tempMap.containsKey(card.getSuit())) {
                Integer count = tempMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                tempMap.put(card.getSuit(), count);
            } else {
                tempMap.put(card.getSuit(), new Integer(1));
            }
            // numMap에서 같은 Rank의 카드 개수 산출
            if (numMap.containsKey(card.getRank())) {
                Integer count = numMap.get(card.getRank());
                count = new Integer(count.intValue() + 1);
                numMap.put(card.getRank(), count);
            } else {
                numMap.put(card.getRank(), new Integer(1));
            }

        }
        // A가 1 또는 14가 될 때의 경우를 나눈 스트레이트 구상
        for (Integer ind = 0; ind < 5; ind++) {
            Integer count = cardList.get(ind).compareTo(cardList.get((ind + 1) % 5));

            if(cardList.get(ind).getRank() == 1) {
                if(ind == 0) {
                    if(cardList.get(4).getRank() == 13) {
                        strMap.put(14, -1);
                    } else if (cardList.get(4).getRank() == 5) {
                        backStraight += 1;
                        strMap.put(1, -1);
                    }
                }
            } else {
                strMap.put(cardList.get(ind).getRank(), count);
            }
        }

        for (Integer num : strMap.values()) {
            if(num == -1) {
                straight += 1;
            }
        }
        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) == 5) {
                flush += 1;
            }
        }

        if(straight==4 && backStraight ==1 && flush ==1)
            return  true;
        else
            return false;
    }
    public boolean check_straight_flush(List<Card> cardList){
        Map<Suit, Integer> tempMap = new HashMap<Suit, Integer>(); // 플러쉬
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>(); // 원페어, 투페어, 트리플, 풀하우스
        Map<Integer, Integer> strMap = new HashMap<Integer, Integer>(); // 스트레이트

        Integer straight = 0; // 스트레이트 제어 변수
        Integer flush = 0; // 플러쉬 제어 변수

        Collections.sort(cardList); // cardList의 원소를 Rank 값 오름차순으로 정렬
        for (Card card : cardList) { // test 코드의 Card List에 card 들을 가져온다.
            // tempMap에서 같은 Suit의 카드 개수 산출
            if (tempMap.containsKey(card.getSuit())) {
                Integer count = tempMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                tempMap.put(card.getSuit(), count);
            } else {
                tempMap.put(card.getSuit(), new Integer(1));
            }
            // numMap에서 같은 Rank의 카드 개수 산출
            if (numMap.containsKey(card.getRank())) {
                Integer count = numMap.get(card.getRank());
                count = new Integer(count.intValue() + 1);
                numMap.put(card.getRank(), count);
            } else {
                numMap.put(card.getRank(), new Integer(1));
            }

        }
        // A가 1 또는 14가 될 때의 경우를 나눈 스트레이트 구상
        for (Integer ind = 0; ind < 5; ind++) {
            Integer count = cardList.get(ind).compareTo(cardList.get((ind + 1) % 5));
            strMap.put(cardList.get(ind).getRank(), count);
        }

        for (Integer num : strMap.values()) {
            if(num == -1) {
                straight += 1;
            }
        }
        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) == 5) {
                flush += 1;
            }
        }

        if(straight==4 && flush ==1)
            return true;
        else
            return false;
    }
}