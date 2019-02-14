package custom.Action.custom;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import custom.Action.command.CommandAction;
import custom.Dao.CustomDao;

public class CustomDeleteAction implements CommandAction {
	
	CustomDao customDao;
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		customDao = new CustomDao();
		
		Enumeration params = request.getParameterNames();
		System.out.println("----------------------------");
		
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    System.out.println(name + " : " +request.getParameter(name));
		}
		
		System.out.println("----------------------------");

		customDelete(request,response);
		
		return null;
	}
	
	void customDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cId = request.getParameter("cId");
		System.out.println("delete cId = " + cId);

		customDao.customDelete(cId);
	}
	
}
