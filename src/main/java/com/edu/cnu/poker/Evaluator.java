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

        Map<Suit, Integer> tempMap = new HashMap<Suit, Integer>(); // 플러쉬
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>(); // 원페어, 투페어, 트리플, 풀하우스
        Map<Integer, Integer> strMap = new HashMap<Integer, Integer>(); // 스트레이트



        Integer onetwoApart = 0; // 원페어, 투페어 구분 변수

        Integer straight = 0; // 스트레이트 제어 변수
        Integer backStraight = 0; // 백스트레이트 제어 변수
        Integer mountin = 0; // 마운틴 제어 변수

        Integer onePair = 0; // 원페어 제어 변수
        Integer twoPair = 0; // 투페어 제어 변수
        Integer triple = 0; // 트리플 제어 변수
        Integer fourCard = 0; // 포카드 제어 변수

        Integer flush = 0; // 플러쉬 제어 변수

        Collections.sort(cardList); // cardList의 원소를 Rank 값 오름차순으로 정렬


        /*  장은정 코드  */

        /*  장은정 코드  */
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

        check_royal_straight_flush(tempMap,strMap, numMap,cardList,mountin, backStraight, straight, flush);

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

        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) == 5) {
                flush += 1;
            }
        }

        if (straight == 4) {
            if (backStraight == 1) {
                if(flush == 1) {
                    return "BACKSTRAIGHTFLUSH";
                } else {
                    return "BACKSTRAIGHT";
                }
            } else if (mountin == 1) {
                if (flush == 1) {
                    return "ROYALSTRAIGHTFLUSH";
                } else {
                    return "MOUNTIN";
                }
            } else if (flush == 1) {
                return "STRAIGHTFLUSH";
            }
            return "STRAIGHT";
        }

        if (flush == 1 && straight != 4) {
            return "FLUSH";
        }

        for (Integer num : numMap.keySet()) {
            if (numMap.get(num) == 2) {
                onetwoApart += 1;
            } else if(numMap.get(num) == 3) {
                triple += 1;
            } else if (numMap.get(num) == 4) {
                fourCard += 1;
            }
        }

        if(onetwoApart == 1) {
            onePair += 1;
        } else if(onetwoApart == 2) {
            twoPair += 1;
        }

        if(onePair == 1 && triple != 1) {
            return "ONEPAIR";
        } else if (twoPair == 1) {
            return "TWOPAIR";
        } else if (onePair == 1 && triple == 1) {
            return "FULLHOUSE";
        } else if (onePair != 1 && triple == 1) {
            return "TRIPLE";
        } else if (fourCard == 1) {
            return "FOURCARD";
        }

        return "NOTHING";

    }

    public boolean check_royal_straight_flush(Map<Suit, Integer> tempMap, Map<Integer, Integer> numMap,Map<Integer, Integer> strMap, List<Card> cardList, int mountin, int backStraight, int straight, int flush){
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
                        mountin += 1;
                        strMap.put(14, -1);
                    }
                }
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
    public boolean check_back_straight_flush(Map<Suit, Integer> tempMap, Map<Integer, Integer> numMap,Map<Integer, Integer> strMap, List<Card> cardList, int mountin, int backStraight, int straight, int flush){
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
                    if (cardList.get(4).getRank() == 5) {
                        backStraight += 1;
                        strMap.put(1, -1);
                    }
                }
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
    public boolean check_straight_flush(Map<Suit, Integer> tempMap, Map<Integer, Integer> numMap,Map<Integer, Integer> strMap, List<Card> cardList, int mountin, int backStraight, int straight, int flush){

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