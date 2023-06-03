package Servlets;

import Beans.Lista;
import Daos.CancionDao;
import Daos.ListasDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ListasServlet", value = "/Listas")
public class ListasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ListasDao listasDao = new ListasDao();
        String action = request.getParameter("a") == null ? "listar" : request.getParameter("a");

        switch (action) {
            case "listar":
                request.setAttribute("listasDeReproduccion", listasDao.listarTodas());
                request.getRequestDispatcher("/listas.jsp").forward(request, response);
                break;
            case "borrar":
                String id = request.getParameter("id");
                listasDao.borrar(id);
                response.sendRedirect(request.getContextPath() + "/Listas");
                break;
            case "ListarPorID":
                String id2 = request.getParameter("id");
                int id3 = Integer.parseInt(id2);
                request.setAttribute("listaEspecifica", listasDao.listaInspeccionar(id2));
                request.setAttribute("ListaActual",listasDao.obtenerListaEspecifica(id3));
                request.getRequestDispatcher("/inspeccionDeLista.jsp").forward(request, response);
                break;

            case "inspeccionar":
                String id4 = request.getParameter("id");
                int id5 = Integer.parseInt(id4);
                request.setAttribute("cancionesPorLista", listasDao.obtenerCancionesFavoritasPorReproductor(id4));
                request.setAttribute("ListaActual",listasDao.obtenerListaEspecifica(id5));
                request.getRequestDispatcher("/listaPorReproductor.jsp").forward(request, response);
                break;

            case "MeGusta":
                String id6 = request.getParameter("idCancion");
                String id7 = request.getParameter("idLista");
                int id8 = Integer.parseInt(id7);
                listasDao.anadirCancionAlista(id6,id7);
                request.setAttribute("listaEspecifica", listasDao.listaInspeccionar(id7));
                request.setAttribute("ListaActual",listasDao.obtenerListaEspecifica(id8));
                request.getRequestDispatcher("/inspeccionDeLista.jsp").forward(request, response);
                break;
            case "NoMeGusta":
                String id9 = request.getParameter("idCancion");
                String id10 = request.getParameter("idLista");
                int id11 = Integer.parseInt(id10);
                listasDao.eliminarCancionAlista(id9,id10);
                request.setAttribute("cancionesPorLista", listasDao.obtenerCancionesFavoritasPorReproductor(id10));
                request.setAttribute("ListaActual",listasDao.obtenerListaEspecifica(id11));
                request.getRequestDispatcher("/listaPorReproductor.jsp").forward(request, response);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("p") == null ? "crear" : request.getParameter("p");
        ListasDao listasDao = new ListasDao();
        switch (action) {
            case "crear":
                request.setAttribute("listasDeReproduccion", listasDao.listarTodas());
                request.getRequestDispatcher("/listas.jsp").forward(request, response);
                break;
            case "a": //añadir
                String nameLista = request.getParameter("nameLista");
                ListasDao.crearLista(nameLista);
                response.sendRedirect(request.getContextPath() + "/Listas");
                break;/*
            case "b":
                String textoBuscar = request.getParameter("textoBuscar");
                request.setAttribute("lista", jobDao.buscarPorTitle(textoBuscar));
                request.getRequestDispatcher("jobs/lista.jsp").forward(request, response);
                break;*/
        }
    }
}
