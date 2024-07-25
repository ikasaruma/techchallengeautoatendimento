package br.com.fiap.autoatendimento.dataprovider.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "status_pedido")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusPedidoEntity implements Serializable {

    @Id
    @Column(name = "id_status_pedido")
    private Integer idStatusPedido;

    @Column(name = "nome")
    private String nome;

}