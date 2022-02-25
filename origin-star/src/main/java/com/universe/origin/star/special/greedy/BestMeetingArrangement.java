package com.universe.origin.star.special.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 最优会议室安排问题
 * 一个时间段内安排最多的会议
 * 选择开始时间早但是结束时间段的会议  也就是结束时间最早的集合
 */
public class BestMeetingArrangement {

    public static void main(String[] args) {
        BestMeetingArrangement bestMeetingArrangement = new BestMeetingArrangement();
        Meeting m1 = new Meeting(3, 6, 1);
        Meeting m2 = new Meeting(1, 4, 2);
        Meeting m3 = new Meeting(5, 7, 3);
        Meeting m4 = new Meeting(2, 5, 4);
        Meeting m5 = new Meeting(5, 9, 5);
        Meeting m6 = new Meeting(3, 8, 6);
        Meeting m7 = new Meeting(8, 11, 7);
        Meeting m8 = new Meeting(6, 10, 8);
        Meeting m9 = new Meeting(8, 12, 9);
        Meeting m10 = new Meeting(12, 14, 10);
        Meeting[] meetings = new Meeting[]{m1,m2,m3,m4,m5,m6,m7,m8,m9,m10};
        System.out.println(bestMeetingArrangement.bestMeeting(meetings,0,24).toString());
    }

    public List bestMeeting(Meeting[] meetings, int begain, int end) {
        //结束时间升序排序
        Arrays.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.end-o2.end;
            }
        });

        List res = new ArrayList<>();
        for (int i = 0; i < meetings.length; i++) {
            if (meetings[i].start > begain && meetings[i].end < end) {
                res.add(meetings[i]);
                begain =meetings[i].end;
            }
        }
        return res;
    }
}

class Meeting {
    int start;
    int end;
    int number;

    @Override
    public String toString() {
        return "Meeting{" +
                "start=" + start +
                ", end=" + end +
                ", number=" + number +
                '}';
    }

    public Meeting(int start, int end, int number) {
        this.start = start;
        this.end = end;
        this.number = number;

    }
}
