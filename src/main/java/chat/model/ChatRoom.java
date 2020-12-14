package chat.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Modela una sala de chat. Por favor, revise detenidamente la clase y complete las partes omitidas
 * atendiendo a los comentarios indicados mediante @TODO_
 */
@Entity
@Table(name = "chatRoom")
public class ChatRoom {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creator")
    private User creator;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private Set<Message> messages;

    public ChatRoom() {

    }

    public ChatRoom(String name, User creator) {
        this.name = name;
        this.creator = creator;
        this.messages = new HashSet<Message>();
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public User getCreator() {
        return this.creator;
    }

    public Set<Message> getMessages() {
        return this.messages;
    }
}
