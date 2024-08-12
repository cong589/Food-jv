package control.ajax;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getMessageAdmin")
public class GetMessageAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");
        int userID = Integer.parseInt(request.getParameter("id"));
        ChatRoomDB chatRoomDB = new ChatRoomDB();
        MessageDB messageDB = new MessageDB();
        ChatRoom checkChatRoom = chatRoomDB.findChatRoomByUserId(userID);
        if (checkChatRoom == null) {
            ChatRoom chatRoom = chatRoomDB.createChatRoom(userID);
            response.getWriter().print(chatRoom.getChatRoomID());
            return;
        }
        List<Message> messageList = messageDB.getAllMessageByChatRoomID(checkChatRoom.getChatRoomID());
        List<Message> messageListResult = new ArrayList<>();
        for (Message message : messageList) {
            Message message1 = new Message();
            message1.setMessageID(message.getMessageID());
            message1.setContent(message.getContent());
            message1.setTimestamp(message.getTimestamp());
            message1.setSender(message.getSender());
            message1.setChatRoomID(message.getChatRoomID());
            message1.setUser(message.getUser());
            messageListResult.add(message1);
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(messageListResult);
        response.getWriter().print(json);
    }
}
