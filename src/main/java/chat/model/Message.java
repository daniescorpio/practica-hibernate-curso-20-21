package chat.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Modela un mensaje. Por favor, revise detenidamente la clase y complete las partes omitidas
 * atendiendo a los comentarios indicados mediante @TODO_
 */
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "chatRoom")
    private ChatRoom chatRoom;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creator")
    private User creator;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ts")
    private Date ts;

    public Message () {

    }

    public Message (String text, ChatRoom chatRoom, User creator) {
        this.text = text;
        this.chatRoom = chatRoom;
        this.creator = creator;
    }

    public String getText() {
        return this.text;
    }

    public User getCreator() {
        return this.creator;
    }

    public ChatRoom getChatRoom() {
        return this.chatRoom;
    }

    public String toString() {
        return "[" + this.creator.getUsername() + "]: " + text;
    }
}
