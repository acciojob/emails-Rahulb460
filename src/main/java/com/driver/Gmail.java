package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Gmail extends Email {
    private LinkedList<Mail> Inbox;
    private LinkedList<Mail> Trash;
    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.Inbox = new LinkedList<>();
        this.Trash = new LinkedList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        Mail newMail = new Mail(date,sender,message);
        if(Inbox.size() == inboxCapacity) {
            Trash.add(Inbox.poll());
        }
        Inbox.addLast(newMail);
    }

    public void deleteMail(String message){
        for(int i = 0; i < Inbox.size(); i++) {
            if(Inbox.get(i).message == message) {
                Inbox.remove(i);
            }
        }
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

    }

    public String findLatestMessage(){
        if(Inbox.size() == 0) {
            return null;
           }
        else {
            Mail mail = Inbox.getLast();
            return mail.message;
        }
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

    }

    public String findOldestMessage(){
        if(Inbox.size() != 0) {
            Mail mail = Inbox.getFirst();
            return mail.message;
        }
        else {
            return null;
        }
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

    }

    public int findMailsBetweenDates(Date start, Date end) {
        int count = 0;
        for (int i = 0; i < Inbox.size(); i++) {
            if(Inbox.get(i).date.compareTo(start) >= 0 && Inbox.get(i).date.compareTo(end) <= 0) {
                count++;
            }
        }
        return count;
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

    }

    public int getInboxSize(){
        return Inbox.size();
        // Return number of mails in inbox

    }

    public int getTrashSize(){
        return Trash.size();
        // Return number of mails in Trash

    }

    public void emptyTrash(){
        Trash.clear();
        // clear all mails in the trash

    }

    public int getInboxCapacity() {
        return inboxCapacity;
        // Return the maximum number of mails that can be stored in the inbox
    }
}
