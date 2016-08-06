import Utils
import HAPIParser
import ca.uhn.hl7v2.model.v25.message.ACK
import ca.uhn.hl7v2.model.v25.segment.MSA

String serverIP = '127.0.0.1'
int serverPort = 8088

Socket socket = new Socket(serverIP, serverPort)

socket.withStreams { input, output ->
    def message = HAPIParser.createMessage()
    println("Enviado: " )
    println(message)
    message = message.replace("\n", "\r")
    message = Utils.getStandardMessage(message)
    output << message
    def ms = Utils.getMessages(input)
    for(m in ms){
        m = m.normalize()
        m = m.replace("\n", "\r")
        ACK ack = HAPIParser.decodeMessage(m)
        MSA msa = ack.getMSA()
        println("Recibido: " + msa.getTextMessage().getValue())
    }
}
socket.close()

