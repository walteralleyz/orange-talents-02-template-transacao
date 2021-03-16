package br.com.zup.academy.transacoes.domain;

import javax.persistence.*;

@Entity
@Table(name = "cards")
public class Cartao {
    @Id
    private String id;

    @Column(nullable = false)
    private String email;

    @Deprecated
    private Cartao() {}

    public Cartao(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}