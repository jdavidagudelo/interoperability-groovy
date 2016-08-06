import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v25.message.ADT_A01;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.model.v25.message.ACK
import ca.uhn.hl7v2.model.v25.segment.MSA
import ca.uhn.hl7v2.model.Message

class HAPIParser{

    /**
     * Crea un mensaje en ADT_A01 para ser enviado por un cliente.
     * @return String con un mensaje ADT_A01.
     */
    static String createMessage(){
        ADT_A01 adt = new ADT_A01()
        adt.initQuickstart("ADT", "A01", "P")
        MSH mshSegment = adt.getMSH()
        mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem")
        mshSegment.getSequenceNumber().setValue("5")
        PID pid = adt.getPID()
        pid.getPatientName(0).getFamilyName().getSurname().setValue("Salas")
        pid.getPatientName(0).getGivenName().setValue("Martin")
        pid.getPatientIdentifierList(0).getIDNumber().setValue("123456")
        HapiContext context = new DefaultHapiContext()
        Parser parser = context.getPipeParser()
        String encodedMessage = parser.encode(adt)
        return encodedMessage.normalize()
    }

    /**
     * Retorna el objecto asociado al mensaje String especificado.
     * @param encodedMessage
     * @return
     */
    static Message decodeMessage(String encodedMessage){
        HapiContext context = new DefaultHapiContext()
        Parser parser = context.getPipeParser()
        Message message = parser.parse(encodedMessage)
        return message
    }

    static String getAckMessage(Message message){
        ACK ack = message.generateACK()
        MSA msa = ack.getMSA()
        msa.getTextMessage().setValue("Mensaje Procesado Exitosamente.")
        HapiContext context = new DefaultHapiContext()
        Parser parser = context.getPipeParser()
        String encodedMessage = parser.encode(ack)
        return encodedMessage.normalize()
    }
}