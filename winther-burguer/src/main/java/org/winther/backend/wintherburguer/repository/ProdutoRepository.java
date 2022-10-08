package org.winther.backend.wintherburguer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winther.backend.wintherburguer.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
