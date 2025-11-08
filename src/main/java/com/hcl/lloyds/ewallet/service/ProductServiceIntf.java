package com.hcl.lloyds.ewallet.service;

import com.hcl.lloyds.ewallet.entity.Product;

import java.util.List;

public interface ProductServiceIntf {

    public Product saveProduct(Product product);

    public List<Product> listProducts();
}
