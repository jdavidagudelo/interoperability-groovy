import Utils
int messagesPerRequest = 3
int port = 8088
// Crea un socket servidor para escuchar conexiones de clientes
ServerSocket server = new ServerSocket(port)
String defaultResponseMessage = "Ok"
while(true){
    server.accept() { socket->
        // se ha conectado un cliente, se habla con él usando socket
        socket.withStreams { input, output ->
            def messages = Utils.getMessages(input, messagesPerRequest)
            ArrayList responseMessages = []
            println "El servidor ha recibido: "
            for (m in messages){
                responseMessages.add(defaultResponseMessage)
                println m
            }
            output << Utils.getCompleteMessage(responseMessages)
        }
    }
}
