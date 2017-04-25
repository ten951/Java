package com.wyt.headfirst.optional;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Darcy
 *         Created by Administrator on 2017/4/21.
 */

public class Demo {
    public static class Person {
        private int age;
        private Car car;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Optional<Car> getCar() {
            return Optional.ofNullable(car);
        }
    }

    public static class Car {
        private Insurance insurance;

        public Optional<Insurance> getInsurance() {
            return  Optional.ofNullable(insurance);
        }
    }

    public static class Insurance {
        private String name;

        public String getName() {
            return name;
        }
    }


    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(10);
        Optional<Person> optPerson = Optional.of(person);
        String unknown = optPerson
                .filter(p->p.getAge()>=23)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElseGet(()->{
                    UUID uuid = UUID.randomUUID();
                    return uuid.toString();
                });
        System.out.println("unknown = " + unknown);
    }
}
