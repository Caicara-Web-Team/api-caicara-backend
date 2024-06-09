package br.com.caicara.backend.model.services.products;

import br.com.caicara.backend.model.dto.products.ProductCreateDto;
import br.com.caicara.backend.model.entities.products.Product;
import br.com.caicara.backend.model.entities.users.Ribeirinho;
import br.com.caicara.backend.model.jwt.JwtUserDetails;
import br.com.caicara.backend.model.repositories.products.ProductRepository;
import br.com.caicara.backend.model.services.users.RibeirinhoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RibeirinhoService ribeirinhoService;
    private final ModelMapper modelMapper;

    @Transactional
    public Product createProduct(Product product, JwtUserDetails userDetails) {
        Ribeirinho rib = ribeirinhoService.getUserById(userDetails.getId());
        product.setRibeirinho(rib);
        rib.getProducts().add(product);
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(Long id, ProductCreateDto dto) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Id not found"));
        modelMapper.map(dto, product);
        productRepository.save(product);
    }
}
