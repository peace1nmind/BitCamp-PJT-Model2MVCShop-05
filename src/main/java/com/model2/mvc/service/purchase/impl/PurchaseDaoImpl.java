package com.model2.mvc.service.purchase.impl;
// W D 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {

	// Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// Constructor
	public PurchaseDaoImpl() {
	}

	@Override
	public Purchase selectPurchase(int tranNo) throws Exception {

		return sqlSession.selectOne("PurchaseMapper.selectPurchase", tranNo);
	}

	@Override
	public Purchase selectPurchaseByProd(int prodNo) throws Exception {

		return sqlSession.selectOne("PurchaseMapper.selectPurchaseByProd", new Integer(prodNo));
	}

	@Override
	public int insertPurchase(Purchase purchase) throws Exception {

		return sqlSession.insert("PurchaseMapper.insertPurchase", purchase);
	}

	@Override
	public List<Purchase> selectPurchaseList(Search search, String buyerId) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("buyerId", buyerId);
		
		return sqlSession.selectList("PurchaseMapper.selectPurchaseList", map);
	}

	@Override
	public int countPurchaseList(String buyerId) throws Exception {

		return sqlSession.selectOne("PurchaseMapper.countPurchaseList", buyerId);
	}

	@Override
	public List<Purchase> selectPurchaseHistoryList(Search search, String buyerId) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("buyerId", buyerId);
		
		return sqlSession.selectList("PurchaseMapper.selectPurchaseHistoryList", map);
	}

	@Override
	public int countPurchaseHistoryList(String buyerId) throws Exception {

		return sqlSession.selectOne("PurchaseMapper.countPurchaseHistoryList", buyerId);
	}

	@Override
	public List<Purchase> selectSaleList(Search search) throws Exception {

		return sqlSession.selectList("PurchaseMapper.selectSaleList", null, new RowBounds(search.getStartRowNum(), search.getEndRowNum()));
	}

	@Override
	public int countSaleList() throws Exception {

		return sqlSession.selectOne("PurchaseMapper.countSaleList");
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	@Override
	public void updateTranStatusCode(Purchase purchase) throws Exception {
		
		sqlSession.update("PurchaseMapper.updateTranStatusCode", purchase);
	}

}
// class end