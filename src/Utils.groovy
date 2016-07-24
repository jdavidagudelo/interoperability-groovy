class Utils{
    static final int CARRIAGE_RETURN = 13
    static final int START_BLOCK = 11
    static final int END_BLOCK = 28

    static String getStandardMessage(String message){
        def startBlock = String.valueOf((char)START_BLOCK)
        def endBlock = String.valueOf((char)END_BLOCK)
        def carriageBlock = String.valueOf((char)CARRIAGE_RETURN)
        return startBlock + message + endBlock + carriageBlock;
    }

    static String getCompleteMessage(messages){
        StringBuilder sb = new StringBuilder()
        for(String message in messages){
            message = getStandardMessage(message)
            sb.append(message)
        }
        return sb.toString()
    }

    static ArrayList getMessages(input, messagesPerRequest){
        def reader = input.newReader()
        def current = reader.read()
        def previous = current
        int i = 0
        ArrayList messages = []
        StringBuilder message = new StringBuilder()
        while(i < messagesPerRequest && current != -1){
            current = reader.read()
            if(current == START_BLOCK){
                message = new StringBuilder()
            }
            else if(current == CARRIAGE_RETURN && previous == END_BLOCK){
                i++
                messages.add(message)
            }
            else if(current != END_BLOCK){
                message.append((char)current)
            }
            previous = current
        }
        return messages
    }
}