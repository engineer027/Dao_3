package mate.academy.controllers;

import mate.academy.lib.Injector;
import mate.academy.model.Car;
import mate.academy.model.Manufacturer;
import mate.academy.service.CarService;
import mate.academy.service.ManufacturerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy");
    CarService carService = (CarService) injector.getInstance(CarService.class);
    ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cars/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String model = req.getParameter("model");
        Long manufacturerId = Long.valueOf(req.getParameter("manufacturerId"));
        Manufacturer manufacturer = manufacturerService.get(manufacturerId);
        Car car = new Car(model, manufacturer);
        carService.create(car);
        req.setAttribute("message", "Car successfully added to the database");
        req.getRequestDispatcher("/WEB-INF/views/cars/create.jsp").forward(req, resp);
    }
}
