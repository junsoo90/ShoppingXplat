package custom.Action.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import custom.Action.command.CommandAction;
import custom.Dao.ProductDao;
import custom.VO.OptionVO;
import custom.VO.PhotoVO;
import custom.VO.ProductVO;

public class ProductDetailAction implements CommandAction {
	
	ProductDao productDao = new ProductDao();
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		System.out.println("------------- detail request pro --------------");
		
		
		String command = request.getRequestURI();
		if (command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		}
		System.out.println(request.getParameter("seq"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		String param = "?seq=" + seq;
		System.out.println("command = " + command + param);
		request.getSession().setAttribute("url", "/views/detail.do"+ param);
		
		
		
		ProductVO vo = productDao.getInfo(seq);
		
		if(vo == null || vo.getProductState().equals("delete"))
			return "/views/productDeleteInfo.jsp";
		
		List<OptionVO> optionlist = productDao.getOptionInfo(seq);
		List<PhotoVO> photolist = productDao.photoGetInfo(seq);
		
		request.setAttribute("vo", vo);
		request.setAttribute("optionlist", optionlist);
		request.setAttribute("photolist", photolist);
		
		return "/views/productDetail.jsp";
	}
}
