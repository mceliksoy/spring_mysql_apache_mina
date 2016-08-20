package com.example.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mehmet CELIKSOY
 *
 */
@Component
public class TcpServerManager {

    /** The listening port (check that it's not already in use) */
    public static final int PORT = 7001;

    @Autowired
    private NioSocketAcceptor nioSocketAcceptor;

    @Autowired
    private TcpRequestHandler requestHandler;


    @PostConstruct
    public void bind() {
        NioSocketAcceptor nioSocketAcceptor = new NioSocketAcceptor();
        nioSocketAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));

        int coreThreadCount = 10;
        int maxThreadCount = 20;
        int threadTimeoutSeconds = 120;
        ExecutorFilter executorFilter = new ExecutorFilter(coreThreadCount, maxThreadCount, threadTimeoutSeconds, TimeUnit.SECONDS);
        nioSocketAcceptor.getFilterChain().addLast("executorFilter", executorFilter);

        nioSocketAcceptor.setReuseAddress(true);

        nioSocketAcceptor.setHandler(requestHandler);
        try {
            nioSocketAcceptor.bind(new InetSocketAddress(PORT));
            System.out.println("App is ready on port : " + PORT + ", if you are !");
        } catch (IOException e) {
            System.out.println("App can not bind to port : " + PORT);
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void unbind() {
        nioSocketAcceptor.unbind(new InetSocketAddress(PORT));
        System.out.println("App give the port : " + PORT + " free. Goodbye");
    }

}
