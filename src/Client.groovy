import Utils

String serverIP = '127.0.0.1'
int serverPort = 8088
ArrayList<String> messages = ['Hola Mundo 1', 'Hola Mundo 2', 'Hola Mundo 3']

Socket socket = new Socket(serverIP, serverPort)

socket.withStreams { input, output ->
   for(String message in messages){
	println("Enviado: "+message)
        message = Utils.getStandardMessage(message)
        output << message
		def ms = Utils.getMessages(input)
    for(m in ms){
        println("Recibido: "+m)
    }
    }
    

	
}
socket.close()
