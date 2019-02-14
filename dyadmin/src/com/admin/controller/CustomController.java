package com.admin.controller;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.VO.CustomVO;
import com.admin.dao.CustomDao;
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
@RequestMapping(value = "/custom")
public class CustomController {


	private int pageSize = 10;
	private int blockCount = 10;

	@Autowired
	private CustomDao customDao;
	 
	@RequestMapping({ "/list.do" })
	public void list(
			@RequestParam(value = "pageNum", defaultValue = "") int currentPage,
			@RequestParam(value = "keyField", defaultValue = "") String keyField,
			@RequestParam(value = "keyWord", defaultValue = "") String keyWord, 
			HttpServletRequest request,
			HttpServletResponse response) throws PlatformException, UnsupportedEncodingException {

		System.out.println("/list in");
		// Xplatform basic object creation
		PlatformData pdata = new PlatformData();

		int nErrorCode = 0;
		String strErrorMsg = "START";

		//keyWord = new String(request.getParameter("keyWord").getBytes("iso8859-1"), "UTF-8");

		try {
			System.out.println("HttpPlatformRequest in");
			HttpPlatformRequest req = new HttpPlatformRequest(request);

			req.receiveData();
			pdata = req.getData();

			DataSet pds = new DataSet("customDataset");

			pds.addColumn("cId", DataTypes.STRING, 4);
			pds.addColumn("cName", DataTypes.STRING, 300);
			pds.addColumn("pAddCode", DataTypes.STRING, 10);
			pds.addColumn("pAdd", DataTypes.STRING, 50);
			pds.addColumn("pAddDetail", DataTypes.STRING, 50);
			pds.addColumn("cSeq", DataTypes.INT, 50);
		
			if (keyWord.toString().equals("undefined"))
				keyWord = "";
			 
			System.out.println("keyField = " + keyField);
			System.out.println("KeyWord = " + keyWord);

			HashMap<String, Object> map = new HashMap();

			map.put("keyField", keyField);
			map.put("keyWord", keyWord);

			int count = 0;
			
			
//			if (keyField.toString().equals("orgfilename")) {
//				System.out.println("filecount");
//				count = productDao.getfileCount(map);
//			} else {
//				System.out.println("boardcount");
//				count = productDao.getCount(map);
//			}

		
			count = customDao.getCustomCount(map);
			
			System.out.println("count = " + count);
			System.out.println("currentPage = " + currentPage);
			
			
			Paging page = new Paging(keyField, keyWord, currentPage, count, this.pageSize, this.blockCount, "list.do");

			map.put("start", Integer.valueOf(page.getStartCount()));
			map.put("end", Integer.valueOf(page.getEndCount()));
			int number = count - (currentPage - 1) * this.pageSize;
			int endPage = (count / pageSize) + ((count % pageSize == 0) ? 0 : 1);
			
			
			List<CustomVO> list = customDao.list(map);
			
			System.out.println("list.size() = " + list.size());
			// int number = count - (currentPage - 1) * this.pageSize;
			// public List<BoardVO> list(Map<String, Object> map)
	
			
			CustomVO pvo = new CustomVO();

			for (int i = 0; i < list.size(); i++) {

				int row = pds.newRow(); // new Row feed

				pvo = list.get(i);
				pds.set(row, "cId", pvo.getcId());
				pds.set(row, "cName", pvo.getcName());
				pds.set(row, "pAddCode", pvo.getcAddCode());
				pds.set(row, "pAdd", pvo.getcAdd());
				pds.set(row, "pAddDetail", pvo.getcAddDetail());
				pds.set(row, "cSeq", pvo.getcSeq());
				
			} // for

			pdata.addDataSet(pds);

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

	@RequestMapping(value = "/detail.do")
	public void detail(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws PlatformException {
		Enumeration e = session.getAttributeNames();

		System.out.println(e);
		while (e.hasMoreElements()) {

			String attributeName = (String) e.nextElement();
			System.out.println(attributeName);
			//String attributeValue = (String) session.getAttribute(attributeName);
		}
		
		PlatformData pdata = new PlatformData();

		int nErrorCode = 0;
		String strErrorMsg = "START";

		try {
			System.out.println("HttpPlatformRequest in");
			HttpPlatformRequest req = new HttpPlatformRequest(request);

			req.receiveData();
			pdata = req.getData();

			int cSeq = Integer.parseInt(request.getParameter("cSeq"));
			System.out.println("cSeq = " + cSeq);

			DataSet pds = new DataSet("customDataSet");
		
			pds.addColumn("cId", DataTypes.STRING, 256);
			pds.addColumn("cName", DataTypes.STRING, 256);
			pds.addColumn("cPass", DataTypes.STRING, 256);
			pds.addColumn("cAddCode", DataTypes.STRING, 256);
			pds.addColumn("cAdd", DataTypes.STRING, 256);
			pds.addColumn("cAddDetail", DataTypes.STRING, 256);
			pds.addColumn("cPhone", DataTypes.STRING, 256);
			pds.addColumn("cSeq", DataTypes.INT, 256);
			
			int row = pds.newRow();
			
			CustomVO vo = customDao.getCustomInfo(cSeq);
			System.out.println(vo.getcId());
			String cPhone = vo.getcPhone1() + " - " 
					+ vo.getcPhone2() + " - "
					+ vo.getcPhone3();
			
			pds.set(row, "cId", vo.getcId());
			pds.set(row, "cName", vo.getcName());
			pds.set(row, "cPass", vo.getcPass());
			pds.set(row, "cAddCode", vo.getcAddCode());
			pds.set(row, "cAddDetail", vo.getcAddDetail());
			pds.set(row, "cAdd", vo.getcAdd());
			pds.set(row, "cPhone", cPhone);
			
			pds.set(row, "cSeq", vo.getcSeq());
			
		
			// productDao.addHit(pSeq);
			pdata.addDataSet(pds);
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

	
	@RequestMapping(value = "/delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws PlatformException {
		System.out.println("delete-------");
		PlatformData pdata = new PlatformData();

		int nErrorCode = 0;
		String strErrorMsg = "START";

		try {
			System.out.println("HttpPlatformRequest in");
			HttpPlatformRequest req = new HttpPlatformRequest(request);

			req.receiveData();
			pdata = req.getData();

			
			String cId = request.getParameter("cId");
			System.out.println("cId = " + cId);
			
			customDao.delete(cId);
			
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
	
}
