package com.example.tcp;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.models.User;
import com.example.repositories.UserRepository;

@Component
public class TcpRequestHandler extends IoHandlerAdapter {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        session.close(true);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        logger.info("[RQHANDLER][MESSAGE_RECEIVED: " + message.toString() + "]");

        if (message.equals("NEW_USER")) {
            User user = new User("Tom and Jery", "tomjery@gmail.com");
            userRepository.save(user);
        } else if (message.equals("DEL_USER")) {
            // ..
        }
    }
}
