package br.com.fiap.autoatendimento.dataprovider.database;

import br.com.fiap.autoatendimento.dataprovider.database.entity.CategoriaEntity;
import br.com.fiap.autoatendimento.dataprovider.database.entity.ProdutoEntity;
import br.com.fiap.autoatendimento.dataprovider.database.repository.ProdutoRepository;
import br.com.fiap.autoatendimento.core.gateway.ProdutoGateway;
import br.com.fiap.autoatendimento.core.entity.produto.Categoria;
import br.com.fiap.autoatendimento.core.entity.produto.Produto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class ProdutoDatabase implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    @Override
    public Integer salvar(Produto produto) {
        
        final ProdutoEntity entity = ProdutoEntity.builder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .imagem(produto.getImagem())
                .ativo(produto.getAtivo())
                .categoria(
                    CategoriaEntity.builder()
                        .nome(produto.getCategoria().getNome())
                        .idCategoria(produto.getCategoria().getIdCategoria())
                        .build()
                    )
                .build();
        
        return produtoRepository.save(entity).getIdProduto();
    }

    @Override
    public Optional<Produto> buscarPorIdProduto(Integer idProduto) {

        return produtoRepository.findById(idProduto)
                .map(entity -> Produto.builder()
                        .idProduto(entity.getIdProduto())
                        .nome(entity.getNome())
                        .descricao(entity.getDescricao())
                        .ativo(entity.getAtivo())
                        .categoria(Categoria.builder()
                                .idCategoria(entity.getCategoria().getIdCategoria())
                                .nome(entity.getCategoria().getNome())
                                .build())
                        .imagem(entity.getImagem())
                        .preco(entity.getPreco())
                        .build());
    }

    @Override
    public List<Produto> listar(String categoria) {

        List<ProdutoEntity> produtos = categoria != null
                ? produtoRepository.findByCategoriaNome(categoria)
                : produtoRepository.findAll();

        return produtos.stream()
                .map(entity -> Produto.builder()
                        .idProduto(entity.getIdProduto())
                        .nome(entity.getNome())
                        .descricao(entity.getDescricao())
                        .preco(entity.getPreco())
                        .imagem(entity.getImagem())
                        .ativo(entity.getAtivo())
                        .categoria(Categoria.builder()
                                .idCategoria(entity.getCategoria().getIdCategoria())
                                .nome(entity.getCategoria().getNome())
                                .build())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Integer atualizar(Produto produto) {
        
        final ProdutoEntity entity = ProdutoEntity.builder()
                .idProduto(produto.getIdProduto())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .imagem(produto.getImagem())
                .ativo(produto.getAtivo())
                .categoria(
                    CategoriaEntity.builder()
                        .nome(produto.getCategoria().getNome())
                        .idCategoria(produto.getCategoria().getIdCategoria())
                        .build()
                    )
                .build();
        
        return produtoRepository.save(entity).getIdProduto();
    }
    
}
