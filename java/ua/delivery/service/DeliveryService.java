package ua.delivery.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ua.delivery.model.ищ.ClientType;
import ua.delivery.model.ищ.CourierType;
import ua.delivery.model.ищ.DeliveryType;

import java.util.List;

@WebService(serviceName = "DeliveryService", name = "Delivery", targetNamespace = Links.ENTITY)
public interface DeliveryService {

    @WebMethod(operationName = "createDelivery", action = "create")
    @RequestWrapper(className = "service.deliveryRequest", localName = "CreateReq", targetNamespace = Links.ENTITY)
    @ResponseWrapper(className = "service.deliveryResponse", localName = "CreateResp", targetNamespace = Links.ENTITY)
    DeliveryType createDelivery(@WebParam(name = "delivery") DeliveryType delivery);

    @WebMethod(operationName = "getClientDeliveries", action = "get")
    @RequestWrapper(className = "service.deliveriesRequest", localName = "DeliveriesReq", targetNamespace = Links.ENTITY)
    @ResponseWrapper(className = "service.deliveriesResponse", localName = "DeliveriesResp", targetNamespace = Links.ENTITY)
    List<DeliveryType> getDeliveries(@WebParam(name = "client") ClientType client);

    @WebMethod(operationName = "assignCourier", action = "get")
    @RequestWrapper(className = "service.assignCourierRequest", localName = "AssignCourierReq", targetNamespace = Links.ENTITY)
    @ResponseWrapper(className = "service.assignCourierResponse", localName = "AssignCourierResp", targetNamespace = Links.ENTITY)
    DeliveryType assignCouriersToDelivery(@WebParam(name = "courier") CourierType courier, String deliveryId);

    @WebMethod(operationName = "updateDelivery", action = "update")
    @RequestWrapper(className = "updateDeliveryRequest", localName = "UpdateDeliveryReq", targetNamespace = Links.ENTITY)
    @ResponseWrapper(className = "updateDeliveryResponse", localName = "UpdateDeliveryResp", targetNamespace = Links.ENTITY)
    DeliveryType updateDelivery(@WebParam(name = "updatedDelivery") DeliveryType delivery);

    @WebMethod(operationName = "cancelDelivery", action = "update")
    @RequestWrapper(className = "cancelDeliveryRequest", localName = "CancelDeliveryReq", targetNamespace = Links.ENTITY)
    @ResponseWrapper(className = "cancelDeliveryResponse", localName = "CancelDeliveryResp", targetNamespace = Links.ENTITY)
    void cancelDelivery(@WebParam(name = "delivery") DeliveryType delivery);

}
