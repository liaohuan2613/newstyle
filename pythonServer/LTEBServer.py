from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer


class MyHandler(BaseHTTPRequestHandler):

    def do_POST(self):
        import time
        time.sleep(1)
        self.send_response(200)

    def do_GET(self):
        print self + "11"
        import time
        time.sleep(1)
        self.send_response(200)


server = HTTPServer(('127.0.0.1', 8081), MyHandler)
print 'Started LTE-B server on port ', 8081
server.serve_forever()
