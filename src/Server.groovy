import Utils
import ServerThread
int port = 8088
// Crea un socket servidor para escuchar conexiones de clientes
ServerSocket server = new ServerSocket(port)


while(true){
	server.accept{socket ->
		def thread = new ServerThread(socket)
		thread.start()
   		thread.join()
	}
}
