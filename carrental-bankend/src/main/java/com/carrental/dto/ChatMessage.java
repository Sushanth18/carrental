package com.carrental.dto;

import java.time.LocalDateTime;


public class ChatMessage {
    private MessageType type;
    private String content;
    private Long vechileId;
    private String sender;
    private String receiver;
	  
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE,
        TYPING
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
 

	public void setVechileId(Long vechileId) {
		this.vechileId = vechileId;
	}
	
	public Long getVechileId() {
		return vechileId;
	}
}
