package ua.delivery.service;

import jakarta.jws.WebService;
import ua.delivery.Database;
import ua.delivery.model.ищ.ClientType;
import ua.delivery.model.ищ.CourierType;
import ua.delivery.model.ищ.DeliveryType;

import java.util.*;
import java.util.logging.Logger;

@WebService(endpointInterface = "ua.delivery.service.DeliveryService", serviceName = "DeliveryService", name = "Delivery", targetNamespace = Links.ENTITY)
public final class DeliveryServiceImpl implements DeliveryService {
    private static DeliveryService serviceDAO;

    private static Database database = new Database();

    private Logger log;

    private DeliveryServiceImpl() {
        log = Logger.getLogger(DeliveryServiceImpl.class.getName());
    }

    public static DeliveryService getInstance() {
        if (serviceDAO == null) {
            serviceDAO = new DeliveryServiceImpl();
        }
        return serviceDAO;
    }

    @Override
    public DeliveryType createDelivery(DeliveryType delivery) {
        database.getDeliveries().add(delivery);
        return delivery;
    }

    @Override
    public List<DeliveryType> getDeliveries(ClientType client) {
        return new ArrayList<>(database.getDeliveries());
    }

    @Override
    public DeliveryType assignCouriersToDelivery(CourierType courier, String deliveryId) {
        return database.getDeliveries().stream()
                .filter(delivery -> delivery.getDeliveryId().equals(deliveryId))
                .peek(delivery -> delivery.setCourier(courier))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public DeliveryType updateDelivery(DeliveryType delivery) {
        List<DeliveryType> deliveryTypes = database.getDeliveries();
        deliveryTypes.removeIf(oldDelivery -> oldDelivery.getDeliveryId().equals(delivery.getDeliveryId()));

        return createDelivery(delivery);
    }

    @Override
    public void cancelDelivery(DeliveryType delivery) {
        database.getDeliveries().removeIf(deliveryType -> delivery.getDeliveryId().equals(deliveryType.getDeliveryId()));
    }
}
