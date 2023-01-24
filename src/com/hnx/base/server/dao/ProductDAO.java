package com.hnx.base.server.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.hnx.base.shared.model.Product;

public class ProductDAO extends DAO {

	public List<Product> getAllProducts() {
		return new ArrayList<Product>(ofy().load().type(Product.class).list());
	}
	
}
