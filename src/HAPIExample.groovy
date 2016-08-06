import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v25.message.ADT_A01;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.parser.Parser;

ADT_A01 adt = new ADT_A01();
adt.initQuickstart("ADT", "A01", "P");
MSH mshSegment = adt.getMSH();
mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
mshSegment.getSequenceNumber().setValue("5");
PID pid = adt.getPID();
pid.getPatientName(0).getFamilyName().getSurname().setValue("Doe");
pid.getPatientName(0).getGivenName().setValue("John");
pid.getPatientIdentifierList(0).getIDNumber().setValue("123456");

context = new DefaultHapiContext();
Parser parser = context.getPipeParser();
String encodedMessage = parser.encode(adt);
println("Printing ER7 Encoded Message:");
println(encodedMessage.normalize());
String message = encodedMessage.normalize().toString()
context = new DefaultHapiContext();
parser = context.getPipeParser();

adt = parser.parse(encodedMessage)

//message = parser.parse(encodedMessage)
//println(message)