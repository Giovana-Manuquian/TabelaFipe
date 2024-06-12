package br.com.alura.TabelaFipe.service; // Declara o pacote onde esta classe está localizada

import com.fasterxml.jackson.core.JsonProcessingException; // Importa a exceção que pode ser lançada ao processar JSON
import com.fasterxml.jackson.databind.ObjectMapper; // Importa a classe principal da biblioteca Jackson para conversão entre JSON e objetos Java
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados { // Define a classe pública ConverteDados que implementa a interface IConverteDados

    private ObjectMapper mapper = new ObjectMapper(); // Cria uma instância de ObjectMapper para realizar a conversão de JSON para objetos Java

    @Override // Indica que estamos sobrescrevendo um método definido na interface IConverteDados
    public <T> T obterDados(String json, Class<T> classe) { // Declara um método genérico que converte uma string JSON para um objeto de uma classe específica
        try {
            return mapper.readValue(json, classe); // Tenta converter o JSON fornecido para um objeto do tipo especificado
        } catch (JsonProcessingException e) { // Captura a exceção que pode ser lançada se a conversão falhar
            throw new RuntimeException(e); // Lança uma RuntimeException encapsulando a exceção original para sinalizar um erro na conversão
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);

        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
