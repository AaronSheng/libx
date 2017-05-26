package net;

import org.junit.Assert;
import org.junit.Test;
import string.ByteUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SocketClientTest {
    @Test
    public void testContruction() {
        try {
            SocketClient socketClient = new SocketClient("127.0.0.1", 9000);
            Assert.assertTrue(true);
            System.out.println("SocketClientTest testContruction server existed pass!");
        } catch (SocketException e) {
            Assert.assertFalse(true);
        }

        try {
            SocketClient socketClient = new SocketClient("127.0.0.1", 8000);
            Assert.assertFalse(true);
        } catch (SocketException e) {
            Assert.assertTrue(true);
            System.out.println("SocketClientTest testContruction server not existed pass!");
        }
    }

    @Test
    public void testConnect() {
        try {
            SocketClient socketClient = new SocketClient();
            socketClient.connect("127.0.0.1", 9000);
            Assert.assertTrue(true);
            System.out.println("SocketClientTest testConnect server existed pass!");
        } catch (SocketException e) {
            Assert.assertFalse(true);
        }

        try {
            SocketClient socketClient = new SocketClient();
            socketClient.connect("127.0.0.1", 8000);
            Assert.assertFalse(true);
        } catch (SocketException e) {
            Assert.assertTrue(true);
            System.out.println("SocketClientTest testConnect server not existed pass!");
        }
    }

    @Test
    public void testClose() {
        try {
            SocketClient socketClient = new SocketClient();
            socketClient.connect("127.0.0.1", 9000);
            socketClient.close();
            Assert.assertTrue(true);
            System.out.println("SocketClientTest testClose server existed pass!");
        } catch (SocketException e) {
            Assert.assertFalse(true);
        }

        try {
            SocketClient socketClient = new SocketClient();
            socketClient.close();
            Assert.assertTrue(true);
        } catch (SocketException e) {
            Assert.assertFalse(true);
            System.out.println("SocketClientTest testClose server not existed pass!");
        }
    }

    @Test
    public void testGetInputStream() {
        try {
            SocketClient socketClient = new SocketClient("127.0.0.1", 9000);
            OutputStream outputStream = socketClient.getOutputStream();
            outputStream.write(ByteUtils.int2FourBytes(5));
            outputStream.write("hello".getBytes());

            BufferedInputStream inputStream = new BufferedInputStream(socketClient.getInputStream());
            inputStream.mark(0);
            byte[] header = new byte[4];
            int len = inputStream.read(header);
            if (len == 4) {
                int bodyLen = ByteUtils.fourBytes2Int(header);
                byte[] body = new byte[bodyLen];
            }

            Assert.assertTrue(true);
            System.out.println("SocketClientTest testGetInputStream server existed pass!");
        } catch (SocketException e) {
            Assert.assertFalse(true);
        } catch (IOException e) {
            Assert.assertFalse(true);
        }
    }

    @Test
    public void testGetOutputStream() {
        try {
            SocketClient socketClient = new SocketClient("127.0.0.1", 9000);
            OutputStream outputStream = socketClient.getOutputStream();
            outputStream.write(ByteUtils.int2FourBytes(5));
            outputStream.write("hello".getBytes());

            Assert.assertTrue(true);
            System.out.println("SocketClientTest testGetOutputStream server existed pass!");
        } catch (SocketException e) {
            Assert.assertFalse(true);
        } catch (IOException e) {
            Assert.assertFalse(true);
        }
    }
}
