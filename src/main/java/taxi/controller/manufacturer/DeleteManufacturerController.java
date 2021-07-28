package taxi.controller.manufacturer;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxi.lib.Injector;
import taxi.service.ManufacturerService;

public class DeleteManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private ManufacturerService manufacturerService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        manufacturerService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("/index");
    }

    @Override
    public void init() {
        manufacturerService = (ManufacturerService) injector.getInstance(ManufacturerService.class);
    }
}
