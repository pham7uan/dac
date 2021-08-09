package vn.seven.stc.masterdata.endpoints;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.config.ApplicationProperties;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.masterdata.models.Product;
import vn.seven.stc.masterdata.service.ProductService;

@RestController
@RequestMapping("/api/products")
@EnableConfigurationProperties(ApplicationProperties.class)
public class ProductEndpoint extends CrudApiEndpoint<Product, Long> {
    private ProductService productService;

    public ProductEndpoint(ProductService service){
        super(service);
        this.productService = service;
        this.baseUrl = "/api/products";
    }
}
