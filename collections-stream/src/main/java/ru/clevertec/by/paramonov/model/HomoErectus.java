package ru.clevertec.by.paramonov.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class HomoErectus extends Person {
    public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public static final String recruitsDataFileName = "collections-stream\\src\\main\\resources\\recruits.json";
    private Car car;
    private Flower flower;
    private Animal animal;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public static List<HomoErectus> getPersons() throws IOException {
        return newMapper().readValue(new File(recruitsDataFileName), new TypeReference<>() {
        });
    }
    private static ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(df);
        mapper.setLocale(Locale.ENGLISH);
        mapper.registerModule(new JSR310Module());
        return mapper;
    }

    @Override
    public String toString() {
        return "HomoErectus{" +
               "car=" + car +
               ", flower=" + flower +
               ", animal=" + animal +
               "} as " + super.toString();
    }
}