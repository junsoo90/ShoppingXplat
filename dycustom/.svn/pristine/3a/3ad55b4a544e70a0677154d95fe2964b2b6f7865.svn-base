package custom.Action.order;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import custom.Action.command.CommandAction;
import custom.Dao.OrderDao;
import custom.Dao.PurchaseDao;
import custom.VO.CustomVO;

public class OrderAction implements CommandAction{
	
	OrderDao orderDao = new OrderDao();
	PurchaseDao purchaseDao = new PurchaseDao();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if(request.getSession().getAttribute("custom") == null) {
			request.getSession().setAttribute("url", "/views/productList.do");
			return "login.do";
		}
		
		System.out.println("================ orderPay ================");
		
		if(request.getRequestURI().equals("/dycustom/views/orderPay.do"))
			return orderPay(request, response);
		
		return null;
	}
	
	public String orderPay( 
			HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("order");
		String url = null;
		
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		
		try {
		
			List<Map<String,Object>> purchaseList =  (List<Map<String, Object>>) session.getAttribute("purchaseList");
			// 援щℓ 由ъ뒪�듃
			if(purchaseList == null) {
				return "/views/productList.do";
			}
			System.out.println("purchaseList = " + purchaseList.size());
			
			CustomVO customVo = (CustomVO) session.getAttribute("custom");
			// �쑀�� �젙蹂�
			System.out.println("cartList = " + session.getAttribute("cartList"));
			
			System.out.println("customVO = " + customVo);
			
			
			String cId = customVo.getcId(); // �쑀�� �븘�씠�뵒
			String orderState = "배송준비중"; // �긽�깭
			
			
			String reqeustSelectBox = request.getParameter("reqeustSelectBox");
			String delRequestMsg = request.getParameter("delRequestMsg");
			System.out.println(delRequestMsg);
			
			// delRequestMsg = new String(delRequestMsg.getBytes("8859_1"), "utf-8");

			System.out.println(delRequestMsg);
			// 諛곗넚�떆 �슂泥��궗�빆
			
			String payType = request.getParameter("payType"); // 寃곗젣諛⑹떇
			String payName; // 移대뱶 or ���뻾 �씠由�
			
			int bNo = 0; // ���뻾 �꽑�깮 �떆 怨꾩쥖踰덊샇
			
			if(payType.equals("bank")) {
				bNo = Integer.parseInt(request.getParameter("bankNum"));
				payName = request.getParameter("payTypeBankSelectBox");
			}
			else
				payName = request.getParameter("payTypeCardSelectBox");
			
			System.out.println("payName = " + payName);
			// 二쇰Ц�궡�뿭
			for(int i=0; i<purchaseList.size(); i++){
			
				// 移대뱶�씤吏� �븘�땶吏�
				if(purchaseList.get(i).get("cartCnt") == null)
					purchaseList.get(i).put("cartCnt", 0);
				
				
				purchaseList.get(i).put("cId", cId);
				purchaseList.get(i).put("orderState", orderState);
				purchaseList.get(i).put("delRequestMsg", delRequestMsg);
				purchaseList.get(i).put("payName", payName);
				purchaseList.get(i).put("payType", payType);
				
				// 二쇰Ц�궡�뿭 異붽�
				orderDao.insertOrder(purchaseList.get(i));
				System.out.println("결제방법 ㄱㄱ");
				
				int maxSeq = orderDao.getMax();
				purchaseList.get(i).put("maxSeq", maxSeq);
				// 移대뱶 / ���뻾
				if(bNo != 0) {
					purchaseList.get(i).put("bNo", bNo);
					orderDao.insertBank(purchaseList.get(i));
				}
				else
					orderDao.insertCard(purchaseList.get(i));
				
				
				// �옣諛붽뎄�땲�뿉�꽌 二쇰Ц�궡�뿭 �궘�젣
				if(session.getAttribute("cartPurchaseList") != null) {
					System.out.println(purchaseList.get(i).get("cartCnt"));
					System.out.println(cId);
					System.out.println(purchaseList.get(i).get("cId"));
					
					purchaseDao.cartDelete(purchaseList.get(i));
				}
				
				
				// 二쇰Ц �닔�웾留뚰겮 �옱怨� 媛먯냼
				orderDao.stockDec(purchaseList.get(i));
				
			}
			if(session.getAttribute("cartPurchaseList") != null)
				session.removeAttribute("cartPurchaseList");
			
			System.out.println(request.getParameter("reqeustSelectBox"));
	
			System.out.println(request.getParameter("payType"));
			System.out.println(request.getParameter("delRequestMsg"));
					
			System.out.println(request.getParameter("bankNum"));
			
			session.removeAttribute("cartList");
			session.removeAttribute("purchaseList");
			//mav.setViewName("orderResult");
			url = "orderResult";
		}catch(Exception e) {
			e.printStackTrace();
			//mav.setViewName("login");
			return "/views/productList.do";
		}
		return url+".jsp";
	}
}

