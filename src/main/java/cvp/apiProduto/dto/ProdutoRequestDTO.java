package cvp.apiProduto.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDTO  {

    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome;

    @NotNull(message = "O preço do produto é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = true, message = "O preço do produto não pode ser negativo.")
    private BigDecimal preco;
}
