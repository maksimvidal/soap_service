package ua.delivery;

import jakarta.xml.ws.Endpoint;
import ua.delivery.service.DeliveryServiceImpl;
import ua.delivery.service.Links;

import java.util.Scanner;
import java.util.logging.Logger;

public class Start {
    private static final Logger LOG = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) {
        Endpoint ep = Endpoint.publish(Links.ADDRESS, DeliveryServiceImpl.getInstance());
        LOG.info("Server ready at " + Links.ADDRESS + "?wsdl ...\n");

        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();
        LOG.info("Server exiting");
        ep.stop();
    }
}
