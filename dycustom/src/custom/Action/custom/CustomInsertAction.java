package custom.Action.custom;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import custom.Action.command.CommandAction;
import custom.Dao.CustomDao;
import custom.VO.CustomVO;

public class CustomInsertAction implements CommandAction {
	CustomDao customDao = new CustomDao();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if(request.getMethod().equals("GET"))
				return "/views/customInsert.jsp";
		
		return customInsert(request, response);
		
	}

	public String customInsert(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		Enumeration params = request.getParameterNames();
		System.out.println("----------------------------");
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    System.out.println(name + " : " +request.getParameter(name));
		}
		System.out.println("----------------------------");
		String cId = request.getParameter("cId");
		String cPass = request.getParameter("cPass");
		String cName = request.getParameter("cName");
		String cAddCode = request.getParameter("cAddCode");
		String cAdd = request.getParameter("cAdd");
		String cAddDetail = request.getParameter("cAddDetail");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		
		CustomVO customVo = new CustomVO(cId,cPass,cName,cAddCode,cAdd,cAddDetail,phone1,phone2,phone3);
		
		customDao.customInsert(customVo);
		
		return "/views/productList.do";
	}
	
}
