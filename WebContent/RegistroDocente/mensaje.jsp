
<!DOCTYPE html>
<%@page import="edu.usmp.fia.taller.common.bean.RegistroDocente.Personaa"%>
<%@page import="java.util.Vector"%>
<%@page import="edu.usmp.fia.taller.common.action.SessionParameters"%>
<%@page import="edu.usmp.fia.taller.common.bean.Usuario"%>
<%@page import="edu.usmp.fia.taller.common.bean.Persona"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Taller Proyectos</title>
<link href="<%=request.getServletContext().getContextPath() %>/RegistroDocente/css/boostrap/bootstrap.min.css" rel="stylesheet" />
<link href="<%=request.getServletContext().getContextPath() %>/RegistroDocente/css/boostrap/bootstrap-table.min.css" rel="stylesheet" />
 <link href="<%=request.getServletContext().getContextPath() %>/RegistroDocente/css/dashboard.css" rel="stylesheet" />
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 <script src="<%=request.getServletContext().getContextPath() %>/RegistroDocente/js/bootstrap-table.min.js"></script>
 <script src="<%=request.getServletContext().getContextPath() %>/RegistroDocente/js/validator.min.js"></script>
 <script src="<%=request.getServletContext().getContextPath() %>/RegistroDocente/js/combos.js"></script>
 <script src="<%=request.getServletContext().getContextPath() %>/RegistroDocente/js/accionesRegistroDocentes.js"></script>
<jsp:include page="/resources/include/header-resources.jsp"></jsp:include>
</head>

<%
Vector<Personaa> listaDocentes=(Vector)request.getAttribute("docentes");
	Usuario oUsuario = (Usuario) request.getSession(false).getAttribute(SessionParameters.USUARIO.text());
	Persona oPersona = oUsuario.getPersona();
%>


<body class="page-body skin-red" style="padding-top: -0;">
	<div class="page-container">
		<jsp:include page="/resources/include/sidebar-menu.jsp"></jsp:include>
		<div class="main-content">
			<jsp:include page="/resources/include/profile-bar.jsp"></jsp:include>

			<!-- Contenido -->
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<%
	String mensaje = (String)request.getAttribute("mensaje");
%>
<h2>Resultado de la operación</h2>
<h3><%=mensaje %></h3>
			</div>
			
					<jsp:include page="/resources/include/footer.jsp"></jsp:include>
		</div>



		<!-- Fin contenido -->
	<jsp:include page="/resources/include/chat.jsp"></jsp:include>
	</div>
	</div>

	<jsp:include page="/resources/include/footer-resources.jsp"></jsp:include>

</body>
</html>




