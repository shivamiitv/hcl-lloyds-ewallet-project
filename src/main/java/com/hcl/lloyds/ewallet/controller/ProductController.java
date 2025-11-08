package com.hcl.lloyds.ewallet.controller;

import com.hcl.lloyds.ewallet.entity.Merchant;
import com.hcl.lloyds.ewallet.entity.Product;
import com.hcl.lloyds.ewallet.service.ProductService;
import com.hcl.lloyds.ewallet.service.ProductServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ewallet/v1/product")
public class ProductController {

    @Autowired
    ProductServiceIntf productService;

    @PostMapping(value = "/save")
    public Product saveMerchant(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.listProducts();
    }
}
