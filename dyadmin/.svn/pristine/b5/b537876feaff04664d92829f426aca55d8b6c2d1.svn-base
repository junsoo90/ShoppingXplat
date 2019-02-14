package com.admin.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import com.admin.VO.OptionVO;
import com.admin.VO.PhotoVO;
import com.admin.VO.ProductVO;



@Component
public class ProductDaoImpl extends SqlSessionDaoSupport  implements ProductDao {

	
	@Override
	public List<ProductVO> list(Map<String, Object> paramMap) {
		return getSqlSession().selectList("productList",paramMap);
	}

	public int getProductCount(Map<String, Object> map) {
		return ((Integer) getSqlSession().selectOne("getProductCount", map)).intValue();
	}
	
	public int getfileCount(Map<String, Object> map) {
		return ((Integer) getSqlSession().selectOne("boardfileCount", map)).intValue();
	}
	
	public String insert(ProductVO vo){
		int n = getSqlSession().insert("productInsert",vo);
		if(n > 0)
			return String.valueOf(vo.getpSeq());
		else
			return "";
	}
	public String optionInsert(OptionVO vo){
		int n = getSqlSession().insert("optionInsert",vo);
		if(n > 0)
			return String.valueOf(vo.getpSeq());
		else
			return "";
	}
	
	public ProductVO getInfo(int pSeq){
		return getSqlSession().selectOne("getInfo",pSeq);
	}
	
	public int addHit(int seq){
		return getSqlSession().update("addHit",seq);
	}
	
	public int productDelete(int pSeq){
		return getSqlSession().delete("productDelete",pSeq);
	}
	
	public int fileDelete(PhotoVO PhotoVO) {
		return getSqlSession().delete("fileDelete",PhotoVO);
	}
	
	public int getMaxSeq(){
		System.out.println(getSqlSession().selectOne("getMaxSeq"));
		return getSqlSession().selectOne("getMaxSeq");
	}
	
	public int fileInsert(PhotoVO PhotoVO) {
		System.out.println(PhotoVO.getFilelocation());
		return getSqlSession().insert("fileInsert", PhotoVO);
	}
	
	public int updateFilename(ProductVO vo){
		return getSqlSession().update("updateFilename",vo);
	}
	
	public int updateOk(ProductVO vo){
		return getSqlSession().update("updateOk",vo);
	}
	
	public int stepUp(ProductVO vo){
		return getSqlSession().update("stepUp",vo);
	}
	
	public List<ProductVO> getBoard(){	
		return getSqlSession().selectList("getBoard");
	}
	
	public List<PhotoVO> filegetInfo(int seq){	
		return getSqlSession().selectList("filegetInfo", seq);
	}
	
	public List<OptionVO> getOptionInfo(int pSeq){
		return getSqlSession().selectList("optionInfo", pSeq);
	}
	
	public int getMaxOptionCnt(int pSeq){
		return getSqlSession().selectOne("getMaxOptionCnt", pSeq);
	}
	
	public void deleteOption(String pOption) {
		getSqlSession().delete("deleteOption", pOption);
	}
	
	public int getPhotoCnt(int pSeq) {
		return getSqlSession().selectOne("getPhotoCnt", pSeq);
	}
	
	public int productCheck(String pName) {
		return getSqlSession().selectOne("productCheck", pName);
	}
	
	public void producDeleteState(int pSeq) {
		getSqlSession().update("producDeleteState", pSeq);
	}
	
	public void productCartDeleteState(int pSeq) {
		getSqlSession().update("productCartDeleteState", pSeq);
	}
}
