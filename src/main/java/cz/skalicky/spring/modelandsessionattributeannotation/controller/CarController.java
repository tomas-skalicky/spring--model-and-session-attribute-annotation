package cz.skalicky.spring.modelandsessionattributeannotation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import cz.skalicky.spring.modelandsessionattributeannotation.model.CarOrder;
import cz.skalicky.spring.modelandsessionattributeannotation.model.CarTypeEnum;

@RestController
@RequestMapping("")
@SessionAttributes("order")
public class CarController {

    static final String GET_CAR_ORDER_URI = "/getCarOrder";
    static final String UPDATE_CAR_ORDER_URI = "/updateCarOrder";

    @ModelAttribute("order")
    public CarOrder populateOrder(final HttpSession session) {
        return new CarOrder();
    }

    @RequestMapping(value = GET_CAR_ORDER_URI, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CarOrder computePrice(@ModelAttribute("order") CarOrder carOrder) {

        if ((carOrder.getCarType() == CarTypeEnum.COMBI) && ("Octavia".equals(carOrder.getCarModel()))) {
            carOrder.setTotalPrice(16_000);
        } else {
            carOrder.setTotalPrice(0);
        }
        return carOrder;
    }

    @RequestMapping(value = UPDATE_CAR_ORDER_URI, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@ModelAttribute("order") CarOrder carOrder) {

        // update done by assigning of body to carOrder
        System.out.println("carOrder: " + carOrder);
        return "success";
    }

}
