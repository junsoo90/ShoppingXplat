package custom.Filter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import custom.Dao.CustomDao;
import custom.VO.CustomVO;
import custom.session.SessionListener;

public class LoginFilter implements javax.servlet.Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("============== filter gogo =============");
		
				
		HttpServletRequest h_request = (HttpServletRequest)request;
		HttpServletResponse h_response = (HttpServletResponse)response;
		
		HttpSession session = h_request.getSession();
		
		if(session.getAttribute("url") == null || session.getAttribute("url") == "")
			session.setAttribute("url", "/views/productList.jsp");
		
		ConcurrentHashMap<String, String> sessionMap = SessionListener.sessionMap;
		
		CustomDao customDao = new CustomDao();
		
		CustomVO customVo = (CustomVO)session.getAttribute("custom");
		String orgLoginSession = "";
		
		if(customVo != null) {
			orgLoginSession = SessionListener.sessionMap.get(customVo.getcId());
			
			int count = customDao.idCheck(customVo.getcId());
			System.out.println("count = " + count);
			
			if(orgLoginSession != session.getId() || count <= 0) {
				System.out.println("remove cId");
				session.removeAttribute(customVo.getcId());
				session.removeAttribute("loginSession");
				session.removeAttribute("custom");
				session.invalidate();
				//response.sendRedirect("login.do");
				//return false;
			}
			
		}
			
		System.out.println("orgLoginSession = " + orgLoginSession);
		System.out.println("current session = " + session.getId());
	
		System.out.println("============== end =============");
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
