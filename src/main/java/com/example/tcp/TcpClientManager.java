package com.example.tcp;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TcpClientManager {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MyIoSession createNewSession() {
        NioSocketConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        TcpResponseHandler handler = new TcpResponseHandler(connector);
        connector.setHandler(handler);

        String ipAddress = "127.0.0.1";
        int port = 7001; // Port to connect
        int connectTimeout = 5000;

        connector.setConnectTimeoutMillis(connectTimeout);
        ConnectFuture connectFuture = connector.connect(new InetSocketAddress(ipAddress, port));
        IoSession session = null;
        try {
            boolean isConnected = connectFuture.await(connectTimeout);
            if (isConnected) {
                session = connectFuture.getSession();
            }

        } catch (InterruptedException e) { // Burada ne yapal�m
            e.printStackTrace();
            connector.dispose();
        }

        if (session == null) {
            // TODO Exception
        }

        return new MyIoSession(session, connector);

    }

    public void sendMessage(String msg) {
        MyIoSession mySession = createNewSession();
        NioSocketConnector connector = mySession.getConnector();
        IoSession session = mySession.getSession();
        try {
            WriteFuture write = session.write(msg);

            write.awaitUninterruptibly().addListener(future -> {
                logger.info("[CLIENT_MANAGER][MESSAGE_SENT]");

                // Eğer aşağıdan bir cevap gelmeyecek ise, bağlantı kapatılmalı.
                boolean notWaitForAnswer = false;
                if (notWaitForAnswer) {
                    CloseFuture closeFuture = future.getSession().close(false);
                    if (connector != null) {
                        closeFuture.awaitUninterruptibly();
                        connector.dispose();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
