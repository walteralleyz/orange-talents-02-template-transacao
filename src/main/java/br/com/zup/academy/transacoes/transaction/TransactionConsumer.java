package br.com.zup.academy.transacoes.transaction;

import br.com.zup.academy.transacoes.domain.Cartao;
import br.com.zup.academy.transacoes.domain.Estabelecimento;
import br.com.zup.academy.transacoes.domain.Transacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class TransactionConsumer {
    @Autowired
    private ObjectMapper mapper;

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @KafkaListener(topics = "${transactions.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(final ConsumerRecord consumerRecord) {
        System.out.println("key: " + consumerRecord.key());
        System.out.println("Headers: " + consumerRecord.headers());
        System.out.println("Partion: " + consumerRecord.partition());

        Transacao transacao = (Transacao) consumerRecord.value();

        transacao = manager.merge(transacao);

        System.out.println("Transaction: " + transacao.toString(mapper));
    }
}
