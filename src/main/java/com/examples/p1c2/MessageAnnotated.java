package com.examples.p1c2;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by ka40215 on 9/26/15.
 */
@Entity
@Table(name = "MESSAGESANNOTATED")
public class MessageAnnotated {
    @Id @GeneratedValue
    @Column(name = "MESSAGE_ID")
    private Long id;

    @Column(name = "MESSAGE_TEXT")
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NEXT_MESSAGE_ID")
    private MessageAnnotated nextMessage;
    MessageAnnotated() {}

    public MessageAnnotated(String text) {
        this.text = text;
    }
    public Long getId() {
        return id;
    }
    private void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public MessageAnnotated getNextMessage() {
        return nextMessage;
    }
    public void setNextMessage(MessageAnnotated nextMessage) {
        this.nextMessage = nextMessage;
    }
}
