# -*- coding: utf-8 -*-

from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
import urllib
import time

sessionList = []


class MyHandler(BaseHTTPRequestHandler):
    def do_POST(self):
        path = self.path
        print path
        # 获取post提交的数据
        data = self.rfile.read(int(self.headers['content-length']))
        data = urllib.unquote(data).decode("utf-8", 'ignore')

        print(data)
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()
        buf = time.time()
        self.wfile.write(buf)

    def do_GET(self):
        path = self.path
        print path
        # 拆分url(也可根据拆分的url获取Get提交才数据),可以将不同的path和参数加载不同的html页面，或调用不同的方法返回不同的数据，来实现简单的网站或接口
        query = urllib.splitquery(path)
        print query
        print query[1]
        if query[1] is not None:
            print "remove" in query[1]
            print query[1].split('&')
        sessionList.append(query)
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()
        buf = time.time()
        self.wfile.write(buf)


server = HTTPServer(('127.0.0.1', 8081), MyHandler)
print 'Started LTE-B server on port ', 8081
server.serve_forever()
