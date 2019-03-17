package com.lelar;

import java.util.Date;

public class Main {
    static CarService service = new CarService();

    public static void main(String... args) {
        Car car1 = new Car();
        car1.setName("BMW");
        car1.setCost(20000);
        car1.setReleaseDate(new Date());

        Car car = service.add(car1);
    }
}
