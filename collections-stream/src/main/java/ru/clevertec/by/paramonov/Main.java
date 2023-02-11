package ru.clevertec.by.paramonov;

import com.fasterxml.jackson.core.JsonToken;
import ru.clevertec.by.paramonov.model.*;
import ru.clevertec.by.paramonov.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
//        task6();
//        task7();
//        task8();
//        task9();
//        task10();
//        task11();
//        task12();
//        task13();
        task14();
        task15();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(x -> ((x.getAge() >= 10) && (x.getAge() < 20)))
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(14)
                .limit(7)
                .forEach(System.out::println);
    }


    //TODO without 'if'
    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(x -> x.getOrigin().equalsIgnoreCase("japanese"))
                .map(x -> x.getGender().equalsIgnoreCase("female")
                        ? x.getBread().toUpperCase()
                        : x.getBread())
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(x -> x.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(x -> x.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long femaleCount = animals.stream()
                .filter(x -> x.getGender().equalsIgnoreCase("female"))
                .count();
        System.out.println(femaleCount);

    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isAnyHungarian = animals.stream()
                .filter(x -> ((x.getAge() >= 20) && (x.getAge() <= 30)))
                .anyMatch(x -> x.getOrigin().equalsIgnoreCase("hungarian"));
        System.out.println(isAnyHungarian);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isOnlyMaleAndFemale = animals.stream()
                .allMatch(x -> (x.getGender().equalsIgnoreCase("male")
                                || x.getGender().equalsIgnoreCase("female")));
        System.out.println(isOnlyMaleAndFemale);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isNoOneFromOceania = animals.stream()
                .noneMatch(x -> x.getOrigin().equalsIgnoreCase("Oceania"));
        System.out.println(isNoOneFromOceania);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        OptionalInt max = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .mapToInt(Animal::getAge)
                .max();
        max.ifPresent(System.out::println);
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        OptionalInt min = animals.stream()
                .mapToInt(x -> x.getBread().toCharArray().length)
                .min();
        min.ifPresent(System.out::println);
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int sumAge = animals.stream()
                .mapToInt(Animal::getAge)
                .reduce(0, Integer::sum);
        System.out.println(sumAge);
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        OptionalDouble indonesianAverageAge = animals.stream()
                .filter(x -> x.getOrigin().equalsIgnoreCase("Indonesian"))
                .mapToInt(Animal::getAge)
                .average();
        indonesianAverageAge.ifPresent(System.out::println);
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
                .filter(x -> x.getGender().equalsIgnoreCase("male"))
                .filter(x -> ((ChronoUnit.YEARS.between(x.getDateOfBirth(), LocalDate.now())) >= 18)
                             && ((ChronoUnit.YEARS.between(x.getDateOfBirth(), LocalDate.now())) < 27))
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        List<Person> people = Stream.concat(
                houses.stream()
                        .filter(x -> x.getBuildingType().equalsIgnoreCase("hospital"))
                        .flatMap(x -> x.getPersonList().stream()),
                Stream.concat(houses.stream()
                                .filter(h -> !h.getBuildingType().equalsIgnoreCase("hospital"))
                                .flatMap(h -> h.getPersonList().stream()
                                        .filter(p -> ((ChronoUnit.YEARS.between(p.getDateOfBirth(), LocalDate.now()) <= 18)
                                                      || (ChronoUnit.YEARS.between(p.getDateOfBirth(), LocalDate.now()) > 59)))),
                        houses.stream()
                                .filter(h -> !h.getBuildingType().equalsIgnoreCase("hospital")).
                                flatMap(h -> h.getPersonList().stream()
                                        .filter(p -> (ChronoUnit.YEARS.between(p.getDateOfBirth(), LocalDate.now()) > 18
                                                      || (ChronoUnit.YEARS.between(p.getDateOfBirth(), LocalDate.now()) > 59))))
                )
        ).limit(500).toList();
        people.forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        List<Car> carListForTurkmen = Stream.concat(cars.stream().filter(a -> a.getColor().equalsIgnoreCase("WHITE")),
                cars.stream().filter(a -> a.getCarMake().equalsIgnoreCase("JAGUAR"))
        ).distinct().toList();
        cars.removeAll(carListForTurkmen);
        List<Car> carListForUzbek = Stream.concat(cars.stream().filter(x -> (x.getMass() > 1500)),
                cars.stream().filter(x -> x.getCarMake().equalsIgnoreCase("BMW")
                                          || x.getCarMake().equalsIgnoreCase("LEXUS")
                                          || x.getCarMake().equalsIgnoreCase("CHRYSLER")
                                          || x.getCarMake().equalsIgnoreCase("TOYOTA"))).distinct().toList();
        cars.removeAll(carListForUzbek);
        List<Car> carListForKazakh = Stream.concat(cars.stream().filter(x -> (x.getColor().equalsIgnoreCase("black")
                                                                              && x.getMass() > 4000)),
                cars.stream().filter(x -> (x.getCarMake().equalsIgnoreCase("GMC")
                                           || x.getCarMake().equalsIgnoreCase("DODGE")))).distinct().toList();
        cars.removeAll(carListForKazakh);
        List<Car> carListForKyrgyz = Stream.concat(cars.stream().filter(x -> x.getReleaseYear() <= 1982),
                cars.stream().filter(x -> x.getCarModel().equalsIgnoreCase("CIVIC")
                                          || x.getCarModel().equalsIgnoreCase("Cherokee"))).distinct().toList();
        cars.removeAll(carListForKyrgyz);
        List<Car> carListForRus = Stream.concat(cars.stream().filter(x -> !x.getColor().equalsIgnoreCase("YELLOW")
                                                                          || !x.getColor().equalsIgnoreCase("RED")
                                                                          || !x.getColor().equalsIgnoreCase("Green")
                                                                          || !x.getColor().equalsIgnoreCase("Blue")),
                cars.stream().filter(x -> x.getPrice() > 40000)).distinct().toList();
        cars.removeAll(carListForRus);
        List<Car> carListForMongol = cars.stream().filter(x -> x.getVin().toLowerCase().contains("59")).toList();
        double v = carListForTurkmen.stream()
                           .map(Car::getMass)
                           .reduce(0, Integer::sum) * 7.14;

        Stream.concat(Stream.concat(Stream.concat(Stream.concat(Stream.concat(carListForTurkmen.stream(),
                                                carListForUzbek.stream()),
                                        carListForKazakh.stream()),
                                carListForKyrgyz.stream()),
                        carListForRus.stream()),
                carListForMongol.stream()).mapToDouble(x -> x.getMass() * 7.14).forEach(System.out::println);
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        //        Продолжить...
    }
}