package com.daniil.messenger.Models;

public class Chat {
    private String sender;
    private String message;
    private String recipient;

    public Chat() {
    }

    public Chat(String sender, String message, String recipient) {
        this.sender = sender;
        this.message = message;
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

}
