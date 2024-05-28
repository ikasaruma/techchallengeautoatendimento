package br.com.fiap.autoatendimento.application.usecase.pedido;

import br.com.fiap.autoatendimento.application.port.in.pedido.CadastrarPedidoPortIn;
import br.com.fiap.autoatendimento.application.port.out.ClientePortOut;
import br.com.fiap.autoatendimento.application.port.out.QRCodeServicePortOut;
import br.com.fiap.autoatendimento.application.port.out.PagamentoPortOut;
import br.com.fiap.autoatendimento.application.port.out.PedidoPortOut;
import br.com.fiap.autoatendimento.application.port.out.ProdutoPortOut;
import br.com.fiap.autoatendimento.application.usecase.exception.ClienteNaoEncontradoException;
import br.com.fiap.autoatendimento.application.usecase.exception.ErroAoGerarQRCodeException;
import br.com.fiap.autoatendimento.application.usecase.exception.ProdutoNaoEncontradoException;
import br.com.fiap.autoatendimento.application.usecase.pedido.dto.CadastrarPedidoInputDto;
import br.com.fiap.autoatendimento.application.usecase.pedido.dto.CadastrarPedidoOutputDto;
import br.com.fiap.autoatendimento.domain.model.cliente.Cliente;
import br.com.fiap.autoatendimento.domain.model.pagamento.Pagamento;
import br.com.fiap.autoatendimento.domain.model.pagamento.StatusPagamento;
import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;
import br.com.fiap.autoatendimento.domain.model.pedido.StatusPedido;
import br.com.fiap.autoatendimento.domain.model.produto.Produto;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Named
@RequiredArgsConstructor
public class CadastrarPedidoUseCase implements CadastrarPedidoPortIn {

    private final static Integer STATUS_RECEBIDO = 1;
    private final static Integer STATUS_PENDENTE = 1;
    private final ClientePortOut clientePortOut;
    private final ProdutoPortOut produtoPortOut;
    private final PedidoPortOut pedidoPortOut;
    private final PagamentoPortOut pagamentoPortOut;
    private final QRCodeServicePortOut QRCodePortOut;

    @Transactional
    @Override
    public CadastrarPedidoOutputDto executar(CadastrarPedidoInputDto input) {

        final Cliente cliente;

        if (Objects.isNull(input.getCpf()) || input.getCpf().isBlank()) {
            cliente = null;
        } else {
            cliente = clientePortOut.buscarPorCpf(input.getCpf())
                    .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente informado nao encontrado."));
        }

        final List<Produto> produtos = new ArrayList<>();

        for (Integer idProduto : input.getProdutos()) {
            produtos.add(produtoPortOut.buscarPorIdProduto(idProduto)
                    .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto informado nao encontrado.")));
        }

        final Pedido pedido = Pedido.builder()
                .idPedido(input.getIdPedido())
                .cliente(cliente)
                .produtos(produtos)
                .status(StatusPedido.builder()
                        .idStatusPedido(STATUS_RECEBIDO)
                        .build())
                .build();

        final Pagamento pagamento = Pagamento.builder()
            .status(StatusPagamento.builder()
                    .idStatusPagamento(STATUS_PENDENTE)
                    .build())
            .pedido(pedido)
            .build();

        final Integer idPedido = pedidoPortOut.salvar(pedido);
        pagamentoPortOut.salvar(pagamento);

        try {
            BufferedImage qrCode = QRCodePortOut.gerar(pedido);
            return CadastrarPedidoOutputDto.builder()
                    .idPedido(idPedido)
                    .qrCode(qrCode)
                    .build();
        } catch (Exception e) {
            throw new ErroAoGerarQRCodeException("Erro ao gerar o QRCode.");
        }
        
    }

}
