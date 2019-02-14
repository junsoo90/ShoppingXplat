package custom.Action.product;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import custom.Action.Paging.Paging;
import custom.Action.command.CommandAction;
import custom.Dao.CustomDao;
import custom.Dao.ProductDao;
import custom.VO.ProductVO;

public class ProductListAction implements CommandAction {
	
	int pageSize = 10;
	int blockCount = 10;
	
	ProductDao productDao = new ProductDao();
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println("requestPro");
		HttpSession session = request.getSession();
		
		String command = request.getRequestURI();
		if (command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		}
		request.getSession().setAttribute("url",command);
		
		// TODO Auto-generated method stub
		
		String keyWord = "";
		String keyField = "";
		System.out.println(request.getParameter("keyWord"));
		if(request.getParameter("keyWord") != null && request.getParameter("keyWord") != "") {
			keyWord = request.getParameter("keyWord");
			keyWord = URLDecoder.decode(keyWord, "UTF-8");
			//new String(request.getParameter("keyWord").getBytes("iso8859-1"),
			 //"UTF-8");
			//URLDecoder.decode(keyWord, "UTF-8") 
		}
		int currentPage;	
		
		if(request.getParameter("pageNum") == null || request.getParameter("pageNum") == "")
			currentPage = 1;
		else
			currentPage = Integer.parseInt(request.getParameter("pageNum"));
			
		
		String pagingHtml = "";
		System.out.println("============ productList.do =============");
		HashMap<String, Object> map = new HashMap();
		
		;
	
		String param = "?keyWord=" + keyWord +"&pageNum=" + currentPage;
		
		session.setAttribute("keyWord", keyWord);
		session.setAttribute("pageNum", currentPage);
		
		session.setAttribute("url","/views/productList.do"+param);
		
		map.put("keyField", keyField);
		map.put("keyWord", keyWord);
		
		
		int count = productDao.getCount(map);
		System.out.println("count = " + count);
		
		Paging page = new Paging(keyField, keyWord, currentPage, count, 
						this.pageSize, this.blockCount, "productList.do");

		pagingHtml = page.getPagingHtml().toString();

		map.put("start", Integer.valueOf(page.getStartCount()));
		map.put("end", Integer.valueOf(page.getEndCount()));

		
		List<ProductVO> list = productDao.productList(map);
		
		int number = count - (currentPage - 1) * this.pageSize;
		int endPage = (count / pageSize) + ((count % pageSize == 0) ? 0 : 1);

		request.setAttribute("list", list);
		
		request.setAttribute("count", count);
		request.setAttribute("currentPage", Integer.valueOf(currentPage));
		request.setAttribute("pagingHtml", pagingHtml);
		request.setAttribute("number", number);
		request.setAttribute("endPage", endPage);
		request.setAttribute("keyWord", keyWord);
		
		session.removeAttribute("purchaseList");
		
		return "/views/productList.jsp";//해당뷰
	}
}
