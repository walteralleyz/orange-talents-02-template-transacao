package br.com.zup.academy.transacoes.transaction;

import br.com.zup.academy.transacoes.domain.Transacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/card/{id}")
    @Transactional
    public ResponseEntity<List> getAll(@PathVariable String id) {
        Query query = manager.createQuery(
            "select t from Transacao t where t.cartao.id = :value order by t.estabelecimento.id desc",
            Transacao.class
        );

        query.setParameter("value", id);
        query.setMaxResults(10);

        List transacoes = query.getResultList();

        if(transacoes.size() < 1) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(transacoes);
    }
}
