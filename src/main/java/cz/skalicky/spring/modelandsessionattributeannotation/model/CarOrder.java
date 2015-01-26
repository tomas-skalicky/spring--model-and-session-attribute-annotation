package cz.skalicky.spring.modelandsessionattributeannotation.model;

public class CarOrder {

    private CarTypeEnum carType;

    private CarBrandEnum carBrand;

    private String carModel;

    private String customerFullName;

    private int totalPrice;

    public CarOrder() {
    }

    public CarTypeEnum getCarType() {
        return carType;
    }

    public void setCarType(CarTypeEnum carType) {
        this.carType = carType;
    }

    public CarBrandEnum getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrandEnum carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CarOrder [carType=" + carType + ", carBrand=" + carBrand + ", carModel=" + carModel
                + ", customerFullName=" + customerFullName + ", totalPrice=" + totalPrice + "]";
    }

}
