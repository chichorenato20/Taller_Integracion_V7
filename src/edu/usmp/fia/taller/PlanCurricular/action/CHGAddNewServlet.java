package edu.usmp.fia.taller.PlanCurricular.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.usmp.fia.taller.common.bean.PlanCurricular.ChangeBean;
import edu.usmp.fia.taller.common.bean.PlanCurricular.Curso;
import edu.usmp.fia.taller.common.bean.PlanCurricular.ResponseBean;
import edu.usmp.fia.taller.PlanCurricular.business.ChangeBusiness;
import edu.usmp.fia.taller.PlanCurricular.business.impl.ChangeBusinessImpl;
import edu.usmp.fia.taller.PlanCurricular.util.Constants;
import edu.usmp.fia.taller.PlanCurricular.util.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class CHGNameServlet
 */
@WebServlet("/addCourse")
public class CHGAddNewServlet extends HttpServlet implements Constants {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher reqDisp = getServletContext().getRequestDispatcher("/" + VIEW_PAGE_PATH +"formAddCourse.jsp");
		reqDisp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ResponseBean<ChangeBean> jresponse = new ResponseBean<ChangeBean>();
		PrintWriter out 	= response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		
		try {
			/* Get Request Parameters */
			int type			= Utils.getIntegerParameter(request, "type");
			int cycle			= Utils.getIntegerParameter(request, "cycle");
			String name			= request.getParameter("name");
			int tHs				= Utils.getIntegerParameter(request, "teo");;
			int pHs				= Utils.getIntegerParameter(request, "prac");;
			int lHs				= Utils.getIntegerParameter(request, "lab");;
			String mentions[]	=request.getParameterValues("mention");
			
			/* Get Session Attributes */
			List<Curso> courses 	= Utils.getSessionCourses(request);
			List<Curso> newCourses = Utils.getSessionNewCourses(request);
			List<ChangeBean> changes 	= Utils.getSessionChanges(request);
			
			/* Apply Business Rules */
			ChangeBusiness chgBusiness 	= new ChangeBusinessImpl();
			ChangeBean change = chgBusiness.changeAddCourse(type, name, cycle,
												tHs, pHs, lHs, mentions, courses, newCourses, changes);
			
			/* Update changes and new courses in session */
			request.getSession().setAttribute(SESSION_NEW_COURSES, newCourses);
			request.getSession().setAttribute(SESSION_CHANGES, changes);
			
			/* Complete response details */
			jresponse.setCode(change.getCode());
			jresponse.setMessage(change.getMessage());
			jresponse.setData(change);
			out.print(mapper.writeValueAsString(jresponse));
		} catch (Exception e) {
			e.printStackTrace();
			
			jresponse.setCode(500);
			jresponse.setMessage("Lo sentimos ocurrio un problema al procesar su cambio. Intentelo nuevamente o contactenos.");
			jresponse.setData(null);
			out.print(mapper.writeValueAsString(jresponse));
		}
		out.close();
	}
}