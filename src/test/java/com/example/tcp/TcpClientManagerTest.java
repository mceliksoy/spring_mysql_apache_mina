package com.example.tcp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TcpClientManagerTest {

    @Test
    public void sendMessage() {
        TcpClientManager client = new TcpClientManager();
        client.sendMessage("Hello");
    }
}
