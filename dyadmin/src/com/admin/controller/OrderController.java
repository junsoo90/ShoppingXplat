package com.admin.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.VO.OrderVO;
import com.admin.dao.OrderDao;
import com.admin.paging.Paging;
import com.tobesoft.xplatform.data.DataSet;
import com.tobesoft.xplatform.data.DataTypes;
import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import com.tobesoft.xplatform.tx.HttpPlatformRequest;
import com.tobesoft.xplatform.tx.HttpPlatformResponse;
import com.tobesoft.xplatform.tx.PlatformException;
import com.tobesoft.xplatform.tx.PlatformType;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
	private int pageSize = 10;
	private int blockCount = 10;

	@Autowired
	OrderDao orderDao;

	// list
	@RequestMapping(value = "/list.do")
	public void list(@RequestParam(value = "pageNum", defaultValue = "") int currentPage,
			@RequestParam(value = "keyField", defaultValue = "") String keyField,
			@RequestParam(value = "keyWord", defaultValue = "") String keyWord, HttpServletRequest request,
			HttpServletResponse response) throws PlatformException, UnsupportedEncodingException {

		System.out.println("/list in");
		// Xplatform basic object creation
		PlatformData pdata = new PlatformData();

		int nErrorCode = 0;
		String strErrorMsg = "START";

		System.out.println("keyword = " + keyWord);
		//keyWord = new String(request.getParameter("keyWord").getBytes("iso8859-1"), "UTF-8");
	
		try {
			System.out.println("HttpPlatformRequest in");
			HttpPlatformRequest req = new HttpPlatformRequest(request);

			req.receiveData();
			pdata = req.getData();

			DataSet ords = new DataSet("orderDataset");

			ords.addColumn("purchaseSeq", DataTypes.INT, 10);
			ords.addColumn("cId", DataTypes.STRING, 50);
			ords.addColumn("pName", DataTypes.STRING, 300);
			ords.addColumn("pOption", DataTypes.STRING, 100);
			ords.addColumn("optionCnt", DataTypes.INT, 10);
			ords.addColumn("optionPrice", DataTypes.INT, 10);
			ords.addColumn("orderDate", DataTypes.DATE, 30);
			ords.addColumn("filelocation", DataTypes.STRING, 300);
			ords.addColumn("orderState", DataTypes.STRING, 300);

			if (keyWord.toString().equals("undefined"))
				keyWord = "";
			// DAO
			System.out.println("keyField = " + keyField);
			System.out.println("KeyWord = " + keyWord);

			HashMap<String, Object> map = new HashMap();

			map.put("keyField", keyField);
			map.put("keyWord", keyWord);

			int count = 0;
//		if (keyField.toString().equals("orgfilename")) {
//			System.out.println("filecount");
//			count = productDao.getfileCount(map);
//		} else {
//			System.out.println("boardcount");
//			count = productDao.getCount(map);
//		}
			
			count = orderDao.getCount(map);
			
			System.out.println("count = " + count);
			System.out.println("currentPage = " + currentPage);

			Paging page = new Paging(keyField, keyWord, currentPage, count, this.pageSize, this.blockCount, "list.do");

			map.put("start", Integer.valueOf(page.getStartCount()));
			map.put("end", Integer.valueOf(page.getEndCount()));
			int number = count - (currentPage - 1) * this.pageSize;
			int endPage = (count / pageSize) + ((count % pageSize == 0) ? 0 : 1);
			
			//List<OrderVO> list = orderDao.orderList();

			
			// public List<BoardVO> list(Map<String, Object> map)

			OrderVO orvo = new OrderVO();
			SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

			List<OrderVO> list = orderDao.orderList(map);
			
			System.out.println("list.size() = " + list.size());
			
			for (int i = 0; i < list.size(); i++) {

				int row = ords.newRow(); // new Row feed

				orvo = list.get(i);

				ords.set(row, "purchaseSeq", orvo.getPurchaseSeq());
				ords.set(row, "cId", orvo.getcId());
				ords.set(row, "pName", orvo.getpName());
				ords.set(row, "pOption", orvo.getpOption());
				ords.set(row, "optionCnt", orvo.getOptionCnt());
				ords.set(row, "optionPrice", orvo.getOptionPrice());
				ords.set(row, "orderDate", dt.parse(orvo.getTime()));
				ords.set(row, "orderState", orvo.getOrderState());

			} // for

			pdata.addDataSet(ords);

			// set the ErrorCode and ErrorMsg about success
			nErrorCode = endPage;
			strErrorMsg = "SUCC";
			strErrorMsg = String.valueOf(count);

		} catch (Throwable th) {
			// set the ErrorCode and ErrorMsg about fail
			nErrorCode = -1;
			strErrorMsg = th.getMessage();
			System.out.println("ERROR:" + strErrorMsg);
			th.printStackTrace();
		} // try

		// save the ErrorCode and ErrorMsg for sending Client
		VariableList varList = pdata.getVariableList();

		varList.add("ErrorCode", nErrorCode);
		varList.add("ErrorMsg", strErrorMsg);

		// send the result data(XML) to Client
		HttpPlatformResponse res = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");

		res.setData(pdata);
		res.sendData(); // Send Data
	}

	// detail
	@RequestMapping(value = "/detail.do")
	public void detail(HttpServletRequest request, HttpServletResponse response) throws PlatformException {

		PlatformData pdata = new PlatformData();

		int nErrorCode = 0;
		String strErrorMsg = "START";

		try {
			System.out.println("HttpPlatformRequest in");
			HttpPlatformRequest req = new HttpPlatformRequest(request);

			req.receiveData();
			pdata = req.getData();

			int purchaseSeq = Integer.parseInt(request.getParameter("purchaseSeq"));
			System.out.println("purchaseSeq = " + purchaseSeq);

			DataSet ords = new DataSet("orderDataset");
			DataSet fileds = new DataSet("fileDataSet");
			DataSet odsds = new DataSet("orderStateDataset");
			DataSet cusds = new DataSet("customDataset");
			
			ords.addColumn("purchaseSeq", DataTypes.INT, 256);
			ords.addColumn("pSeq", DataTypes.INT, 256);

			ords.addColumn("pName", DataTypes.STRING, 256);
			ords.addColumn("pOption", DataTypes.STRING, 256);
			ords.addColumn("optionCnt", DataTypes.INT, 5);
			ords.addColumn("optionPrice", DataTypes.INT, 10);
			ords.addColumn("orderDate", DataTypes.DATE, 50);
			ords.addColumn("filelocation", DataTypes.STRING, 256);
			ords.addColumn("payType", DataTypes.STRING, 50);
			ords.addColumn("delRequestMsg", DataTypes.STRING, 256);
			ords.addColumn("currentOrderState", DataTypes.STRING, 30);
			ords.addColumn("totalPrice", DataTypes.INT, 30);
			
			
			cusds.addColumn("cId", DataTypes.STRING, 30);
			cusds.addColumn("cName", DataTypes.STRING, 30);
			cusds.addColumn("cAdd", DataTypes.STRING, 256);

			odsds.addColumn("optionState", DataTypes.STRING, 50);

			fileds.addColumn("seq", DataTypes.STRING, 50);
			fileds.addColumn("filelocation", DataTypes.STRING, 50);

			String payType = orderDao.getPayType(purchaseSeq);

			OrderVO orderVo = new OrderVO(purchaseSeq, payType);
			System.out.println("payType = " + payType);
			HashMap<String, Object> map;

			if (payType.equals("card"))
				map = orderDao.orderInfoCard(orderVo);
				
			else {
				map = orderDao.orderInfoBank(orderVo);
			}
			//String photo = orderDao.orderPhotoInfo(purchaseSeq);
			
			// List<FileVO> filelist = OrderVO.filegetInfo(pSeq);
//			Iterator<String> keys = map.keySet().iterator();
//			while (keys.hasNext()) {
//				String key = keys.next();
//				System.out.println("방법3) key : " + key + " / value : " + map.get(key));
//			}

		
			int cusRow = cusds.newRow();
			int orderRow = ords.newRow();
			int fileRow = fileds.newRow();
			
			System.out.println(map.get("PNAME"));
			System.out.println(map.get("POPTION"));
			System.out.println(map.get("OPTIONCNT"));
			System.out.println(map.get("OPTIONPRICE"));
			System.out.println(map.get("ORDERDATE"));
			System.out.println("filelocation = " + map.get("FILELOCATION"));
			
			int totalPrice = Integer.parseInt(String.valueOf(map.get("OPTIONCNT")))
					* Integer.parseInt(String.valueOf(map.get("OPTIONPRICE")));

			ords.set(0, "purchaseSeq", purchaseSeq);
			ords.set(0, "pSeq", map.get("PSEQ"));
			ords.set(0, "pName", map.get("PNAME"));
			ords.set(0, "pOption", map.get("POPTION"));
			ords.set(0, "optionCnt", map.get("OPTIONCNT"));
			ords.set(0, "optionPrice", map.get("OPTIONPRICE"));
			ords.set(0, "payType", map.get("PAYTYPE"));
			ords.set(0, "filelocation", map.get("FILELOCATION"));


			ords.set(0, "delRequestMsg", map.get("DELREQUESTMSG"));			
			ords.set(0, "totalPrice", totalPrice);

			ords.set(0, "currentOrderState", map.get("ORDERSTATE"));
			
			cusds.set(cusRow, "cId", map.get("CID"));
			cusds.set(cusRow, "cName", map.get("CNAME"));

			fileds.set(fileRow, "filelocation", map.get("FILELOCATION"));
			
			
			StringBuffer sb = new StringBuffer();
			sb.append(map.get("CADDCODE"));
			sb.append(map.get("CADD"));
			sb.append(map.get("CADDDETAIL"));

			String cAdd = sb.toString();

			cusds.set(cusRow, "cAdd", cAdd);

			/*
			 * for (int i = 0; i < filelist.size(); i++) { int filerow = fileds.newRow();
			 * System.out.println(filelist.get(i).getSavefilename()); fileds.set(filerow,
			 * "filecnt", i + 1); fileds.set(filerow, "pSeq", filelist.get(i).getpSeq());
			 * fileds.set(filerow, "orgfilename", filelist.get(i).getOrgfilename());
			 * fileds.set(filerow, "savefilename", filelist.get(i).getSavefilename());
			 * fileds.set(filerow, "filelocation", filelist.get(i).getFilelocation()); }
			 */
			// productDao.addHit(pSeq);
			pdata.addDataSet(ords);
			pdata.addDataSet(fileds);
			pdata.addDataSet(odsds);
			pdata.addDataSet(cusds);

			// set the ErrorCode and ErrorMsg about success
			nErrorCode = 0;
			strErrorMsg = "SUCC";

		} catch (Throwable th) {
			// set the ErrorCode and ErrorMsg about fail
			nErrorCode = -1;
			strErrorMsg = th.getMessage();
			System.out.println("ERROR:" + strErrorMsg);
			th.printStackTrace();
		} // try

		// save the ErrorCode and ErrorMsg for sending Client
		VariableList varList = pdata.getVariableList();

		varList.add("ErrorCode", nErrorCode);
		varList.add("ErrorMsg", strErrorMsg);

		// send the result data(XML) to Client
		HttpPlatformResponse res = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");

		res.setData(pdata);
		res.sendData(); // Send Data
	}

	@RequestMapping(value = "/updateOrderState.do")
	public void updateOrderState(HttpServletRequest request, HttpServletResponse response)
			throws PlatformException, UnsupportedEncodingException {

		System.out.println("updateOrderState.do");

		PlatformData pdata = new PlatformData();

		int nErrorCode = 0;
		String strErrorMsg = "START";

		try {
			HttpPlatformRequest req = new HttpPlatformRequest(request);

			req.receiveData();
			pdata = req.getData();

			DataSet ords = pdata.getDataSet("orderStateDataset");

			request.setCharacterEncoding("utf-8");

			int orderStateIndex = Integer.parseInt(request.getParameter("orderStateIndex"));

			int purchaseSeq = Integer.parseInt(request.getParameter("purchaseSeq"));

			String orderState = ords.getString(orderStateIndex, "orderState");

			OrderVO orderVo = new OrderVO();
			orderVo.setPurchaseSeq(purchaseSeq);
			orderVo.setOrderState(orderState);

			orderDao.updateOrderState(orderVo);

		} catch (Throwable th) {
			// set the ErrorCode and ErrorMsg about fail
			nErrorCode = -1;
			strErrorMsg = th.getMessage();
			th.printStackTrace();
			System.out.println("ERROR:" + strErrorMsg);
		} // try

		// save the ErrorCode and ErrorMsg for sending Client
		VariableList varList = pdata.getVariableList();

		varList.add("ErrorCode", nErrorCode);
		varList.add("ErrorMsg", strErrorMsg);

		// send the result data(XML) to Client
		HttpPlatformResponse res = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
		res.setData(pdata);
		res.sendData(); // Send Data
	}

}
