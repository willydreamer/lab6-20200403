<%@ page import="Beans.Tour" %>
<%@ page import="Beans.Cancion" %>
<%@ page import="Beans.Lista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<Beans.Lista>" scope="request" id="listasDeReproduccion"/>
<html>
<jsp:include page="/static/head.jsp">
  <jsp:param name="title" value="Listas"/>
</jsp:include>
<body>
<div class='container'>
  <jsp:include page="/includes/navbar.jsp">
    <jsp:param name="page" value="listas"/>
  </jsp:include>
  <div class="pb-5 pt-4 px-3 titlecolor">
    <div class="col-lg-6">
      <h1 class='text-light'>Listas de Reproducción</h1>
      <h3 class='text-light'>Cree una lista personalizada</h3>
    </div>

    <div class="text-center">
      <button type="button" class="btn btn-success" data-toggle="modal" data-target="#crearListaModal">
        <i class="bi bi-music-note-list"></i> Crear lista
      </button>
    </div>
    <div class="modal fade" id="crearListaModal" tabindex="-1" role="dialog" aria-labelledby="crearListaModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="crearListaModalLabel">Crear lista</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <!-- Aquí puedes agregar el contenido del formulario -->
            <form method="POST" action="<%=request.getContextPath()%>/Listas?p=a">
              <div class="mb-3">
                <label for="nameLista">Nombre de lista</label>
                <input type="text" class="form-control" name="nameLista" id="nameLista">
              </div>
              <a class="btn btn-danger" href="<%=request.getContextPath()%>/Listas">Cancelar</a>
              <button type="submit" class="btn btn-primary">Crear</button>
            </form>
          </div>
        </div>
      </div>
    </div>

  </div>
  <div class="tabla">
    <table class="table table-dark table-transparent table-hover">
      <thead>
      <th>ID</th>
      <th>Nombre de Lista</th>
      <th></th>
      <th></th>
      </thead>
      <%
        for (Lista lista : listasDeReproduccion) {
      %>
      <tr>
        <td><%=lista.getIdLista()%>
        </td>
        <td><%=lista.getNameLista()%>
        </td>
        <td>
          <a href="<%=request.getContextPath()%>/Listas?a=inspeccionar&id=<%=lista.getIdLista()%>" class="btn btn-outline-primary "><i class="bi bi-eye"></i> Inspeccionar</a>
        </td>
        <td>
          <a href="<%=request.getContextPath()%>/Listas?a=ListarPorID&id=<%=lista.getIdLista()%>" class="btn btn-outline-success"> <i class="bi bi-music-note-beamed"></i> Añadir canciones</a>
        </td>
        <td><a onclick="return confirm('Estas seguro de borrar esta lista:( ?')" class="btn btn-danger"
               href="<%=request.getContextPath()%>/Listas?a=borrar&id=<%=lista.getIdLista()%>"><i class="bi bi-trash3"></i></a>
        </td>
      </tr>
      <%
        }
      %>
    </table>
  </div>
  <h5 style="color: darkgoldenrod; font-size: 15px ">Asumimos que una cancion puede estar en distintas listas de reproduccion*</h5>


</div>
<jsp:include page="/static/scripts.jsp"/>
</body>
</html>