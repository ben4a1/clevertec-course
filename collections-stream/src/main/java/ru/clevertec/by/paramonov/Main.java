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
//        task14();
//        task15();
    }

    private static void task1() throws IOException {
        int ageFrom = 10;
        int ageTo = 20;
        int animalPerZoo = 7;
        int numberOfOurZoo = 3;
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(x -> ((x.getAge() >= ageFrom) && (x.getAge() < ageTo)))
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(animalPerZoo * (numberOfOurZoo - 1))
                .limit(animalPerZoo)
                .forEach(System.out::println);
    }


    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> "japanese".equalsIgnoreCase(animal.getOrigin()))
                .collect(Collectors.groupingBy(Animal::getGender))
                .forEach((gender, animalList) -> System.out.println("female".equalsIgnoreCase(gender)
                        ? animalList.stream().map(animal -> animal.getBread().toUpperCase()).collect(Collectors.toList())
                        : animalList.stream().map(Animal::getBread).collect(Collectors.toList())));
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int olderThan = 30;
        animals.stream()
                .filter(x -> x.getAge() > olderThan)
                .map(Animal::getOrigin)
                .filter(x -> x.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long femaleCount = animals.stream()
                .filter(animal -> "female".equalsIgnoreCase(animal.getGender()))
                .count();
        System.out.println(femaleCount);

    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int ageMoreThan = 20;
        int ageLessThan = 30;
        boolean isAnyHungarian = animals.stream()
                .filter(animal -> ((animal.getAge() >= ageMoreThan) && (animal.getAge() <= ageLessThan)))
                .anyMatch(animal -> "hungarian".equalsIgnoreCase(animal.getOrigin()));
        System.out.println(isAnyHungarian);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isOnlyMaleAndFemale = animals.stream()
                .allMatch(animal -> ("male".equalsIgnoreCase(animal.getGender())
                                     || "female".equalsIgnoreCase(animal.getGender())));
        System.out.println(isOnlyMaleAndFemale);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isNoOneFromOceania = animals.stream()
                .noneMatch(animal -> "Oceania".equalsIgnoreCase(animal.getOrigin()));
        System.out.println(isNoOneFromOceania);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int firstValues = 100;
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(firstValues)
                .mapToInt(Animal::getAge)
                .max().ifPresent(System.out::println);
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .mapToInt(animal -> animal.getBread().toCharArray().length)
                .min().ifPresent(System.out::println);
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .mapToInt(Animal::getAge)
                .reduce(0, Integer::sum));
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        OptionalDouble indonesianAverageAge = animals.stream()
                .filter(animal -> "Indonesian".equalsIgnoreCase(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average();
        indonesianAverageAge.ifPresent(System.out::println);
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
                .filter(person -> "male".equalsIgnoreCase(person.getGender()))
                .filter(person -> ((ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now())) >= 18)
                                  && ((ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now())) < 27))
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        Stream.concat(
                        houses.stream()
                                .filter(house -> "hospital".equalsIgnoreCase(house.getBuildingType()))
                                .flatMap(house -> house.getPersonList().stream()),
                        Stream.concat(houses.stream()
                                        .filter(house -> !"hospital".equalsIgnoreCase(house.getBuildingType()))
                                        .flatMap(house -> house.getPersonList().stream()
                                                .filter(person -> ((ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now()) <= 18)
                                                                   || (ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now()) > 59)))),
                                houses.stream()
                                        .filter(house -> !"hospital".equalsIgnoreCase(house.getBuildingType())).
                                        flatMap(personList -> personList.getPersonList().stream()
                                                .filter(person -> (ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now()) > 18
                                                                   || (ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now()) > 59))))
                        )
                ).limit(500)
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        List<Car> carListForTurkmen = Stream.concat(cars.stream().filter(car -> car.getColor().equalsIgnoreCase("WHITE")),
                cars.stream().filter(car -> car.getCarMake().equalsIgnoreCase("JAGUAR"))
        ).distinct().toList();
        cars.removeAll(carListForTurkmen);
        List<Car> carListForUzbek = Stream.concat(cars.stream().filter(car -> (car.getMass() > 1500)),
                cars.stream().filter(car -> car.getCarMake().equalsIgnoreCase("BMW")
                                            || car.getCarMake().equalsIgnoreCase("LEXUS")
                                            || car.getCarMake().equalsIgnoreCase("CHRYSLER")
                                            || car.getCarMake().equalsIgnoreCase("TOYOTA"))).distinct().toList();
        cars.removeAll(carListForUzbek);
        List<Car> carListForKazakh = Stream.concat(cars.stream().filter(car -> (car.getColor().equalsIgnoreCase("black")
                                                                                && car.getMass() > 4000)),
                cars.stream().filter(car -> (car.getCarMake().equalsIgnoreCase("GMC")
                                             || car.getCarMake().equalsIgnoreCase("DODGE")))).distinct().toList();
        cars.removeAll(carListForKazakh);
        List<Car> carListForKyrgyz = Stream.concat(cars.stream().filter(car -> car.getReleaseYear() <= 1982),
                cars.stream().filter(car -> car.getCarModel().equalsIgnoreCase("CIVIC")
                                            || car.getCarModel().equalsIgnoreCase("Cherokee"))).distinct().toList();
        cars.removeAll(carListForKyrgyz);
        List<Car> carListForRus = Stream.concat(cars.stream().filter(car -> !car.getColor().equalsIgnoreCase("YELLOW")
                                                                            || !car.getColor().equalsIgnoreCase("RED")
                                                                            || !car.getColor().equalsIgnoreCase("GREEN")
                                                                            || !car.getColor().equalsIgnoreCase("blue")),
                cars.stream().filter(car -> car.getPrice() > 40000)).distinct().toList();
        cars.removeAll(carListForRus);
        List<Car> carListForMongol = cars.stream().filter(car -> car.getVin().toLowerCase().contains("59")).toList();

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