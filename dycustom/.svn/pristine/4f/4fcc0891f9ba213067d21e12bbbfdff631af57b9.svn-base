package custom.Action.product;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import custom.Action.command.CommandAction;
import custom.Dao.ProductDao;
import net.sf.json.JSONArray;

public class StockCheckAction implements CommandAction{
	private static JSONObject result = new JSONObject();
	ProductDao productDao;
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		Boolean bol = true;
		if(request.getSession().getAttribute("custom") == null) {
			request.getSession().setAttribute("url", "/views/productList.do");
			return "login.do";
		}
		
		System.out.println("======== StockCheckAction ==========");
		HttpSession session = request.getSession();
		productDao = new ProductDao();
		
		Enumeration params = request.getParameterNames();
		System.out.println("----------------------------");
		
		while (params.hasMoreElements()){			
		    String name = (String)params.nextElement();
		    System.out.println(name + " : " + request.getParameter(name));
		}
		
		System.out.println("---------------------------");
		String jsonStr = request.getParameter("submitData");
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse( jsonStr );
		

		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
	
			System.out.println("========== stock Check ==========");
			List<Map<String,Object>> purchaseList;
			
			int totalPrice = 0;
			
			// cart purchase
			if(session.getAttribute("purchaseList") == null) {
				purchaseList = new ArrayList<Map<String,Object>>();
				purchaseList = JSONArray.fromObject(jsonStr);
				
			}
			// detail purchase
			else {
				purchaseList = (List<Map<String, Object>>) session.getAttribute("purchaseList");
				
			}
			System.out.println("======== purchaseList ===========");
			for(int i=0; i<purchaseList.size(); i++) {
				
				Map<String,Object> optionMap = new HashMap<String,Object>();
				optionMap.put("pSeq", String.valueOf(purchaseList.get(i).get("pSeq")));
				optionMap.put("pOption", String.valueOf(purchaseList.get(i).get("pOption")));
				
				String productState;
				
				
				int pSeq = Integer.parseInt(String.valueOf(purchaseList.get(i).get("pSeq")));
				System.out.println("pSeq = " + pSeq);
				productState = productDao.productStateCheck(pSeq);
			
				System.out.println("productState = " + productState);
				
				if(productState.equals("delete")) {
					bol = false;
					String stateMsg = purchaseList.get(i).get("pName") + " 은 더이상 판매하지 않는 제품입니다."
							+ " 확인 부탁드립니다.";
					System.out.println(stateMsg);
					
					result.put("msg", stateMsg);
					break;
				}
				
				int purchaseStock = Integer.parseInt(String.valueOf(purchaseList.get(i).get("optionCnt")));
				
				int optionStock = productDao.checkStock(optionMap);
				
				System.out.println("optionStock = " + optionStock);
				System.out.println("purchaseStock = " + purchaseStock);
				
				if(purchaseStock > optionStock) {
					
					String stockMsg = purchaseList.get(i).get("pName") + " 의 옵션 : " + purchaseList.get(i).get("pOption") 
							+ "의 재고가 부족합니다";
					System.out.println(stockMsg);
					
					result.put("msg", stockMsg);
					bol = false;
					break;
				}
			}
			
			System.out.println("result = " + bol);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		result.put("result", bol);
		response.getWriter().print(result);
		response.setContentType("application/json; charset=utf8");
		return null;
	}
}
