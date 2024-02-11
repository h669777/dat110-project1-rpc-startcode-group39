package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;
import static java.lang.System.arraycopy;


public class MessageUtils {

	public static final int SEGMENTSIZE = 128;
	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		byte[] segment = new byte[SEGMENTSIZE];
		byte[] data = message.getData();

		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messaging layer

		segment[0] = (byte) data.length;
		arraycopy(data,0,segment,1,data.length);

		return segment;
	}

	public static Message decapsulate(byte[] segment) {

		// decapsulate segment and put received payload data into a message
		
		int length = segment[0];
		byte[] data = Arrays.copyOfRange(segment,1,length+1);

		return new Message(data);
	}
}
