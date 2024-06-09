package br.com.caicara.backend.model.repositories.products;

import br.com.caicara.backend.model.entities.products.Product;
import br.com.caicara.backend.model.entities.products.Stock;
import br.com.caicara.backend.model.entities.users.Ribeirinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProduct(Product product);

    List<Stock> findByRibeirinho(Ribeirinho rib);
}
