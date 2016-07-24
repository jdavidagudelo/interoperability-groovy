import Utils

String serverIP = '127.0.0.1'
int serverPort = 8088
ArrayList<String> messages = ['Hola Mundo 1', 'Hola Mundo 2', 'Hola Mundo 3']

Socket socket = new Socket(serverIP, serverPort)

socket.withStreams { input, output ->
    output << Utils.getCompleteMessage(messages)
    def ms = Utils.getMessages(input, messages.size())
    println "El cliente ha recibido: "
    for(m in ms){
        println m
    }
}
socket.close()