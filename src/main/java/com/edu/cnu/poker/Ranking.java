package com.edu.cnu.poker;

/**
 * Created by ChungNamUniversity on 2017-04-27.
 */
public class Ranking {
    public int value(String rank) {
        if (rank.equals("ROYALSTRAIGHTFLUSH")) {
            return 13;
        } else if (rank.equals("BACKSTRAIGHTFLUSH")) {
            return 12;
        } else if (rank.equals("STRAIGHTFLUSH")) {
            return 11;
        } else if (rank.equals("FOURCARD")) {
            return 10;
        } else if (rank.equals("FULLHOUSE")) {
            return 9;
        } else if (rank.equals("FLUSH")) {
            return 8;
        } else if (rank.equals("BACKSTRAIGHT")) {
            return 7;
        } else if (rank.equals("STRAIGHT")) {
            return 6;
        } else if (rank.equals("MOUNTAIN")) {
            return 5;
        } else if (rank.equals("TRIPLE")) {
            return 4;
        } else if (rank.equals("TWOPAIR")) {
            return 3;
        } else if (rank.equals("ONEPAIR")) {
            return 2;
        } else {
            return 1;
        }
    }
}
