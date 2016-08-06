import Utils
import HAPIParser
import ca.uhn.hl7v2.model.v25.message.ADT_A01;
import ca.uhn.hl7v2.model.v25.segment.PID;

class ServerThread extends Thread{
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
            for (message in messages){
                message = message.normalize()
                message = message.replace("\n", "\r")
                ADT_A01 adt = HAPIParser.decodeMessage(message)
                PID pid = adt.getPID()
                println("Apellido: " + pid.getPatientName(0).getFamilyName().getSurname().getValue())
                println("Nombre: " + pid.getPatientName(0).getGivenName().getValue())
                println("Numero Identificacion: " + pid.getPatientIdentifierList(0).getIDNumber())
                String responseMessage = HAPIParser.getAckMessage(adt)
                responseMessages.add(responseMessage)
            }
            output << Utils.getCompleteMessage(responseMessages)
        }
    }
}
}
