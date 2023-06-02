package Servlets;

import Daos.TourDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RecomendadosServlet", value = "/listaRecomendados")
public class RecomendadosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TourDao tourDao = new TourDao();
        request.setAttribute("listaMasReproducidas", tourDao.listarMasReproducidas());
        request.getRequestDispatcher("/listaRecomendados.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
