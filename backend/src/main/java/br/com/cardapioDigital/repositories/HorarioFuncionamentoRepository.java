package br.com.cardapioDigital.repositories;

import br.com.cardapioDigital.models.HorarioFuncionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HorarioFuncionamentoRepository extends JpaRepository<HorarioFuncionamento, UUID> {
}
