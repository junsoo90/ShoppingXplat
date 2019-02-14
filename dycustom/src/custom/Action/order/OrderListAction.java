package custom.Action.order;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import custom.Action.Paging.OrderPaging;
import custom.Action.command.CommandAction;
import custom.Dao.OrderDao;
import custom.VO.CustomVO;
import custom.VO.OrderVO;

public class OrderListAction implements CommandAction{
	OrderDao orderDao = new OrderDao();
	int pageSize = 10;
	int blockCount = 10;
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		return orderList(request, response);
	}
	
	public String orderList(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession().getAttribute("custom") == null) {
			request.getSession().setAttribute("url", "/views/productList.do");
			return "login.do";
		}
		
		Enumeration params = request.getParameterNames();
		System.out.println("----------------------------");
		
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    System.out.println(name + " : " +request.getParameter(name));
		}
		
		System.out.println("----------------------------");

		HttpSession session = request.getSession();
		String url;
		CustomVO customVo = (CustomVO) session.getAttribute("custom");
		
		try {
			String cId = customVo.getcId();
			
			String pagingHtml = "";
			HashMap<String, Object> map = new HashMap();
			
			String orderKeyWord = "";
			String keyField = "";
			System.out.println(request.getParameter("orderKeyWord"));
			
			if(request.getParameter("orderKeyWord") != null && request.getParameter("orderKeyWord") != "") {
				orderKeyWord = request.getParameter("orderKeyWord");
				orderKeyWord = URLDecoder.decode(orderKeyWord,"UTF-8");
			}
			
			int currentPage;
			
			if(request.getParameter("pageNum") == null || request.getParameter("pageNum") == "")
				currentPage = 1;
			else
				currentPage = Integer.parseInt(request.getParameter("pageNum"));
			
			
			
			map.put("orderKeyWord", orderKeyWord);
			map.put("cId", cId);
			
			String param = "?orderKeyWord=" + orderKeyWord +"&pageNum=" + currentPage;
			url = "/views/orderList.jsp"+param;
			
			System.out.println("orderKeyWord = " + orderKeyWord);
			System.out.println("orderKeyWord = " + URLEncoder.encode(orderKeyWord,"UTF-8"));
			System.out.println("orderKeyWord = " + URLDecoder.decode(orderKeyWord,"UTF-8"));
			
			int count = orderDao.orderCount(map);
			
			System.out.println("count = " + count);
			OrderPaging page = new OrderPaging(keyField, URLEncoder.encode(orderKeyWord,"UTF-8"), currentPage, count, 
							this.pageSize, this.blockCount, "orderList.do");
	
			pagingHtml = page.getPagingHtml().toString();
	
			map.put("start", Integer.valueOf(page.getStartCount()));
			map.put("end", Integer.valueOf(page.getEndCount()));
	
			int number = count - (currentPage - 1) * this.pageSize;
			int endPage = (count / pageSize) + ((count % pageSize == 0) ? 0 : 1);
	
			
			List<OrderVO> orderList = orderDao.orderListInfo(map);
			
			
			
			//mav.addObject("count", Integer.valueOf(count));
			//mav.addObject("currentPage", Integer.valueOf(currentPage));
			//mav.addObject("pagingHtml", pagingHtml);
			//mav.addObject("number", Integer.valueOf(number));
			//mav.addObject("endPage", endPage);
			//mav.addObject("keyField",keyField);

			request.setAttribute("count", Integer.valueOf(count));
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pagingHtml", pagingHtml);
			request.setAttribute("number", Integer.valueOf(number));
			request.setAttribute("endPage", endPage);
			System.out.println("endPage = " + endPage);
			
			//mav.addObject("orderKeyWord", URLEncoder.encode(orderKeyWord,"UTF-8"));
			request.setAttribute("orderKeyWord", URLEncoder.encode(orderKeyWord,"UTF-8"));
			
			//mav.addObject("orderList", orderList);
			request.setAttribute("orderList", orderList);
			
		
		}
		catch(Exception e) {
			e.printStackTrace();
			url = "/views/productEmpty.jsp";
		}
		
		return url;
	}
}
