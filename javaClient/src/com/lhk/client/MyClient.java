package com.lhk.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {
	public static final String IP_ADDR = "127.0.0.1";
	public static final int PORT = 8081;
	public static void main(String[] args) {
		System.out.println("客户端启动...");
		System.out.println("当接收到服务器端字符为\"OK\"的时候，客户端将终止\n");
		while (true) {
			Socket socket = null;
			try {
				socket = new Socket(IP_ADDR, PORT);
				DataInputStream inputStream = new DataInputStream(socket.getInputStream());
				DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
//				ObjectOutputStream outputStream2 = new ObjectOutputStream(socket.getOutputStream());
				System.out.println("请输入：\t");
//				Gather gather = new Gather();
//				gather.getFile();
//				outputStream2.writeObject(new Gather().getList());
				String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
				outputStream.writeUTF(str);
				String ret = inputStream.readUTF();
				System.out.println("服务器端返回过来的是：" + ret);
				if ("OK".equals(ret)) {
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
