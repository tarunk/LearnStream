package com.tarun.votes;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TopVotedCandidate {
    private Map<Integer, Integer> votes;
    private TreeMap<Integer, Integer> timeMap;

    public TopVotedCandidate(int[] persons, int[] times) {
        int maxVote = -1;
        votes = new HashMap<>();
        timeMap = new TreeMap<>();

        for (int i = 0; i < persons.length; ++i) {
            if (!votes.containsKey(persons[i])) {
                votes.put(persons[i], 1);
            } else {
                votes.put(persons[i], votes.get(persons[i]) + 1);
            }

            if (votes.get(persons[i]) >= maxVote) {
                maxVote = votes.get(persons[i]);
                timeMap.put(times[i], persons[i]);
            } else {
                int previousMax = timeMap.get(times[i - 1]);
                timeMap.put(times[i], previousMax);
            }
        }
    }

    public int q(int t) {
        int floorTime = timeMap.floorKey(t);
        return timeMap.get(floorTime);
    }

    public static void main(String []args) {
        int []persons = {0, 1, 1, 0, 0, 1, 0};
        int []times = {0, 5, 10, 15, 20, 25, 30};

        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(persons, times);
        System.out.println(topVotedCandidate.q(3));
        System.out.println(topVotedCandidate.q(12));
        System.out.println(topVotedCandidate.q(25));
    }
}
