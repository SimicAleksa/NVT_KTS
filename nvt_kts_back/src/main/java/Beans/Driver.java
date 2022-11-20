package Beans;

import Enumerations.CarType;

public class Driver {
    private Boolean active;
    private CarType carType;
    private Boolean babyAllowed;
    private Boolean petAllowed;
    private Boolean isDriverFree;

    public Driver() {
    }

    public Driver(Boolean active, CarType carType, Boolean babyAllowed, Boolean petAllowed, Boolean isDriverFree) {
        this.active = active;
        this.carType = carType;
        this.babyAllowed = babyAllowed;
        this.petAllowed = petAllowed;
        this.isDriverFree = isDriverFree;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Boolean getBabyAllowed() {
        return babyAllowed;
    }

    public void setBabyAllowed(Boolean babyAllowed) {
        this.babyAllowed = babyAllowed;
    }

    public Boolean getPetAllowed() {
        return petAllowed;
    }

    public void setPetAllowed(Boolean petAllowed) {
        this.petAllowed = petAllowed;
    }

    public Boolean getDriverFree() {
        return isDriverFree;
    }

    public void setDriverFree(Boolean driverFree) {
        isDriverFree = driverFree;
    }
}

