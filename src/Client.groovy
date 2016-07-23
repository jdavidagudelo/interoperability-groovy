def serverIP = '127.0.0.1'
def serverPort = 8088

def CARRIAGE_RETURN = 13
def START_BLOCK = 11
def END_BLOCK = 28
def messages = ['Hola Mundo 1', 'Hola Mundo 2', 'Hola Mundo 3']
// Se conecta al servidor desde un socket cliente
def socket = new Socket(serverIP, serverPort)

String getStandardMessage(String message){
    def startBlock = String.valueOf((char)11)
    def endBlock = String.valueOf((char)28)
    def carriageBlock = String.valueOf((char)13)
    return startBlock + message + endBlock + carriageBlock;
}

String getCompleteMessage(messages){
    StringBuilder sb = new StringBuilder()
    for(String message in messages){
        message = getStandardMessage(message)
        sb.append(message)
    }
    return sb.toString()
}

String getMessage(String standardMessage){
    return standardMessage.substring(1, standardMessage.length() - 1)
}


def printMLLPMessages(input){
    def reader = input.newReader()
    def line = reader.readLine()
    def messages = []
    messages.add(getMessage(line))
    println "El cliente ha recibido: "
    for(message in messages){
        println message
    }
}

// Para leer o escribir en el socket
socket.withStreams { input, output ->
   // Cuidado, la lectura bloquea el proceso actual
   output << getCompleteMessage(messages)
   def reader = input.newReader()
   def current = reader.read()
   def previous = current
   int i = 0
   def ms = []
   def message = new StringBuilder()
   while(i < messages.size()){
        current = reader.read()
            if(current == START_BLOCK){
                message = new StringBuilder()
            }
            else if(current == CARRIAGE_RETURN && previous == END_BLOCK){
                i++
                ms.add(message)
            }
            else if(current != END_BLOCK){
                message.append((char)current)
            }
            previous = current
        }
    println "El cliente ha recibido: "
    for(m in ms){
        println m
    }
}
socket.close()