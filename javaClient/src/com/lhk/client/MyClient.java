package com.lhk.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {
    public static final String IP_ADDRESS = "127.0.0.1";
    public static final int PORT = 8081;

    public static void main(String[] args) {
        System.out.println("客户端启动...");
        System.out.println("当接收到服务器端字符为\"OK\"的时候，客户端将终止\n");
        while (true) {
            Socket socket = null;
            try {
                socket = new Socket(IP_ADDRESS, PORT);
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
//				ObjectOutputStream outputStream2 = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("请输入：\t");
//				Gather gather = new Gather();
//				gather.getFile();
//				outputStream2.writeObject(new Gather().getList());
                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
                os.write(str.getBytes());
                int len = -1;
                byte[] bs = new byte[1024];
                StringBuffer sb = new StringBuffer();
                while ((len = is.read(bs)) != -1) {
                    sb.append(bs);
                }
                System.out.println("服务器端返回过来的是：" + sb.toString());
                if ("OK".equals(sb.toString())) {
                    System.out.println("客户端即将关闭连接");
                    Thread.sleep(500);
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        socket = null;
                        System.out.println("客户端finally异常：" + e.getMessage());
                    }
                }
            }
        }
    }
}
