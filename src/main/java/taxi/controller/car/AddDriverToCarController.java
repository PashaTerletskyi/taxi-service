package taxi.controller.car;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxi.lib.Injector;
import taxi.model.Car;
import taxi.model.Driver;
import taxi.service.CarService;
import taxi.service.DriverService;

@WebServlet(urlPatterns = "/cars/drivers/add")
public class AddDriverToCarController extends HttpServlet {
    private static final String PAGE_PATH = "/WEB-INF/views/cars/drivers/add.jsp";
    private static final Injector injector = Injector.getInstance("taxi");
    private CarService carService;
    private DriverService driverService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(PAGE_PATH).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        long driverId = Long.parseLong(req.getParameter("driver_id"));
        long carId = Long.parseLong(req.getParameter("car_id"));
        Driver driver = driverService.get(driverId);
        Car car = carService.get(carId);
        carService.addDriverToCar(driver, car);
        resp.sendRedirect("/index");
    }

    @Override
    public void init() {
        carService = (CarService) injector.getInstance(CarService.class);
        driverService = (DriverService) injector.getInstance(DriverService.class);
    }
}
