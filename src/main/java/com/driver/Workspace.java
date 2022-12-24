package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        super(emailId,Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
        // The inboxCapacity is equal to the maximum value an integer can store.

    }

    public void addMeeting(Meeting meeting){
        calendar.add(meeting);
        //add the meeting to calendar
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        if(calendar.size() == 0) {
            return 0;
        }
        Collections.sort(calendar,new Comparator<Meeting>() {
            public int compare(Meeting a,Meeting b) {
                return a.getEndTime().compareTo(b.getEndTime());
            }
        });
        LocalTime time = calendar.get(0).getEndTime();
        int count=1;
        for(int i = 1; i < calendar.size(); i++) {
            if(calendar.get(i).getStartTime().compareTo(time) > 0) {
                count++;
                time = calendar.get(i).getEndTime();
            }
        }
        return count;
    }
}
