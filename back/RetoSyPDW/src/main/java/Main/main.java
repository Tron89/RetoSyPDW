package Main;

import jakarta.xml.ws.Endpoint;

public class main {
	public static void main(String[] args) {
		Saludar implementor = new Saludar();
		String address = "http://localhost:9000/ServicioSaludar";
		Endpoint.publish(address, implementor);
	}
}
