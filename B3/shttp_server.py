from http.server import HTTPServer, SimpleHTTPRequestHandler
import datetime
import time

last_access_time = None

class TimeServer(SimpleHTTPRequestHandler):
    def do_GET(self):
        global last_access_time
        current_time = time.time()
        current_datetime = datetime.datetime.now()
        
        response = "<!DOCTYPE html><html><body>"
        response += f"<h2>Hallo! Es ist der {current_datetime.strftime('%d.%m.%Y, %H:%M')} Uhr.</h2>"
        
        if last_access_time is None:
            response += "<p>Sie sind zum ersten Mal auf diesem Server gelandet.</p>"
        else:
            last_datetime = datetime.datetime.fromtimestamp(last_access_time)
            seconds_passed = int(current_time - last_access_time)
            response += f"<p>Der letzte Zugriff erfolgte am {last_datetime.strftime('%d.%m.%Y, %H:%M')} Uhr.</p>"
            response += f"<p>Es sind {seconds_passed} Sekunden seit dem letzten Zugriff verstrichen.</p>"
        
        response += "</body></html>"
        
        last_access_time = current_time
        
        self.send_response(200)
        self.send_header('Content-type', 'text/html')
        self.end_headers()
        self.wfile.write(response.encode())

server = HTTPServer(('localhost', 6789), TimeServer)
print('Server running on port 6789...')
server.serve_forever()
