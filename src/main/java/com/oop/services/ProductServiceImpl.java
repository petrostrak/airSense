/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.services;

import com.oop.dao.IProductDao;
import com.oop.entities.Product;
import com.oop.entities.ProductType;
import com.oop.exceptions.ProductNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author petros_trak
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductDao productDao;

    @Autowired
    IProductTypeService productTypeService;
    
    @Override
    public Product getById(long productId) {
        return productDao.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> getAllProducts() {
        Iterable<Product> productEntity = productDao.findAll();
        List<Product> products = (List<Product>) productEntity;
        return products;
    }

    @Override
    public Product save(Product product) {
        if (product == null) {
            return null;
        }
        ProductType productType = productTypeService.getById(product.getProductType().getId());
        product.setProductType(productType);
        return productDao.save(product);
    }

    @Override
    public Product update(Product product) {
        if(!productDao.existsById(product.getId()))throw new ProductNotFoundException();
        return productDao.save(product);
    }

    @Override
    public void delete(Product product) {
        if(!productDao.existsById(product.getId()))throw new ProductNotFoundException();
        productDao.deleteById(product.getId());
    }


}
