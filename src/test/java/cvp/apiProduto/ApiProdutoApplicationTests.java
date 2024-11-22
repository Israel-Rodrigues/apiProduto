package cvp.apiProduto;

import cvp.apiProduto.dto.ProdutoRequestDTO;
import cvp.apiProduto.dto.ProdutoResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProdutoControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void deveInserirProdutoComSucesso() {
        // Criando um ProdutoRequestDTO válido para envio
        ProdutoRequestDTO novoProduto = new ProdutoRequestDTO(null, "Produto Teste", BigDecimal.valueOf(99.99));

        // Fazendo a requisição POST para inserir o produto
        ResponseEntity<ProdutoResponseDTO> response = restTemplate.postForEntity("/api/produtos", novoProduto, ProdutoResponseDTO.class);

        // Verificando se o status HTTP da resposta é 200 (OK)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Validando o conteúdo da resposta
        ProdutoResponseDTO produtoSalvo = response.getBody();
        assertThat(produtoSalvo).isNotNull();
        assertThat(produtoSalvo.getId()).isNotNull(); // Validando que o ID foi gerado
        assertThat(produtoSalvo.getNome()).isEqualTo("Produto Teste");
        assertThat(produtoSalvo.getPreco()).isEqualTo(BigDecimal.valueOf(99.99));
    }

    @Test
    void deveRetornarListaDeProdutosComSucesso() {
        // Fazendo a requisição GET para obter todos os produtos
        ResponseEntity<ProdutoResponseDTO[]> response = restTemplate.getForEntity("/api/produtos", ProdutoResponseDTO[].class);

        // Verificando se o status HTTP da resposta é 200 (OK)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Validando o conteúdo da resposta
        ProdutoResponseDTO[] produtos = response.getBody();
        assertThat(produtos).isNotNull();
        assertThat(produtos.length).isGreaterThanOrEqualTo(0);

        // Validando que os produtos retornados possuem IDs não nulos
        for (ProdutoResponseDTO produto : produtos) {
            assertThat(produto.getId()).isNotNull();
        }
    }
}
