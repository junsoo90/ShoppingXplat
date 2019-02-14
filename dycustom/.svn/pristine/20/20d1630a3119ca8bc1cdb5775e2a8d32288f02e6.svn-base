package custom.Action.custom;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;


import custom.Action.command.CommandAction;
import custom.Dao.CustomDao;
import custom.VO.CustomVO;
import custom.session.SessionListener;

public class CustomInfoCheckAction implements CommandAction {
	
	private static JSONObject result = new JSONObject();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// idCheck, idInfo, passInfo, customInfo
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Enumeration params = request.getParameterNames();
		System.out.println("----------------------------");
		
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    System.out.println(name + " : " +request.getParameter(name));
		}
		
		System.out.println("----------------------------");

		String customInfoCheck = String.valueOf(request.getParameter("customInfoCheck"));
		
		System.out.println(request.getRequestURI());
		if ( request.getMethod().equals("GET") ) {
			if( request.getRequestURI().equals("/dycustom/idSearch.do"))
				return "/views/idSearch.jsp";
			if( request.getRequestURI().equals("/dycustom/passSearch.do"))
				return "/views/passSearch.jsp";
		}
		
		
		if( customInfoCheck.equals("idCheck") )
			idCheck(request,response);
		
		else if( customInfoCheck.equals("idSearch")) {
			idInfo(request,response);
		}
		else if( customInfoCheck.equals("passSearch") )  {
			passInfo(request,response);
		}
		
		else
			customInfoCheck(request,response);
	
		response.setContentType("application/json; charset=utf8");
		
		return null;
	}
	
	// 아이디 체크
	static void idCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cId = request.getParameter("userid");
		
		CustomDao customDao = new CustomDao();
		int resultId = customDao.idCheck(cId);
		System.out.println("result = " + resultId);

		
		result.put("result", resultId);
		
		response.getWriter().print(result);
	
	}
	
	// 로그인 체크
	static void customInfoCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String cId = request.getParameter("userId");
		
		String inputPass = request.getParameter("userPass");
		if(cId == null)
			cId = request.getParameter("cId");
		if(inputPass == null)
			inputPass = request.getParameter("cInputOldPass");
		
		CustomDao customDao = new CustomDao();
		
		CustomVO orgVo = customDao.login(cId);
		
	
		JSONObject result = new JSONObject();
		
		String msg = "";
		
		if( orgVo == null || !orgVo.getcId().equals(cId) ) {
			result.put("result", 0);
			result.put("msg",URLEncoder.encode("아이디가 없습니다.", "UTF-8"));
		
		}
		else if(!orgVo.getcPass().equals(inputPass)) {
			result.put("result", -1);
			result.put("msg", URLEncoder.encode("비밀번호가 일치하지 않습니다.", "UTF-8"));
		}
		else {
			result.put("result", 1);
			result.put("msg", orgVo.getcId() +  URLEncoder.encode("님 반갑습니다" , "UTF-8"));
			
			request.getSession().setAttribute("custom", orgVo);
			request.getSession().setAttribute("loginSession", request.getSession().getId());
			
			SessionListener.sessionMap.put(cId, request.getSession().getId());
			
			
		}
		String url = String.valueOf(request.getSession().getAttribute("url"));
		System.out.println("url = " + url);
		
		if( url == null)
			url = "/views/productList.do";
		result.put("url", url);
		
		System.out.println(URLDecoder.decode(result.get("msg").toString()));
		
		response.getWriter().print(result);
	}
	
	// 아이디 찾기
	static void idInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		String cName = request.getParameter("cName");
		String cPhone1 = request.getParameter("cPhone1");
		String cPhone2 = request.getParameter("cPhone2");
		String cPhone3 = request.getParameter("cPhone3");
		CustomDao customDao = new CustomDao();
		
		System.out.println(cName);
		
		CustomVO customVo = new CustomVO(cName, cPhone1, cPhone2, cPhone3);
		
		List<CustomVO> resultVoList = customDao.idSearch(customVo);
		
		
		String msg = "";
		Boolean bol = false;
		
		if(resultVoList == null || resultVoList.size() == 0) {
			msg = "아이디를 찾을 수 없습니다";
			// mav.addObject("url","custom/idSearch.do");
			
			bol = false;
		}
		else {
			bol = true;
			msg += "찾으시는 아이디는 : ";
			for(int i=0; i<resultVoList.size();i++) {
				msg += resultVoList.get(i).getcId();
				if(resultVoList.size()-1 != i)
					msg += " , " ;
			}
			msg += " 입니다 ";
			
		}
		
		//mav.addObject("msg",msg);
		//mav.addObject("result",bol);
		System.out.println("msg = " + msg);
		result.put("result", bol);
		result.put("msg", msg);
		
		//mav.setViewName("jsonView");
		response.getWriter().print(result);
		
		
	}
	
	// 비밀번호 찾기
	static void passInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String cId = request.getParameter("cId");
		String cName = request.getParameter("cName");
		String cPhone1 = request.getParameter("cPhone1");
		String cPhone2 = request.getParameter("cPhone2");
		String cPhone3 = request.getParameter("cPhone3");
		
		CustomVO customVo = new CustomVO(cId, cName,cPhone1,cPhone2, cPhone3);
		String msg = "";
		
		CustomDao customDao = new CustomDao();
		String resultPass = customDao.passSearch(customVo);
		
		System.out.println("resultPass = " + resultPass);
		Boolean bol;

		if(resultPass == null || resultPass == "") {
			msg = "비밀번호를 찾을 수 없습니다. 정보를 정확하게 입력해주세요";
			// mav.addObject("url","custom/idSearch.do");
			
			bol = false;
		}
		else {
			bol = true;
			msg += "찾으시는 비밀번호는 : " 
					+ resultPass + " 입니다";	
			
		}
		
		result.put("result", bol);
		result.put("msg", msg);
		
		System.out.println("result = " + result);
		response.getWriter().print(result);
	}

	public void customLoginAction(){
		
	}
}
