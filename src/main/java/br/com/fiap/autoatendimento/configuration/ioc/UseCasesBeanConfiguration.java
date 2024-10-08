package br.com.fiap.autoatendimento.configuration.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.autoatendimento.core.gateway.CategoriaGateway;
import br.com.fiap.autoatendimento.core.gateway.ClienteGateway;
import br.com.fiap.autoatendimento.core.gateway.NotificacaoGateway;
import br.com.fiap.autoatendimento.core.gateway.PagamentoGateway;
import br.com.fiap.autoatendimento.core.gateway.PedidoGateway;
import br.com.fiap.autoatendimento.core.gateway.ProdutoGateway;
import br.com.fiap.autoatendimento.core.gateway.QRCodeServiceGateway;
import br.com.fiap.autoatendimento.core.gateway.StatusPagamentoGateway;
import br.com.fiap.autoatendimento.core.gateway.StatusPedidoGateway;
import br.com.fiap.autoatendimento.core.usecase.cliente.BuscarClientePorCpfUseCase;
import br.com.fiap.autoatendimento.core.usecase.cliente.CadastrarClienteUseCase;
import br.com.fiap.autoatendimento.core.usecase.cliente.impl.BuscarClientePorCpfUseCaseImpl;
import br.com.fiap.autoatendimento.core.usecase.cliente.impl.CadastrarClienteUseCaseImpl;
import br.com.fiap.autoatendimento.core.usecase.pagamento.AtualizacaoPagamentoUseCase;
import br.com.fiap.autoatendimento.core.usecase.pagamento.ConsultarStatusPagamentoPedidoUseCase;
import br.com.fiap.autoatendimento.core.usecase.pagamento.impl.AtualizacaoPagamentoUseCaseImpl;
import br.com.fiap.autoatendimento.core.usecase.pagamento.impl.ConsultarStatusPagamentoPedidoUseCaseImpl;
import br.com.fiap.autoatendimento.core.usecase.pedido.AtualizarStatusPedidoUseCase;
import br.com.fiap.autoatendimento.core.usecase.pedido.CadastrarPedidoUseCase;
import br.com.fiap.autoatendimento.core.usecase.pedido.ListarPedidosUseCase;
import br.com.fiap.autoatendimento.core.usecase.pedido.impl.AtualizarStatusPedidoUseCaseImpl;
import br.com.fiap.autoatendimento.core.usecase.pedido.impl.CadastrarPedidoUseCaseImpl;
import br.com.fiap.autoatendimento.core.usecase.pedido.impl.ListarPedidosUseCaseImpl;
import br.com.fiap.autoatendimento.core.usecase.produto.AtualizarProdutoUseCase;
import br.com.fiap.autoatendimento.core.usecase.produto.CadastrarProdutoUseCase;
import br.com.fiap.autoatendimento.core.usecase.produto.ListarProdutosUseCase;
import br.com.fiap.autoatendimento.core.usecase.produto.RemoverProdutoUseCase;
import br.com.fiap.autoatendimento.core.usecase.produto.impl.AtualizarProdutoUseCaseImpl;
import br.com.fiap.autoatendimento.core.usecase.produto.impl.CadastrarProdutoUseCaseImpl;
import br.com.fiap.autoatendimento.core.usecase.produto.impl.ListarProdutosUseCaseImpl;
import br.com.fiap.autoatendimento.core.usecase.produto.impl.RemoverProdutoUseCaseImpl;
import br.com.fiap.autoatendimento.core.usecase.statuspedido.ListarStatusPedidosUseCase;
import br.com.fiap.autoatendimento.core.usecase.statuspedido.impl.ListarStatusPedidosUseCaseImpl;

@Configuration
public class UseCasesBeanConfiguration {
    @Bean AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase(
        StatusPedidoGateway statusPedidoGateway,
        PedidoGateway pedidoGateway,
        NotificacaoGateway notificacaoGateway) {
            return new AtualizarStatusPedidoUseCaseImpl(statusPedidoGateway, pedidoGateway,notificacaoGateway);
        }

    @Bean CadastrarPedidoUseCase cadastrarPedidoUseCase(
        ClienteGateway clienteGateway,
        ProdutoGateway produtoGateway,
        PedidoGateway pedidoGateway,
        StatusPedidoGateway statusPedidoGateway,
        PagamentoGateway pagamentoGateway,
        StatusPagamentoGateway statusPagamentoGateway,
        QRCodeServiceGateway QRCodePortOut) {
            return new CadastrarPedidoUseCaseImpl(clienteGateway, produtoGateway, pedidoGateway, statusPedidoGateway, pagamentoGateway, statusPagamentoGateway, QRCodePortOut);
        }

    @Bean ListarPedidosUseCase listarPedidosUseCase(
        PedidoGateway pedidoGateway) {
            return new ListarPedidosUseCaseImpl(pedidoGateway);
        }

    @Bean BuscarClientePorCpfUseCase buscarClientePorCpfUseCase(
        ClienteGateway clienteGateway) {
            return new BuscarClientePorCpfUseCaseImpl(clienteGateway);
        }

    @Bean CadastrarClienteUseCase cadastrarClienteUseCase(
        ClienteGateway clienteGateway) {
            return new CadastrarClienteUseCaseImpl(clienteGateway);
        }

    @Bean AtualizacaoPagamentoUseCase atualizacaoPagamentoUseCase(
        PagamentoGateway pagamentoGateway,
        StatusPagamentoGateway statusPagamentoGateway,
        PedidoGateway pedidoGateway,
        NotificacaoGateway notificacaoGateway) {
            return new AtualizacaoPagamentoUseCaseImpl( pagamentoGateway, statusPagamentoGateway, pedidoGateway, notificacaoGateway);
        }

    @Bean ConsultarStatusPagamentoPedidoUseCase consultarStatusPagamentoPedidoUseCase(
        PagamentoGateway pagamentoGateway) {
            return new ConsultarStatusPagamentoPedidoUseCaseImpl( pagamentoGateway);
        }

    @Bean AtualizarProdutoUseCase atualizarProdutoUseCase(
        ProdutoGateway produtoGateway,
        CategoriaGateway categoriaGateway) {
            return new AtualizarProdutoUseCaseImpl(produtoGateway, categoriaGateway);
        }

    @Bean CadastrarProdutoUseCase cadastrarProdutoUseCase(
        ProdutoGateway produtoGateway,
        CategoriaGateway categoriaGateway) {
            return new CadastrarProdutoUseCaseImpl(produtoGateway, categoriaGateway);
        }

    @Bean ListarProdutosUseCase listarProdutosUseCase(
        ProdutoGateway produtoGateway) {
            return new ListarProdutosUseCaseImpl(produtoGateway);
        }

    @Bean RemoverProdutoUseCase removerProdutoUseCase(
        ProdutoGateway produtoGateway) {
            return new RemoverProdutoUseCaseImpl(produtoGateway);
        }

    @Bean ListarStatusPedidosUseCase listarStatusPedidosUseCase(
        StatusPedidoGateway statusPedidoGateway) {
            return new ListarStatusPedidosUseCaseImpl(statusPedidoGateway);
        }

}
