package com.example;

import com.example.tcp.TcpClientManager;

public class TcpClientTest {

    public static void main(String[] args) {
        TcpClientManager client = new TcpClientManager();
        client.sendMessage("NEW_USER");
    }
}
