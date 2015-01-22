package cz.skalicky.spring.modelandsessionattributeannotation.model;

public class CarModel {

    private CarTypeEnum carType;

    private CarBrandEnum carBrand;

    private String modelName;

    public CarModel() {
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

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

}
