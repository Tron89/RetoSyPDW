package Main;

import jakarta.xml.ws.Endpoint;

public class main {
	public static void main(String[] args) {
		Peticiones implementor = new Peticiones();
		String address = "http://localhost:9000/Peticiones";
		Endpoint.publish(address, implementor);
	}
}
