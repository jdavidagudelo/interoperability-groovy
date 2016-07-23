def port = 8088
// Crea un socket servidor para escuchar conexiones de clientes
def server = new ServerSocket(port)
String defaultResponseMessage = "Ok"
int messagesPerRequest = 3
def CARRIAGE_RETURN = 13
def START_BLOCK = 11
def END_BLOCK = 28
String getMessage(String standardMessage){
    return standardMessage.substring(1, standardMessage.length() - 1)
}

String getCompleteMessage(messages){
    StringBuilder sb = new StringBuilder()
    for(String message in messages){
        message = getStandardMessage(message)
        sb.append(message)
    }
    return sb.toString()
}

String getStandardMessage(String message){
    def startBlock = String.valueOf((char)11)
    def endBlock = String.valueOf((char)28)
    def carriageBlock = String.valueOf((char)13)
    return startBlock + message + endBlock + carriageBlock;
}



while(true){
server.accept() {
socket->
// se ha conectado un cliente, se habla con él usando socket
socket.withStreams { input, output ->
        def reader = input.newReader()
        def current = reader.read()
        def previous = current
        int i = 0
        def messages = []
        def message = new StringBuilder()
        while(i < messagesPerRequest){
            current = reader.read()
            if(current == START_BLOCK){
                message = new StringBuilder()
            }
            else if(current == CARRIAGE_RETURN && previous == END_BLOCK){
                i++
                messages.add(message)
            }
            else if(current != END_BLOCK){
                message.append((char)current)
            }
            previous = current
        }
        def responseMessages = []
        println "El servidor ha recibido: "
        for (m in messages){
            responseMessages.add(defaultResponseMessage)
            println m
        }
      output << getCompleteMessage(responseMessages)
}
}
}