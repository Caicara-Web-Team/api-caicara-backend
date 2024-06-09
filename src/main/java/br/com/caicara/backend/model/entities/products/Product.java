package br.com.caicara.backend.model.entities.products;

import br.com.caicara.backend.model.entities.users.Ribeirinho;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_ribeirinho")
    @JsonIgnore
    private Ribeirinho ribeirinho;


    public enum Category{
        PEIXES,
        CRUSTACEOS,
        MOLUSCOS
    }
}
