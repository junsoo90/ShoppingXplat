package custom.Action.custom;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import custom.Action.command.CommandAction;
import custom.Dao.CustomDao;
import custom.VO.CustomVO;
import custom.session.SessionListener;

public class CustomUpdateAction implements CommandAction {
	CustomDao customDao = new CustomDao();
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if(request.getSession().getAttribute("custom") == null) {
			request.getSession().setAttribute("url", "/views/productList.do");
			return "login.do";
		}
		
		System.out.println("update " + request.getMethod());
		Enumeration params = request.getParameterNames();
		System.out.println("----------------------------");
		
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    System.out.println(name + " : " +request.getParameter(name));
		}
		
		System.out.println("----------------------------");
		
		if(request.getRequestURI().equals("/dycustom/views/customUpdate.do")) {
			customUpdateGet(request, response);
			return "/views/customUpdate.jsp";
		}
		else if(request.getRequestURI().equals("/dycustom/views/customUpdate.ajax"))
			customUpdate(request, response);
		
		
		return null;
	}
	public void customUpdateGet(HttpServletRequest request, HttpServletResponse response) {
		CustomVO loginCustom = (CustomVO)request.getSession().getAttribute("custom");
		System.out.println("loginCustom = " + loginCustom);
		System.out.println("loginCustom.getcId() = " + loginCustom.getcId());
		
		CustomVO customVo = customDao.customInfo(loginCustom.getcId());
		
		request.getSession().setAttribute("customVo", customVo);
	}
	
	public void customUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
    	String cId = request.getParameter("cId");
		String cInputOldPass = request.getParameter("cInputOldPass");
		String cChangePass = request.getParameter("cChangePass");
		String cName = request.getParameter("cName");
		String cAdd = request.getParameter("cAdd");
		String cAddCode = request.getParameter("cAddCode");
		String cAddDetail = request.getParameter("cAddDetail");
		
		String cPhone1 = request.getParameter("cPhone1");
		String cPhone2 = request.getParameter("cPhone2");
		String cPhone3 = request.getParameter("cPhone3");
	
		if(cChangePass == "" || cChangePass == null)
			cChangePass = cInputOldPass;
		
		CustomVO customVo = new CustomVO(cId,cChangePass,cName
				,cAdd,cAddCode,cAddDetail,cPhone1,cPhone2,cPhone3);
		
		customDao.customUpdate(customVo);
		
		
	}

}
