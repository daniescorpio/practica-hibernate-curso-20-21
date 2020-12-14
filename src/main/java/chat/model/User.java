package chat.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Modela un usuario. Por favor, revise detenidamente la clase y complete las partes omitidas
 * atendiendo a los comentarios indicados mediante @TODO_
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private Set<ChatRoom> chatRooms;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private Set<Message> messages;

    public User () {

    }

    public User (String username) {
        this.username = username;
        this.chatRooms = new HashSet<ChatRoom>();
        this.messages = new HashSet<Message>();
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername () {
        return this.username;
    }

    public Set<ChatRoom> getChatRooms() {
        return this.chatRooms;
    }

    public Set<Message> getMessages() {
        return this.messages;
    }
}
