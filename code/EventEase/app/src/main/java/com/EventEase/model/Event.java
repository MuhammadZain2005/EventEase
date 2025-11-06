package com.EventEase.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Event {

    public String id;
    public String title;
    public String location;
    public long startsAtEpochMs;
    public int capacity;
    public String notes;
    public String guidelines;
    public String posterUrl;
    public int waitlistCount;

    public ArrayList<String> waitlist;
    public ArrayList<String> admitted;
    public String organizerId;
    public long createdAtEpochMs;
    public String qrPayload;

    private ArrayList<String> selectedEntrants;
    private ArrayList<String> notSelectedEntrants;
    private ArrayList<String> cancelledEntrants;

    public Event() {
        selectedEntrants = new ArrayList<>();
        notSelectedEntrants = new ArrayList<>();
        cancelledEntrants = new ArrayList<>();
        waitlist = new ArrayList<>();
        admitted = new ArrayList<>();
    }

    public Date getStartAt() {
        return new Date(startsAtEpochMs);
    }

    public Event(String id, String title, String location, long startsAtEpochMs, int capacity,
                 String notes, String guidelines, String posterUrl, int waitlistCount,
                 String organizerId, long createdAtEpochMs, String qrPayload) {

        this.id = id;
        this.title = title;
        this.location = location;
        this.startsAtEpochMs = startsAtEpochMs;
        this.capacity = capacity;
        this.notes = notes;
        this.guidelines = guidelines;
        this.posterUrl = posterUrl;
        this.waitlistCount = waitlistCount;
        this.organizerId = organizerId;
        this.createdAtEpochMs = createdAtEpochMs;
        this.qrPayload = qrPayload;

        this.selectedEntrants = new ArrayList<>();
        this.notSelectedEntrants = new ArrayList<>();
        this.cancelledEntrants = new ArrayList<>();
        this.waitlist = new ArrayList<>();
        this.admitted = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public static Event fromMap(Map<String, Object> map) {
        if (map == null) return null;

        Event event = new Event();
        event.id = (String) map.get("id");
        event.title = (String) map.get("title");
        event.location = (String) map.get("location");

        Object startsObj = map.get("startsAtEpochMs");
        if (startsObj instanceof Number) event.startsAtEpochMs = ((Number) startsObj).longValue();

        Object capacityObj = map.get("capacity");
        if (capacityObj instanceof Number) event.capacity = ((Number) capacityObj).intValue();

        event.notes = (String) map.get("notes");
        event.guidelines = (String) map.get("guidelines");
        event.posterUrl = (String) map.get("posterUrl");

        Object waitlistCountObj = map.get("waitlistCount");
        if (waitlistCountObj instanceof Number) event.waitlistCount = ((Number) waitlistCountObj).intValue();

        event.organizerId = (String) map.get("organizerId");

        Object createdAtObj = map.get("createdAtEpochMs");
        if (createdAtObj instanceof Number) event.createdAtEpochMs = ((Number) createdAtObj).longValue();

        event.qrPayload = (String) map.get("qrPayload");

        event.selectedEntrants = new ArrayList<>();
        if (map.get("selectedEntrants") instanceof List) {
            for (Object o : (List<?>) map.get("selectedEntrants")) {
                if (o instanceof String) event.selectedEntrants.add((String) o);
            }
        }

        event.notSelectedEntrants = new ArrayList<>();
        if (map.get("notSelectedEntrants") instanceof List) {
            for (Object o : (List<?>) map.get("notSelectedEntrants")) {
                if (o instanceof String) event.notSelectedEntrants.add((String) o);
            }
        }

        event.cancelledEntrants = new ArrayList<>();
        if (map.get("cancelledEntrants") instanceof List) {
            for (Object o : (List<?>) map.get("cancelledEntrants")) {
                if (o instanceof String) event.cancelledEntrants.add((String) o);
            }
        }

        event.waitlist = new ArrayList<>();
        if (map.get("waitlist") instanceof List) {
            for (Object o : (List<?>) map.get("waitlist")) {
                if (o instanceof String) event.waitlist.add((String) o);
            }
        }

        event.admitted = new ArrayList<>();
        if (map.get("admitted") instanceof List) {
            for (Object o : (List<?>) map.get("admitted")) {
                if (o instanceof String) event.admitted.add((String) o);
            }
        }

        return event;
    }



    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public long getStartsAtEpochMs() { return startsAtEpochMs; }
    public int getCapacity() { return capacity; }
    public String getNotes() { return notes; }
    public String getGuidelines() { return guidelines; }
    public String getPosterUrl() { return posterUrl; }
    public int getWaitlistCount() { return waitlistCount; }
    public ArrayList<String> getSelectedEntrants() { return selectedEntrants; }
    public ArrayList<String> getNotSelectedEntrants() { return notSelectedEntrants; }
    public ArrayList<String> getCancelledEntrants() { return cancelledEntrants; }
    public ArrayList<String> getWaitlist() { return waitlist; }
    public ArrayList<String> getAdmitted() { return admitted; }
    public String getOrganizerId() { return organizerId; }
    public long getCreatedAtEpochMs() { return createdAtEpochMs; }
    public String getQrPayload() { return qrPayload; }

    public void setSelectedEntrants(ArrayList<String> selectedEntrants) { this.selectedEntrants = selectedEntrants; }
    public void setNotSelectedEntrants(ArrayList<String> notSelectedEntrants) { this.notSelectedEntrants = notSelectedEntrants; }
    public void setCancelledEntrants(ArrayList<String> cancelledEntrants) { this.cancelledEntrants = cancelledEntrants; }
    public void setWaitlist(ArrayList<String> waitlist) { this.waitlist = waitlist; }
    public void setAdmitted(ArrayList<String> admitted) { this.admitted = admitted; }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("location", location);
        map.put("startsAtEpochMs", startsAtEpochMs);
        map.put("capacity", capacity);
        map.put("notes", notes);
        map.put("guidelines", guidelines);
        map.put("posterUrl", posterUrl);
        map.put("waitlistCount", waitlistCount);
        map.put("organizerId", organizerId);
        map.put("createdAtEpochMs", createdAtEpochMs);
        map.put("qrPayload", qrPayload);
        map.put("selectedEntrants", selectedEntrants);
        map.put("notSelectedEntrants", notSelectedEntrants);
        map.put("cancelledEntrants", cancelledEntrants);
        map.put("waitlist", waitlist);
        map.put("admitted", admitted);
        return map;
    }

    public void setId(String id) {
        this.id = id;
    }
}
