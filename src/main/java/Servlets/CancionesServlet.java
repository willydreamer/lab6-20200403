package Servlets;

import Daos.CancionDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CancionesServlet", value = "/listaCanciones")
public class CancionesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CancionDao cancionDao = new CancionDao();
        request.setAttribute("listaCanciones", cancionDao.listarTodas());
        request.getRequestDispatcher("/listaCanciones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
