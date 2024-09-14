package com.anish.complex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinimumChairs {

    // Helper class to store time and type of event
    static class Event {
        int time;   // time in minutes from 00:00
        String type; // "in" or "out"
        public Event(int time, String type) {
            this.time = time;
            this.type = type;
        }
    }

    // Function to convert time in "HH:MM" format to total minutes from 00:00
    private static int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    // Function to find the minimum number of chairs required
    public static int findMinimumChairs(String[][] timings) {
        List<Event> events = new ArrayList<>();
        // Add in and out events
        for (String[] timing : timings) {
            int entryTime = convertToMinutes(timing[0]);
            int exitTime = convertToMinutes(timing[1]);
            events.add(new Event(entryTime, "in"));
            events.add(new Event(exitTime, "out"));
        }

        // Sort events: first by time, then by type ("out" before "in" if times are equal)
        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                if (e1.time == e2.time) {
                    return e1.type.compareTo(e2.type);
                }
                return Integer.compare(e1.time, e2.time);
            }
        });

        int maxChairs = 0;
        int currentChairs = 0;

        // Process events to track chairs in use
        for (Event event : events) {
            if (event.type.equals("in")) {
                currentChairs++;
                maxChairs = Math.max(maxChairs, currentChairs);
            } else {
                currentChairs--;
            }
        }

        return maxChairs;
    }

    public static void main(String[] args) {
        // Employee log in and log out times
        String[][] timings = { {"09:00", "12:30"},
                {"10:00", "12:00"},
                {"14:00", "18:00"},
                {"20:00", "21:00"}
        };

        // Call the function and print the result
        int result = findMinimumChairs(timings);
        System.out.println("Minimum number of chairs required: " + result);
    }
}
