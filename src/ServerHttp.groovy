int port = 8088
ServerSocket server = new ServerSocket(port)
while(true){
    server.accept() { socket->
        socket.withStreams { input, output ->
            def reader = input.newReader()
            def startLine = reader.readLine()
            String method = ((String[])startLine.split(" "))[0]
            if(!method.equals('GET')){
                return 'HTTP/1.1 405 Method Not Allowed\r\n'
            }
            String html = "<!DOCTYPE html><html><body><h1>My First Heading</h1><p>My first paragraph.</p></body></html>"
            output << "HTTP/1.1 200 OK\r\n"+'Date: '+(new Date()).toString() +'\r\n'+
                    'Content-Type: text/html\r\n' + 'Content-Length:' + html.length() + '\r\n\r\n'+ html
        }
    }
}