package custom.Action.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import custom.Action.command.CommandAction;
import custom.Dao.CustomDao;
import custom.VO.CustomVO;
import net.sf.json.JSONArray;


public class PurchaseAction implements CommandAction {
	private static JSONObject result = new JSONObject();
	
	CustomDao customDao = new CustomDao();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if(request.getSession().getAttribute("custom") == null) {
			request.getSession().setAttribute("url", "/views/productList.do");
			return "login.do";
		}
		
		System.out.println("----------- purchaseAction -------------");
	
		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    System.out.println("name : " + name + " // value : " + request.getParameter(name));
		}
		
		System.out.println("------------------------");
		
		System.out.println(request.getParameter("submitData"));
		System.out.println("requestURI = " + request.getRequestURI());
		if(request.getRequestURI().equals("/dycustom/views/purchaseCheck.ajax")) {
			purchaseCheck(request, response);
		}
		
		
		if(request.getRequestURI().equals("/dycustom/views/purchaseClick.do") ||
				request.getRequestURI().equals("/dycustom/views/purchaseClick.ajax")) {
			return purchaseClick(request, response);
		}
		if( request.getRequestURI().equals("/dycustom/views/purchase.do")) {
			return purchaseDo(request, response);
		}
		
		response.setContentType("application/json; charset=utf8");
		return null;
	}
	
	public String purchaseDo(HttpServletRequest request, HttpServletResponse response) {
		CustomVO sessionCustomVo = (CustomVO) request.getSession().getAttribute("custom");
		CustomVO customVo = customDao.customInfo(sessionCustomVo.getcId());
		request.getSession().setAttribute("custom", customVo);
		return "purchase.jsp";
	}
	
	
	public void purchaseCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Boolean bol = true;
		
		if(session.getAttribute("custom") == null) {
			session.setAttribute("purchaseCheck", "purchaseCheck");
			System.out.println("세션없당");
			
			bol = false;
		}
		String jsonStr = request.getParameter("submitData");
		List<Map<String,Object>> purchaseList = new ArrayList<Map<String,Object>>();
		purchaseList = JSONArray.fromObject(jsonStr);
		
		session.setAttribute("purchaseList", purchaseList);
		response.getWriter().print(bol);
	    
	}
	
	
	public String purchaseClick(
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		
		String url = null;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
	
			System.out.println("========== purchase click ==========");
			
			List<Map<String,Object>> purchaseList;
			int totalPrice = 0;
			
			// cart purchase
			if(session.getAttribute("purchaseList") == null) {
				System.out.println("cart에서 누른거야");
				
				String jsonStr = request.getParameter("submitData");
				purchaseList = JSONArray.fromObject(jsonStr);
				
				if(purchaseList == null) {
					return "/views/productList.do";
				}
				
				System.out.println("purchaseList = " + purchaseList);
				//purchaseList = JSONArray.fromObject(purchaseInfo);
				
				
				System.out.println(purchaseList.get(0).get("savefilename"));
				//mav.addObject("cartPurchaseList", purchaseList);				
				result.put("cartPurchaseList", purchaseList);
				
				session.setAttribute("cartPurchaseList", purchaseList);
				session.setAttribute("purchaseList", purchaseList);
				//mav.addObject("url","purchase");
				//result.put("url","purchase");
				
				url = "purchase";
				
				//mav.setViewName("jsonView");
			}
			// detail purchase
			else {
				System.out.println("Detail에서 누른거야");
				purchaseList = (List<Map<String, Object>>) session.getAttribute("purchaseList");
				
				if(purchaseList == null) {
					return "/views/productList.do";
				}
				
				System.out.println("purchaseList.size() = " + purchaseList.size());
				System.out.println("savefilename = " + purchaseList.get(0).get("savefilename"));
				//mav.setViewName("purchase");
				url = "purchase";
			}
			
			
			totalPrice(request, response, totalPrice,purchaseList );
			
		}catch(Exception e) {
			e.printStackTrace();
			//mav.setViewName("login");
			//url = "login";
		}
		
		//return mav;
		response.getWriter().print(result);
		System.out.println("url = " + url);
		return url+".jsp";
	}
	
	
	public void totalPrice(HttpServletRequest request, HttpServletResponse response
			, int totalPrice, List<Map<String,Object>> purchaseList) {


		// totalPrice
		
		for (Map<String, Object> map : purchaseList) {
			System.out.println(map.get("pName") + " , " + map.get("pOption"));
			int cnt = Integer.parseInt(String.valueOf(map.get("optionCnt")));
			int optionPrice = Integer.parseInt(String.valueOf(map.get("optionPrice")));
			
			totalPrice += totalPriceResult(cnt, optionPrice);			
		}
		
		for(int i=0; i<purchaseList.size(); i++) {
			Map<String,Object> optionMap = new HashMap<String,Object>();
			optionMap.put("pSeq", String.valueOf(purchaseList.get(i).get("pSeq")));
			optionMap.put("pOption", String.valueOf(purchaseList.get(i).get("pOption")));
			
			int purchaseStock = Integer.parseInt(String.valueOf(purchaseList.get(i).get("optionCnt")));
			
			//int optionStock = productDao.checkStock(optionMap);
			//System.out.println("optionStock = " + optionStock);
			System.out.println("purchaseStock = " + purchaseStock);
			System.out.println("deliveryCharge = " + purchaseList.get(i).get("deliveryCharge"));
			
		}
		
		// userInfo
		CustomVO sessionCustomVo = (CustomVO) request.getSession().getAttribute("custom");
		
		CustomVO customVo = customDao.customInfo(sessionCustomVo.getcId());
		
		System.out.println(customVo.getcName());
		System.out.println(customVo.getcId());
		System.out.println(customVo.getcAdd());
		System.out.println(customVo.getcAddCode());
		System.out.println(customVo.getcAddDetail());
		
		int deliveryCharge = 0;
		int orderPrice = 0;
		orderPrice = totalPrice;
		System.out.println("totalPrice = " + totalPrice);
		
		System.out.println("deliveryCharge = " + purchaseList.get(0).get("deliveryCharge") );
		
		if(totalPrice < 50000) {
			deliveryCharge = Integer.parseInt(String.valueOf(purchaseList.get(0).get("deliveryCharge")));				
			totalPrice += deliveryCharge;
		}
		request.getSession().setAttribute("orderPrice", orderPrice);
		request.getSession().setAttribute("deliveryCharge", deliveryCharge);
		request.getSession().setAttribute("purchaseListSize", purchaseList.size());
		request.getSession().setAttribute("totalPrice", totalPrice);
		
		request.setAttribute("custom", customVo);
		
		System.out.println("savefilename = " + purchaseList.get(0).get("savefilename"));
		
	}
	
	
	
	public int totalPriceResult(int cnt, int price) {
		return cnt * price;
	}
}
