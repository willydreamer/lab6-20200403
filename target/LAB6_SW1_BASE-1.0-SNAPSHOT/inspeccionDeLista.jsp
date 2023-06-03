<%@ page import="Beans.Tour" %>
<%@ page import="Beans.Cancion" %>
<%@ page import="Beans.Lista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<Beans.Cancion>" scope="request" id="listaEspecifica"/>
<jsp:useBean type="Beans.Lista" scope="request" id="ListaActual"/>

<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="listas"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="listas"/>
    </jsp:include>
    <div class="pb-5 pt-4 px-3 titlecolor">
        <div class="col-lg-6">
            <h3 class='text-light'>Añade tus canciones favoritas a </h3><h2 style="color: chartreuse; font-family: 'Bauhaus 93'"><%=ListaActual.getNameLista()%></h2>
        </div>
        <div class="text-center">
            <a href="<%=request.getContextPath()%>/Listas" class="btn btn-outline-warning"><i class="bi bi-arrow-left"></i> Regresar</a>
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
                for (Cancion cancion : listaEspecifica) {
            %>
            <tr>
                <td><%=cancion.getIdCancion()%>
                </td>
                <td><%=cancion.getNombreCancion()%>
                </td>
                <td><%=cancion.getBanda()%>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/Listas?a=MeGusta&idCancion=<%=cancion.getIdCancion()%>&idLista=<%=ListaActual.getIdLista()%>">
                        <i class="bi bi-plus-circle" data-toggle="tooltip" data-placement="top" title="Añadir a lista"></i>
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