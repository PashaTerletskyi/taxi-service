package taxi.controller.driver;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxi.lib.Injector;
import taxi.model.Driver;
import taxi.service.DriverService;

@WebServlet(urlPatterns = "/drivers")
public class GetAllDriversController extends HttpServlet {
    private static final String PAGE_PATH = "WEB-INF/views/drivers/all.jsp";
    private static final Injector injector = Injector.getInstance("taxi");
    private DriverService driverService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Driver> allDrivers = driverService.getAll();
        req.setAttribute("drivers", allDrivers);
        req.getRequestDispatcher(PAGE_PATH).forward(req, resp);
    }

    @Override
    public void init() {
        driverService = (DriverService) injector.getInstance(DriverService.class);
    }
}
