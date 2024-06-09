
package br.com.caicara.backend.controllers.products;

import br.com.caicara.backend.model.entities.products.Stock;
import br.com.caicara.backend.model.jwt.JwtUserDetails;
import br.com.caicara.backend.model.services.products.StockService;
import br.com.caicara.backend.model.services.users.RibeirinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final RibeirinhoService ribeirinhoService;

    @PostMapping("/add/{productId}")
    @PreAuthorize("hasRole('RIBEIRINHO')")
    public ResponseEntity<Stock> addProductToStock(@PathVariable Long productId, @RequestParam Integer quantity, @AuthenticationPrincipal JwtUserDetails userDetails){
        Stock stock = stockService.addProductToStock(productId, quantity, userDetails);
        return ResponseEntity.ok(stock);
    }

    @PostMapping("/remove/{productId}")
    @PreAuthorize("hasRole('RIBEIRINHO')")
    public ResponseEntity<Void> removeProductFromStock(@PathVariable Long productId, @RequestParam Integer quantity){
        stockService.removeProductFromStock(productId, quantity);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detalhes")
    @PreAuthorize("hasRole('RIBEIRINHO')")
    public ResponseEntity<List<Stock>> getStock(@AuthenticationPrincipal JwtUserDetails userDetails){
        List<Stock> stocks = stockService.getStock(userDetails);
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<List<Stock>> getAll(){
        List<Stock> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

}

