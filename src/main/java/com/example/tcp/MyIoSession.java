package com.example.tcp;

import java.io.Serializable;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MyIoSession implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private IoSession session;
	private NioSocketConnector connector;
	
	public MyIoSession(IoSession session, NioSocketConnector connector) {
		this.session = session;
		this.connector = connector;
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public NioSocketConnector getConnector() {
		return connector;
	}

	public void setConnector(NioSocketConnector connector) {
		this.connector = connector;
	}
	
}
