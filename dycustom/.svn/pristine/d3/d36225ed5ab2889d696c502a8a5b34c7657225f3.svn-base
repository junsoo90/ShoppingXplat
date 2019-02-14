package custom.Action.order;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import custom.Action.command.CommandAction;
import custom.Dao.OrderDao;
import custom.VO.OrderVO;

public class OrderStateAction implements CommandAction{
	OrderDao orderDao = new OrderDao();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if(request.getSession().getAttribute("custom") == null) {
			request.getSession().setAttribute("url", "/views/productList.do");
			return "login.do";
		}
		
		
		if(request.getRequestURI().equals("/dycustom/views/deliveryConfirm.ajax"))
			deliveryConfirm(request,response);
			
		else if(request.getRequestURI().equals("/dycustom/views/refundReqeust.ajax"))
			refundReqeust(request,response);
			
		else if(request.getRequestURI().equals("/dycustom/views/refundCancel.ajax"))
			refundCalcel(request,response);
		
		
		return null;
		
	}
	
	public void deliveryConfirm(
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int purchaseSeq = Integer.parseInt(request.getParameter("purchaseSeq"));
		String pOption = request.getParameter("pOption");
		
		System.out.println(purchaseSeq);
		orderDao.updateStateCheck(purchaseSeq);
		
		response.getWriter().print(true);
	}
	
	public void refundReqeust(
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		int purchaseSeq = Integer.parseInt(request.getParameter("purchaseSeq"));
		
		OrderVO vo = new OrderVO();
		vo.setPurchaseSeq(purchaseSeq);
		
		System.out.println(purchaseSeq);
		
		String orderState = orderDao.orderStateCheck(vo);
		System.out.println("orderState = " + orderState);
		
		if(orderState.equals("환불완료")) {			
			response.getWriter().print(false);
		}
		
		else {
			orderDao.updateRefundReqeust(purchaseSeq);
			response.getWriter().print(true);
		}
	}
	
	public void refundCalcel(
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int purchaseSeq = Integer.parseInt(request.getParameter("purchaseSeq"));
		String pOption = request.getParameter("pOption");
		
		OrderVO vo = new OrderVO();
		vo.setPurchaseSeq(purchaseSeq);
		vo.setpOption(pOption);
		
		System.out.println(purchaseSeq);
		System.out.println(pOption);
		
		
		String orderState = orderDao.orderStateCheck(vo);
		System.out.println("orderState = " + orderState);
		if(orderState.equals("환불완료")) {
			
			response.getWriter().print(false);
		}
		else {
			orderDao.updateRefundCancel(purchaseSeq);
			response.getWriter().print(true);
		}
	}
	
	
}
