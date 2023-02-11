package ru.clevertec.by.paramonov;

import com.fasterxml.jackson.core.JsonToken;
import ru.clevertec.by.paramonov.model.*;
import ru.clevertec.by.paramonov.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
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
        LocalDate now = LocalDate.now();
        people.stream()
                .filter(x -> x.getGender().equalsIgnoreCase("male"))
                .filter(x -> ((ChronoUnit.YEARS.between(x.getDateOfBirth(), now)) >= 18)
                             && ((ChronoUnit.YEARS.between(x.getDateOfBirth(), now)) < 27))
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        //        Продолжить...
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        //        Продолжить...
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        //        Продолжить...
    }
}