import Utils

def serverIP = '127.0.0.1'
def serverPort = 8088
def messages = ['Hola Mundo 1', 'Hola Mundo 2', 'Hola Mundo 3']

def socket = new Socket(serverIP, serverPort)

// Para leer o escribir en el socket
socket.withStreams { input, output ->
   output << Utils.getCompleteMessage(messages)
   def ms = Utils.getMessages(input, messages.size())
    println "El cliente ha recibido: "
    for(m in ms){
        println m
    }
}
socket.close()