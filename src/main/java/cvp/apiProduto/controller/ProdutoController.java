package cvp.apiProduto.controller;

import cvp.apiProduto.dto.ProdutoRequestDTO;
import cvp.apiProduto.dto.ProdutoResponseDTO;
import cvp.apiProduto.model.Produto;
import cvp.apiProduto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> inserirProduto(@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {

        Produto produto = new Produto(null, produtoRequestDTO.getNome(), produtoRequestDTO.getPreco());
        Produto produtoSalvo = produtoService.inserirProduto(produto);
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO(
                produtoSalvo.getId(), produtoSalvo.getNome(), produtoSalvo.getPreco());

        return ResponseEntity.ok(produtoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> obterTodosProdutos() {

        List<ProdutoResponseDTO> produtosDTO = produtoService.obterTodosProdutos()
                .stream()
                .map(produto -> new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco()))
                .collect(Collectors.toList());
                
        return ResponseEntity.ok(produtosDTO);
    }
}
