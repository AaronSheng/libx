package net;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by aaron on 5/26/17.
 */
public class SocketClient {
    private Socket socket = null;
    private String ip;
    private int port;

    private InputStream in = null;
    private OutputStream out = null;

    public SocketClient() {
    }

    public SocketClient(String ip, int port) throws SocketException {
        connect(ip, port);
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public InputStream getInputStream() throws SocketException {
        if (socket == null || socket.isClosed()) {
            throw new SocketException("Socket is not connected");
        }

        try {
            return socket.getInputStream();
        } catch (Exception e) {
            throw new SocketException(e);
        }
    }

    public OutputStream getOutputStream() throws SocketException {
        if (socket == null || socket.isClosed()) {
            throw new SocketException("Socket is not connected");
        }

        try {
            return socket.getOutputStream();
        } catch (Exception e) {
            throw new SocketException(e);
        }
    }

    public void connect(String ip, int port) throws SocketException {
        this.ip = ip;
        this.port = port;

        doClose();
        doConnect();
    }

    public void close() throws SocketException {
        doClose();
    }

    private void doConnect() throws SocketException {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), 30000);
            socket.setSoTimeout(30000);
            socket.setKeepAlive(true);
            socket.setTcpNoDelay(true);

            in = new BufferedInputStream(socket.getInputStream());
            out = socket.getOutputStream();

            System.out.printf("connect to %s:%d succeed!\n", ip, port);
        } catch (Exception e) {
            throw new SocketException(e);
        }
    }

    private void doClose() throws SocketException {
        try {
            if (in != null) {
                in.close();
                in = null;
            }

            if (out != null) {
                out.close();
                out = null;
            }

            if (socket != null) {
                socket.close();
                socket = null;
            }
        } catch (Exception e) {
            throw new SocketException(e);
        }
    }
}
