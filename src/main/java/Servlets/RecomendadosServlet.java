package Servlets;

import Daos.CancionDao;
import Daos.TourDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.concurrent.CancellationException;

@WebServlet(name = "RecomendadosServlet", value = "/listaRecomendados")
public class RecomendadosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CancionDao cancionDao = new CancionDao();
        String action = request.getParameter("a") == null ? "listar" : request.getParameter("a");
        switch (action) {
            case "listar":
                request.setAttribute("listaMasReproducidas", cancionDao.listarMasReproducidas());
                request.getRequestDispatcher("/listaRecomendados.jsp").forward(request, response);
                break;
            case "meGusta":
                String id = request.getParameter("id");
                cancionDao.MeGusta(id);
                request.getRequestDispatcher("/listaCanciones").forward(request, response);
                break;
            case "NoMeGusta":
                String id2 = request.getParameter("id");
                cancionDao.NoMeGusta(id2);
                response.sendRedirect(request.getContextPath() + "/ListaFavoritos");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
