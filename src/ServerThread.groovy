import Utils
class ServerThread extends Thread{
int messagesPerRequest = 1
    Socket socket = null
	ServerThread(Socket socket){
        this.socket = socket
    }
    void run(){
		socket.withStreams { input, output ->
		while(true){
            def messages = Utils.getMessages(input)
			if(messages.size() == 0){
				break
			}
            ArrayList responseMessages = []
            for (m in messages){
                responseMessages.add(Utils.defaultResponseMessage)
                println('Recibido: '+m)
            }
            output << Utils.getCompleteMessage(responseMessages)
        }
    }
}
}
