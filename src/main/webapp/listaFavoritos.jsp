<%@ page import="Beans.Tour" %>
<%@ page import="Beans.Cancion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<Beans.Cancion>" scope="request" id="listaFavoritos"/>
<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="Lista Favoritos"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="favoritos"/>
    </jsp:include>
    <div class="pb-5 pt-4 px-3 titlecolor">
        <div class="col-lg-6">
            <h1 class='text-light'>Lista de Canciones Favoritas</h1>
        </div>
    </div>
    <div class="tabla">
        <table class="table table-dark table-transparent table-hover">
            <thead>
            <th>ID</th>
            <th>CANCION</th>
            <th>BANDA</th>
            <th></th>
            </thead>
            <%
                for (Cancion cancion : listaFavoritos) {
            %>
            <tr>
                <td><%=cancion.getIdCancion()%>
                </td>
                <td><%=cancion.getNombreCancion()%>
                </td>
                <td><%=cancion.getBanda()%>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/listaRecomendados?a=NoMeGusta&id=<%=cancion.getIdCancion()%>">
                        <i class=" bi <%= (cancion.getLike() == 0) ? "bi-trash heart-icon" : "bi-trash-fill heart-icon-liked" %>" data-toggle="tooltip" data-placement="top" title="Eliminar de la lista"></i>
                    </a>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>

</div>
<jsp:include page="/static/scripts.jsp"/>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
</body>
</html>
