package custom.Action.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import custom.Action.command.CommandAction;
import custom.Dao.ProductDao;
import custom.Dao.PurchaseDao;
import custom.VO.CartVO;
import custom.VO.CustomVO;
import net.sf.json.JSONArray;

public class CartAction implements CommandAction {
	
	private static JSONObject result = new JSONObject();
	ProductDao productDao = new ProductDao();
	PurchaseDao purchaseDao = new PurchaseDao();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if(request.getSession().getAttribute("custom") == null) {
			request.getSession().setAttribute("url", "/views/productList.do");
			return "login.do";
		}
		
		System.out.println("======  CartAction =====");

		System.out.println("request.getRequestURI() = " + request.getRequestURI());
		if(request.getRequestURI().equals("/dycustom/views/cartInCheck.ajax")) {
			cartInCheck(request,response);
			
			return null;
		}
		else if(request.getRequestURI().equals("/dycustom/views/cartDelete.ajax")) {
			cartDelete(request,response);
			
			return null;
		}
		else if(request.getRequestURI().equals("/dycustom/views/cartIn.do"))
			return cartIn(request, response);
		
		else if(request.getRequestURI().equals("/dycustom/views/cart.do"))
			return cartDo(request, response);
		
		return null;
		
	}

	public void cartDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String jsonStr = request.getParameter("submitData");
		List<Map<String,Object>> cartDeleteList = new ArrayList<Map<String,Object>>();
		cartDeleteList = JSONArray.fromObject(jsonStr);
		
		CustomVO customVo = (CustomVO)request.getSession().getAttribute("custom");
		String userId = customVo.getcId();
		
		for(Map deleteMap : cartDeleteList) {
			deleteMap.put("cId", userId);			
			System.out.println(deleteMap.get("optionStock"));
			int cartCnt = Integer.parseInt(String.valueOf(deleteMap.get("cartCnt")));
			System.out.println("carCnt = " + cartCnt);
			purchaseDao.cartDelete(deleteMap);	
		}
		
	}
	
	public void cartInCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		String res = "true";
		if (session.getAttribute("custom") == null) {
			System.out.println("세션없당");
			session.setAttribute("cartInCheck", "cartInCheck");
			result.put("result", "false");
			res = "false";
		}
		System.out.println("세션있당");

		List<Map<String, Object>> cartList = new ArrayList<Map<String, Object>>();
		String jsonStr = request.getParameter("submitData");
		cartList = JSONArray.fromObject(jsonStr);
		
		CustomVO customVo = (CustomVO) session.getAttribute("custom");
		System.out.println("customVo.getcId() = " + customVo.getcId() );
		
		List<CartVO> getCartList = purchaseDao.getCart(customVo.getcId());

		for (int i = 0; i < getCartList.size(); i++) {
			for (int j = 0; j < cartList.size(); j++) {
				System.out.println("장바구니에 있는거 = " + getCartList.get(i).getpOption());
				System.out.println("이제 넣을꺼 = " + cartList.get(j).get("pOption"));
				if (getCartList.get(i).getpName().equals(cartList.get(j).get("pName"))
						&& getCartList.get(i).getpOption().equals(cartList.get(j).get("pOption"))) {
					result.put("result", "exist");
					res = "exist";
					response.getWriter().print(res);
					
					return;
				}
			}
		}
		request.getSession().setAttribute("cartList", cartList);
		
		result.put("result", "true");
		response.getWriter().print(res);
	}


	public String cartIn(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		List<Map<String,Object>> list = (List<Map<String, Object>>) session.getAttribute("cartList");
		String url = null;
		System.out.println("session.getAttribute(\"cartList\") = " + session.getAttribute("cartList"));
		CustomVO customVo = (CustomVO)session.getAttribute("custom");
		
		try {
			String userId = customVo.getcId();
			
			int cartCnt = purchaseDao.getMaxCnt(userId);
			// 장바구니 담기
			session.removeAttribute("purchaseList");
			//int cartCount = 0;
			int totalPrice = 0;
			
			for (Map<String, Object> map : list) {
				
				int pSeq = Integer.parseInt((String) map.get("pSeq"));
				String pName = (String) map.get("pName");
				String savefilename = (String) map.get("savefilename");
				String pOption = (String) map.get("pOption");
				int optionPrice = Integer.parseInt((String) map.get("optionPrice"));
				int optionCnt = (Integer) map.get("optionCnt");
				int optionStock = Integer.parseInt((String) map.get("optionStock"));
				int deliveryCharge = Integer.parseInt((String) map.get("deliveryCharge"));
				
				System.out.println(pName);
				System.out.println(userId);
				System.out.println(savefilename);
				System.out.println(pOption);
				System.out.println(optionPrice);
				System.out.println(optionCnt);
				
			
				CartVO cartvo = new CartVO(++cartCnt, userId, pSeq, pName, savefilename,
						pOption, optionPrice, optionCnt, optionStock);
				cartvo.setDeliveryCharge(deliveryCharge);
				
				purchaseDao.cartIn(cartvo);
				totalPrice += optionPrice * optionCnt;
		    }
			System.out.println("totalPrice = " + totalPrice);
			//mav.addObject("cartTotalPrice",totalPrice);
			
			request.setAttribute("cartTotalPrice", totalPrice);
			List<CartVO> cartList = purchaseDao.getCart(userId);
			
			request.setAttribute("cartList", cartList);
			//mav.addObject("cartList",cartList);
			
			session.removeAttribute("cartList");
			//mav.setViewName("cart");
			url = "cart.do";
			response.setContentType("application/json; charset=utf8");
		}
		catch(Exception e) {
			e.printStackTrace();
			//mav.setViewName("login");
			url = "login.do";
		}
		
		return url;
	}
	
	
	
	public String cartDo(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String url = null;
		CustomVO customVo = (CustomVO)session.getAttribute("custom");
		if(customVo == null) {
			//mav.setViewName("login");
			url = "login.do";
			return url;
		}
		try {
			String userId = customVo.getcId();
			System.out.println("userId = " + userId);
			
			session.removeAttribute("purchaseList");
			List<CartVO> cartList = purchaseDao.getCart(userId);
			// session.setAttribute("cartList", cartList);
			//System.out.println("CartList[0].savefilename = " + cartList.get(0).getsavefilename());
			System.out.println("Cart size = " + cartList.size());
			
			int totalPrice = 0;
			
			for(int i=0; i<cartList.size(); i++) {
				
				totalPrice += totalPriceResult(cartList.get(i).getCartCnt(), cartList.get(i).getOptionPrice());
				System.out.println("optionStock = " + cartList.get(i).getOptionStock());
				System.out.println("productState = " + cartList.get(i).getProductState());
				cartList.get(i).setOptionStock(purchaseDao.getOptionStock(cartList.get(i)));
			}
			
			System.out.println("totalPrice = " + totalPrice);
			
//			mav.addObject("count", cartList.size());
//			mav.addObject("cartList", cartList);
//			mav.addObject("cartTotalPrice", totalPrice);
		
			request.setAttribute("count", cartList.size());
			request.setAttribute("cartList", cartList);
			request.setAttribute("cartTotalPrice", totalPrice);
			
			
			//mav.setViewName("cart");
			url = "cart.jsp";
		}catch(Exception e) {
			e.printStackTrace();
			//mav.setViewName("productList");
			url = "productList.do";
		}
		response.setContentType("application/json; charset=utf8");
		return url;
	}
	
	public int totalPriceResult(int cnt, int price) {
		return cnt * price;
	}
	
	
}
