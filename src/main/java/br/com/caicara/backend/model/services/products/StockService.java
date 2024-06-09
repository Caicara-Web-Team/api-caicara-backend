package br.com.caicara.backend.model.services.products;

import br.com.caicara.backend.model.entities.products.Product;
import br.com.caicara.backend.model.entities.products.Stock;
import br.com.caicara.backend.model.entities.users.Ribeirinho;
import br.com.caicara.backend.model.jwt.JwtUserDetails;
import br.com.caicara.backend.model.repositories.products.ProductRepository;
import br.com.caicara.backend.model.repositories.products.StockRepository;
import br.com.caicara.backend.model.services.users.RibeirinhoService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final RibeirinhoService ribeirinhoService;

    @Transactional
    public Stock addProductToStock(Long productId, Integer quantity, JwtUserDetails userDetails) {

        Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found"));
        Ribeirinho rib = ribeirinhoService.getUserById(userDetails.getId());
        Stock stock = stockRepository.findByProduct(product).orElse(new Stock(null, product, 0, rib));
        if (quantity == 0) {
            stock.setQuantityAvailable(stock.getQuantityAvailable() + 1);
        }
        else {
            stock.setQuantityAvailable(stock.getQuantityAvailable() + quantity);
        }
        return stockRepository.save(stock);
    }

    @Transactional
    public void removeProductFromStock(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found"));
        Stock stock = stockRepository.findByProduct(product).orElseThrow(() -> new RuntimeException("Stock not found for product"));
        int newQuantity = stock.getQuantityAvailable() - quantity;

        if (newQuantity < 0)
            throw new RuntimeException("Not enough stock available");

        if (quantity == 0) {
            stock.setQuantityAvailable(stock.getQuantityAvailable() - 1);
        }
        else {
            stock.setQuantityAvailable(newQuantity);
            stockRepository.save(stock);
        }
    }

    @Transactional
    public List<Stock> getStock(JwtUserDetails userDetails){
        Ribeirinho rib = ribeirinhoService.getUserById(userDetails.getId());
        return stockRepository.findByRibeirinho(rib);
    }

    @Transactional
    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }
}
