package com.edu.cnu.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ChungNamUniversity on 2017-04-27.
 */
public class SinglePlay {
    private int coin;
    private int card_num;
    private int set_Deck;
    private boolean isBat;
    private boolean isStart;
    private int batting;
    private Deck suffle_Deck;
    private Evaluator evaluator;
    private List<Card> player_Deck;
    private List<Card> computer_Deck;

    public SinglePlay() {
        // TODO Auto-generated constructor stub
        evaluator = new Evaluator();
    }

    public void init() {
        Scanner input = new Scanner(System.in);
        Ranking ranking = new Ranking();
        System.out.println("코인 개수 입력 : ");
        coin = input.nextInt();
        do {
            player_Deck = new ArrayList<Card>();
            computer_Deck = new ArrayList<Card>();
            do {
                System.out.println("5장(1), 7장(2)");
                card_num = input.nextInt();
            } while (card_num != 1 && card_num != 2);
            if (card_num == 1) {
                card_num = 5;
                set_Deck = 3;
            } else {
                card_num = 7;
                set_Deck = 4;
            }

            suffle_Deck = new Deck(card_num);

            for (int i = 0; i < card_num; i++) {
                player_Deck.add(suffle_Deck.drawCard());
                computer_Deck.add(suffle_Deck.drawCard());
            }

            System.out.println("-----------player_Deck-----------");
            for (Card card : player_Deck) {
                System.out.println("card : " + card.getRank() + "," + card.getSuit());
            }
            System.out.println("----------computer_Deck----------");
            for (Card card : computer_Deck) {
                set_Deck--;
                System.out.println("card : " + card.getRank() + "," + card.getSuit());
                if (set_Deck == 0) {
                    break;
                }
            }
            System.out.println("----------현재 보유 coin : " + coin + "----------");
            System.out.println("Batting(true), Die(false)");
            isBat = input.nextBoolean();
            if (isBat) {
                do {
                    System.out.println("배팅할 금액을 입력 : ");
                    batting = input.nextInt();
                } while (coin < batting);
                System.out.println("------------------------------------------");
                if (ranking.value(evaluator.evaluate(player_Deck)) < ranking.value(evaluator.evaluate(computer_Deck))) {
                    System.out.println("패배");
                    coin -= batting;
                } else if (ranking.value(evaluator.evaluate(player_Deck)) > ranking
                        .value(evaluator.evaluate(computer_Deck))) {
                    System.out.println("승");
                    coin += batting;
                } else {
                    System.out.println("무승부");
                }
            }
            System.out.println("------------------------------------------");
            System.out.println("나의 패는 : " + evaluator.evaluate(player_Deck));
            System.out.println("상대의 패는 : " + evaluator.evaluate(computer_Deck));
            System.out.println("computer");
            for (Card card : computer_Deck) {
                System.out.println("card : " + card.getRank() + "," + card.getSuit());
            }
            System.out.println("남은 coin : " + coin);
            System.out.println("------------------------------------------");
        } while (coin > 0);
    }
}
