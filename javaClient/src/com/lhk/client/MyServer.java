package com.lhk.client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

	public static final int PORT = 8081;

	public static void main(String[] args) {
		System.out.println("服务器启动...\n");
		MyServer server = new MyServer();
		server.init();
	}

	public void init() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			while (true) {
				Socket client = serverSocket.accept();
				new HandlerThread(client);
			}
		} catch (IOException e) {
			System.out.println("服务器异常：" + e.getMessage());
		}
	}

	private class HandlerThread implements Runnable {
		private Socket socket;

		public HandlerThread(Socket client) {
			socket = client;
			new Thread(this).run();
		}

		public void run() {
			try {
				// 接收客户端的信息
				DataInputStream inputStream = new DataInputStream(socket.getInputStream());
				String clientInputStr = inputStream.readUTF();
				System.out.println("客户端发过来的内容：" + clientInputStr);
				// 向客户段回复信息
				DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
				System.out.println("请输入：\t");
				String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
				outputStream.writeUTF(str);
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
				System.out.println("服务器run异常：" + e.getMessage());
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						socket = null;
						System.out.println("服务的finally异常：" + e.getMessage());
					}
				}
			}
		}
	}
}
