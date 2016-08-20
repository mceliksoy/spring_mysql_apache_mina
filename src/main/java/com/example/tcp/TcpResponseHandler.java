package com.example.tcp;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class TcpResponseHandler extends IoHandlerAdapter {

    public NioSocketConnector connector;

    public TcpResponseHandler() {

    }

    public TcpResponseHandler(NioSocketConnector connector) {
        this.connector = connector;
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        session.close(true);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        // your code.
        // ..

        session.close(false);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        if (connector != null) {
            connector.dispose();
        }
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {

    }

}
