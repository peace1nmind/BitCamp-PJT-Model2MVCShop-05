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
		Purchase purchase = sqlSession.selectOne("PurchaseMapper.selectPurchase", tranNo);
		purchase.setPurchaseProd(sqlSession.selectOne("ProductMapper.selectProduct", purchase.getPurchaseProd().getProdNo()));
		purchase.setBuyer(sqlSession.selectOne("UserMapper.getUser", purchase.getBuyer().getUserId()));
		
		return purchase;
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
		
		List<Purchase> list = sqlSession.selectList("PurchaseMapper.selectPurchaseList", map);
		
		for (Purchase purchase : list) {
			purchase.setPurchaseProd(sqlSession.selectOne("ProductMapper.selectProduct", purchase.getPurchaseProd().getProdNo()));
			purchase.setBuyer(sqlSession.selectOne("UserMapper.getUser", purchase.getBuyer().getUserId()));
			
			list.set(list.indexOf(purchase), purchase);
		}
		
		return list;
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
		
		List<Purchase> list = sqlSession.selectList("PurchaseMapper.selectPurchaseHistoryList", map);
		
		for (Purchase purchase : list) {
			purchase.setPurchaseProd(sqlSession.selectOne("ProductMapper.selectProduct", purchase.getPurchaseProd().getProdNo()));
			purchase.setBuyer(sqlSession.selectOne("UserMapper.getUser", purchase.getBuyer().getUserId()));
			
			list.set(list.indexOf(purchase), purchase);
		}
		
		return list;
	}

	@Override
	public int countPurchaseHistoryList(String buyerId) throws Exception {

		return sqlSession.selectOne("PurchaseMapper.countPurchaseHistoryList", buyerId);
	}

	@Override
	public List<Purchase> selectSaleList(Search search) throws Exception {
		
		List<Purchase> list = sqlSession.selectList("PurchaseMapper.selectSaleList", null, new RowBounds(search.getStartRowNum(), search.getEndRowNum()));
		
		for (Purchase purchase : list) {
			purchase.setPurchaseProd(sqlSession.selectOne("ProductMapper.selectProduct", purchase.getPurchaseProd().getProdNo()));
			purchase.setBuyer(sqlSession.selectOne("UserMapper.getUser", purchase.getBuyer().getUserId()));
			
			list.set(list.indexOf(purchase), purchase);
		}
		
		return list;
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