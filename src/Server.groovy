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
        def messages = Utils.getMessages(input, messagesPerRequest)
        def responseMessages = []
        println "El servidor ha recibido: "
        for (m in messages){
            responseMessages.add(defaultResponseMessage)
            println m
        }
      output << Utils.getCompleteMessage(responseMessages)
}
}
}