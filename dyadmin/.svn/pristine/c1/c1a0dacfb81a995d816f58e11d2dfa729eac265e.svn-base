package com.admin.dao;

import java.util.List;
import java.util.Map;

import com.admin.VO.PhotoVO;
import com.admin.VO.OptionVO;
import com.admin.VO.PhotoVO;
import com.admin.VO.ProductVO;


public interface ProductDao {

	public abstract List<ProductVO> list(Map<String, Object> paramMap);

	public abstract int getProductCount(Map<String, Object> paramMap);
	
	public abstract String insert(ProductVO vo);
	
	public String optionInsert(OptionVO vo);
	
	public abstract ProductVO getInfo(int seq);
	
	public abstract int addHit(int seq);

	public int productDelete(int pSeq);
	
	public abstract int getMaxSeq();
	
	public abstract int updateOk(ProductVO vo);
	
	public abstract int stepUp(ProductVO vo);
	
	public abstract List<ProductVO> getBoard();
	
	public abstract int fileInsert(PhotoVO PhotoVO);
	
	public abstract List<PhotoVO> filegetInfo(int seq);

	public abstract int fileDelete(PhotoVO PhotoVO);
	
	public int getfileCount(Map<String, Object> map);

	public abstract int updateFilename(ProductVO vo);
	
	public List<OptionVO> getOptionInfo(int pSeq);
	
	public int getMaxOptionCnt(int pSeq);
		
	public void deleteOption(String pOption);
	
	public int getPhotoCnt(int pSeq);
	
	public int productCheck(String pName);
	
	public void producDeleteState(int pSeq);
	
	public void productCartDeleteState(int pSeq);
	
}
