package ru.clevertec.by.paramonov;

import ru.clevertec.by.paramonov.model.*;
import ru.clevertec.by.paramonov.util.Util;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;
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
        task16();
    }

    private static void task1() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
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
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> "japanese".equalsIgnoreCase(animal.getOrigin()))
                .collect(Collectors.groupingBy(Animal::getGender))
                .forEach((gender, animalList) -> System.out.println("female".equalsIgnoreCase(gender)
                        ? animalList.stream().map(animal -> animal.getBread().toUpperCase()).collect(Collectors.toList())
                        : animalList.stream().map(Animal::getBread).collect(Collectors.toList())));
    }

    private static void task3() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
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
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        List<Animal> animals = Util.getAnimals();
        long femaleCount = animals.stream()
                .filter(animal -> "female".equalsIgnoreCase(animal.getGender()))
                .count();
        System.out.println(femaleCount);

    }

    private static void task5() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        List<Animal> animals = Util.getAnimals();
        int ageMoreThan = 20;
        int ageLessThan = 30;
        boolean isAnyHungarian = animals.stream()
                .filter(animal -> ((animal.getAge() >= ageMoreThan) && (animal.getAge() <= ageLessThan)))
                .anyMatch(animal -> "hungarian".equalsIgnoreCase(animal.getOrigin()));
        System.out.println(isAnyHungarian);
    }

    private static void task6() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        List<Animal> animals = Util.getAnimals();
        boolean isOnlyMaleAndFemale = animals.stream()
                .allMatch(animal -> ("male".equalsIgnoreCase(animal.getGender())
                                     || "female".equalsIgnoreCase(animal.getGender())));
        System.out.println(isOnlyMaleAndFemale);
    }

    private static void task7() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        List<Animal> animals = Util.getAnimals();
        boolean isNoOneFromOceania = animals.stream()
                .noneMatch(animal -> "Oceania".equalsIgnoreCase(animal.getOrigin()));
        System.out.println(isNoOneFromOceania);
    }

    private static void task8() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        List<Animal> animals = Util.getAnimals();
        int firstValues = 100;
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(firstValues)
                .mapToInt(Animal::getAge)
                .max().ifPresent(System.out::println);
    }

    private static void task9() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .mapToInt(animal -> animal.getBread().toCharArray().length)
                .min().ifPresent(System.out::println);
    }

    private static void task10() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .mapToInt(Animal::getAge)
                .reduce(0, Integer::sum));
    }

    private static void task11() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> "Indonesian".equalsIgnoreCase(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average().ifPresent(System.out::println);
    }

    private static void task12() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        List<Person> people = Util.getPersons();
        int ageMin = 18;
        int ageMax = 27;
        int armadaSize = 200;
        people.stream()
                .filter(person -> "male".equalsIgnoreCase(person.getGender()))
                .filter(person -> ((ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now())) >= ageMin)
                                  && ((ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now())) < ageMax))
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(armadaSize)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        List<House> houses = Util.getHouses();
        int firstArkSize = 500;
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
                ).limit(firstArkSize)
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        List<Car> cars = Util.getCars();
        DecimalFormat df = new DecimalFormat(".##");
        List<Car> carListForTurkmen = Stream.concat(cars.stream().filter(car -> "WHITE".equalsIgnoreCase(car.getColor())),
                cars.stream().filter(car -> "JAGUAR".equalsIgnoreCase(car.getCarMake()))
        ).distinct().toList();
        cars.removeAll(carListForTurkmen);
        List<Car> carListForUzbek = Stream.concat(cars.stream().filter(car -> (car.getMass() < 1500)),
                cars.stream().filter(car -> "BMW".equalsIgnoreCase(car.getCarMake())
                                            || "LEXUS".equalsIgnoreCase(car.getCarMake())
                                            || "CHRYSLER".equalsIgnoreCase(car.getCarMake())
                                            || "TOYOTA".equalsIgnoreCase(car.getCarMake()))).distinct().toList();
        cars.removeAll(carListForUzbek);
        List<Car> carListForKazakh = Stream.concat(cars.stream().filter(car -> ("BLACK".equalsIgnoreCase(car.getColor())
                                                                                && car.getMass() > 4000)),
                cars.stream().filter(car -> ("GMC".equalsIgnoreCase(car.getCarMake())
                                             || "DODGE".equalsIgnoreCase(car.getCarMake())))).distinct().toList();
        cars.removeAll(carListForKazakh);
        List<Car> carListForKyrgyz = Stream.concat(cars.stream().filter(car -> car.getReleaseYear() <= 1982),
                cars.stream().filter(car -> "CIVIC".equalsIgnoreCase(car.getCarModel())
                                            || "CHEROKEE".equalsIgnoreCase(car.getCarModel()))).distinct().toList();
        cars.removeAll(carListForKyrgyz);
        List<Car> carListForRus = Stream.concat(cars.stream().filter(car -> (!"YELLOW".equalsIgnoreCase(car.getColor())
                                                                             && !"RED".equalsIgnoreCase(car.getColor())
                                                                             && !"GREEN".equalsIgnoreCase(car.getColor())
                                                                             && !"BLUE".equalsIgnoreCase(car.getColor()))),
                cars.stream().filter(car -> car.getPrice() > 40000)).distinct().toList();
        cars.removeAll(carListForRus);
        List<Car> carListForMongol = cars.stream().filter(car -> car.getVin().contains("59")).toList();

        Map<String, List<Car>> stringListMap = new HashMap<>();
        stringListMap.put("Turkmenistan", carListForTurkmen);
        stringListMap.put("Uzbekistan", carListForUzbek);
        stringListMap.put("Kazakhstan", carListForKazakh);
        stringListMap.put("Kyrgyzstan", carListForKyrgyz);
        stringListMap.put("Russia", carListForRus);
        stringListMap.put("Mongolia", carListForMongol);


        stringListMap.forEach((country, carList) -> System.out.println(country + " : \n\t" +
                                                                       "summary mass of echelon: " + df.format(carList.stream()
                .map(Car::getMass)
                .reduce(0, Integer::sum)) +
                                                                       "$\n\tcost of transport costs: " +
                                                                       df.format(carList.stream()
                                                                               .map(car -> car.getMass() * 7.14)
                                                                               .reduce(0.0, Double::sum)) + "$"));
        System.out.println("\nTotal revenue of the logistics campaign: \n\t" + df.format(Stream.of(carListForKazakh, carListForKyrgyz, carListForMongol, carListForTurkmen, carListForUzbek, carListForRus)
                .map(x -> x.stream().map(car -> car.getMass() * 7.14).reduce(0.0, Double::sum))
                .reduce(0.0, Double::sum)) + "$");
    }

    private static void task15() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        List<Flower> flowers = Util.getFlowers();
        String regex = "^[C-S]\\D*";
        DecimalFormat df = new DecimalFormat(".##");
        System.out.println("Flowers cost a fortune: " + df.format(flowers.stream().sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Flower::getWaterConsumptionPerDay).reversed())
                .filter(flower -> Pattern.matches(regex, flower.getCommonName()))
                .filter(Flower::isShadePreferred)
                .filter(flower
                        -> new HashSet<>(flower.getFlowerVaseMaterial()).containsAll(new ArrayList<>(Arrays.asList("Glass", "Aluminum", "Steel"))))
                .map(flower -> (flower.getWaterConsumptionPerDay() * 365 * 5) * 1.39)
                .reduce(0.0, Double::sum)) + "$");
    }

    private static void task16() throws IOException {
        System.out.println("\n" + new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName().toUpperCase() + "\n");
        Random random = new Random();
        List<Flower> flowers = Util.getFlowers();
        List<Car> cars = Util.getCars();
        List<Animal> animals = Util.getAnimals();
        List<HomoErectus> people = HomoErectus.getPersons().stream()
                .peek(homoErectus -> homoErectus.setFlower(flowers.get(random.nextInt(flowers.size() - 1))))
                .peek(homoErectus -> homoErectus.setCar(cars.get(random.nextInt(cars.size() - 1))))
                .peek(homoErectus -> homoErectus.setAnimal(animals.get(random.nextInt(cars.size() - 1))))
                .toList();

        System.out.println(people.stream()
                                   .filter(homoErectus -> "female".equalsIgnoreCase(homoErectus.getGender()))
                                   .filter(homoErectus -> ChronoUnit.YEARS.between(homoErectus.getDateOfBirth(), LocalDate.now()) > 18
                                                          && ChronoUnit.YEARS.between(homoErectus.getDateOfBirth(), LocalDate.now()) < 48)
                                   .filter(homoErectus -> homoErectus.getFirstName().length() > 3)
                                   .filter(homoErectus -> (homoErectus.getOccupation().toUpperCase().contains("SALES")
                                                           || homoErectus.getOccupation().toUpperCase().contains("ENGINEER")))
                                   .filter(homoErectus -> "PORSCHE".equalsIgnoreCase(homoErectus.getCar().getCarMake())
                                                          || "MERCEDES-BENZ".equalsIgnoreCase(homoErectus.getCar().getCarMake())
                                                          || "JAGUAR".equalsIgnoreCase(homoErectus.getCar().getCarMake()))
                                   .filter(homoErectus -> homoErectus.getFlower().getFlowerVaseMaterial().contains("Glass"))
//                .filter(homoErectus -> homoErectus.getAnimal().getBread().matches("(w+[cat] )|(D|d)og|(L|l)obster"))
                                   .limit(40)
                                   .sorted(Comparator.comparing(HomoErectus::getCity))
                                   .filter(homoErectus -> !BigInteger.valueOf(homoErectus.getId()).isProbablePrime(123111))
                                   .max(Comparator.comparingInt(o -> o.getFlower().getCommonName().length())).orElse(null) != null
                ? "true"
                : "false... meow/woof/lobster`s sound");
    }
}