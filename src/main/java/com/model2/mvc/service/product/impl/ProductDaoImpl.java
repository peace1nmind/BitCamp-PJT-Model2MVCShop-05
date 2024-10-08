package com.model2.mvc.service.product.impl;
// W D 

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {

	// Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// Constructor
	public ProductDaoImpl() {
	}

	@Override
	public List<Product> selectProductList(Search search) throws Exception {

		return sqlSession.selectList("ProductMapper.selectProductList", search);
	}

	@Override
	public int selectTotalCount(Search search) throws Exception {

		return sqlSession.selectOne("ProductMapper.selectTotalCount", search);
	}

	@Override
	public Product selectProduct(int prodNo) throws Exception {

		return sqlSession.selectOne("ProductMapper.selectProduct", prodNo);
	}

	@Override
	public Product selectCurrentProduct() throws Exception {

		return sqlSession.selectOne("ProductMapper.selectCurrentProduct");
	}

	@Override
	public int insertProduct(Product product) throws Exception {

		return sqlSession.insert("ProductMapper.insertProduct", product);
	}

	@Override
	public int updateProduct(Product product) throws Exception {

		return sqlSession.update("ProductMapper.updateProduct", product);
	}

	@Override
	public int updateProTranCode(Product product) throws Exception {

		return sqlSession.update("ProductMapper.updateTranCode", product);
	}

}
// class end