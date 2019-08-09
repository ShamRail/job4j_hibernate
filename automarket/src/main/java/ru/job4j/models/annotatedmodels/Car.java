package ru.job4j.models.annotatedmodels;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mark;

    private String model;

    private String engineType;

    private String engineVolume;

    private boolean used;

    private long manufactureYear;

    private String transmission;

    private String body;

    @OneToOne(mappedBy = "car", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Advertisement advertisement;

    public Car() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(String engineVolume) {
        this.engineVolume = engineVolume;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public long getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(long manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                used == car.used &&
                Objects.equals(mark, car.mark) &&
                Objects.equals(model, car.model) &&
                Objects.equals(engineType, car.engineType) &&
                Objects.equals(engineVolume, car.engineVolume) &&
                Objects.equals(manufactureYear, car.manufactureYear) &&
                Objects.equals(transmission, car.transmission) &&
                Objects.equals(body, car.body) &&
                Objects.equals(advertisement, car.advertisement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, model, engineType, engineVolume, used, manufactureYear, transmission, body, advertisement);
    }
}
