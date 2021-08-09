package vn.seven.stc.masterdata.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Product;
import vn.seven.stc.masterdata.repositories.ProductRepository;

@Service
public class ProductService extends CrudService<Product, Long> {
    private ProductRepository productRepository;
    @Value("${baseUrl}")
    private String baseUrl;

    public ProductService(ProductRepository productRepository){
        this.repository = this.productRepository = productRepository;
    }
}

