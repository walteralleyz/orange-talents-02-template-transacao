package br.com.zup.academy.transacoes.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transacao {

    @Id
    private String id;

    @Column(nullable = false)
    private BigDecimal valor;

    @OneToOne(cascade = CascadeType.MERGE)
    private Estabelecimento estabelecimento;

    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Column(nullable = false)
    private final LocalDateTime efetivadoEm = LocalDateTime.now();

    @Deprecated
    private Transacao() {}

    public Transacao(String id, BigDecimal valor, Estabelecimento estabelecimento, Cartao cartao) {
        this.id = id;
        this.valor = valor;
        this.cartao = cartao;
        this.estabelecimento = estabelecimento;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadoEm() {
        return efetivadoEm;
    }

    public String toString(ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Transacao";
        }
    }
}
