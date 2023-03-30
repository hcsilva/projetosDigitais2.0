package br.com.cardapioDigital.repositories;

import br.com.cardapioDigital.models.ItemCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoriaRepository extends JpaRepository<ItemCategoria, Long> {
}
