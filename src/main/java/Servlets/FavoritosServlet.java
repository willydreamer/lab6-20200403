package Servlets;

import Daos.CancionDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FavoritosServlet", value = "/ListaFavoritos")
public class FavoritosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CancionDao cancionDao = new CancionDao();
        String action = request.getParameter("a") == null ? "listar" : request.getParameter("a");
        switch (action) {
            case "listar":
                request.setAttribute("listaFavoritos", cancionDao.listarFavoritas());
                request.getRequestDispatcher("/listaFavoritos.jsp").forward(request, response);
                break;
            case "listarBanda":
                String id = request.getParameter("id");
                request.setAttribute("listaCancionesBanda", cancionDao.listarPorBanda(id));
                request.getRequestDispatcher("/listaCancionesPorBanda.jsp").forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
