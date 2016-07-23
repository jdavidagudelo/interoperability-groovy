import Utils
def messagesPerRequest = 3
def port = 8088
// Crea un socket servidor para escuchar conexiones de clientes
def server = new ServerSocket(port)
String defaultResponseMessage = "Ok"

String getMessage(String standardMessage){
    return standardMessage.substring(1, standardMessage.length() - 1)
}
while(true){
server.accept() {
socket->
// se ha conectado un cliente, se habla con él usando socket
socket.withStreams { input, output ->
        def reader = input.newReader()
        def startLine = reader.readLine()
        def method = startLine.split(" ")[0]
        if(!method.equals('GET')){
            return 'HTTP/1.1 405 Method Not Allowed\r\n'
        }
        def html = "<!DOCTYPE html><html><body><h1>My First Heading</h1><p>My first paragraph.</p></body></html>"
        output << "HTTP/1.1 200 OK\r\n"+'Date: '+(new Date()).toString() +'\r\n'+
        'Content-Type: text/html\r\n' + 'Content-Length:' + html.length() + '\r\n\r\n'+ html
}
}
}