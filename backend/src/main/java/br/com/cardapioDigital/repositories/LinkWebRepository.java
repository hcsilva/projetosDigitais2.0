package br.com.cardapioDigital.repositories;

import br.com.cardapioDigital.models.LinkWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LinkWebRepository extends JpaRepository<LinkWeb, UUID> {
}
