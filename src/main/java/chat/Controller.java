package chat;

import chat.model.ChatRoom;
import chat.model.Message;
import chat.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador de la aplicación. Por favor, revise detenidamente la clase y complete las partes omitidas
 * atendiendo a los comentarios indicados mediante @TODO_
 */
public class Controller {

    private Session session;

    /**
     * Crea un nuevo controlador
     */
    public Controller() {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        this.session = sessionFactory.openSession();
    }

    /**
     * Crea un nuevo usuario en la aplicación
     *
     * @param nickname nombre de usuario
     * @return el nuevo usuario creado
     * @throws SQLException
     */
    public User createUser(String nickname) throws SQLException {
        User user = new User(nickname);

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

        return user;
    }

    /**
     * Crea una nueva sala de chat
     *
     * @param user   usuario que crea la sala de chat
     * @param CRname nombre de la sala de chat
     * @return la nueva sala de chat creada
     * @throws SQLException
     */
    public ChatRoom createChatRoom(User user, String CRname) throws SQLException {
        ChatRoom chatRoom = new ChatRoom(CRname, user);

        session.beginTransaction();
        session.save(chatRoom);
        session.getTransaction().commit();

        return chatRoom;
    }

    /**
     * Crea un nuevo mensaje en una sala de chat
     *
     * @param user     usuario que crea el mensaje
     * @param chatRoom sala de chat en la que se crea el mensaje
     * @param content  contenido del mensaje
     * @throws SQLException
     */
    public void sendMessage(User user, ChatRoom chatRoom, String content) throws SQLException {
        Message message = new Message(content, chatRoom, user);

        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();
    }

    /**
     * Recupera un listado con todas las salas de chat
     *
     * @return listado con las salas de chat
     * @throws SQLException
     */
    public List<ChatRoom> getChatRooms() throws SQLException {
        Query query = session.createQuery("FROM ChatRoom");
        return query.getResultList();
    }

    /**
     * Borra los mensajes que envió ese usuario al chat
     *
     * @param user     usuario que creó los mensajes
     * @param chatRoom sala de chat en la que se crearon los mensajes
     * @return número de mensajes borrados(int)
     * @throws SQLException
     */
    public int deleteMessages(User user, ChatRoom chatRoom) throws SQLException {
        // @TODO complete este metodo para eliminar los mensajes que envió el usuario que tiene la sesión abierta
        // @TODO en el chat seleccionado
        // utilice la función "createQuery" dentro de una transacción
        // dentro del "createQuery" realice la consulta hql con "delete from"

        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Message WHERE creator = :creator AND chatRoom = :chatRoom");
        query.setParameter("creator", user);
        query.setParameter("chatRoom", chatRoom);
        int messagesDeleted = query.executeUpdate();
        session.getTransaction().commit();
        return messagesDeleted;
    }
}
