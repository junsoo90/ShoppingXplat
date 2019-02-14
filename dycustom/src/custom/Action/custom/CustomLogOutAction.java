package custom.Action.custom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import custom.Action.command.CommandAction;
import custom.VO.CustomVO;
import custom.session.SessionListener;

public class CustomLogOutAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		CustomVO vo = (CustomVO) request.getSession().getAttribute("custom");

		if(vo != null) {
			request.getSession().removeAttribute("custom");
			request.getSession().removeAttribute("loginSession");
			SessionListener.sessionMap.remove(vo.getcId());
		}
		
		String url = request.getParameter("url");
		if(url == null)
			url = "/views/productList.do";
		return  url;
	}
}
