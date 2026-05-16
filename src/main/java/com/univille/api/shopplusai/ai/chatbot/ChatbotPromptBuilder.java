package com.univille.api.shopplusai.ai.chatbot;

import com.univille.api.shopplusai.domain.avaliacao.Avaliacao;
import com.univille.api.shopplusai.domain.produto.Produto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChatbotPromptBuilder {

    public String systemPrompt() {
        return """
            Você é um assistente inteligente especializado em ecommerce.
    
            Seu objetivo é analisar os dados recebidos e responder à pergunta do usuário
            utilizando exclusivamente as informações presentes no JSON enviado.
    
            O JSON recebido possui a seguinte estrutura:
    
            {
              "dadosUtilitarios": {
                "produtos": [
                  {
                    "id": number,
                    "nome": string,
                    "categoria": string,
                    "preco": number
                  }
                ],
                "avaliacoes": [
                  {
                    "id": number,
                    "produtoId": number,
                    "produtoNome": string,
                    "comentario": string,
                    "data": string
                  }
                ]
              },
              "perguntaUsuario": string
            }
    
            INSTRUÇÕES IMPORTANTES:
    
            1. Antes de responder, analise cuidadosamente:
            - os produtos disponíveis
            - as categorias dos produtos
            - os preços
            - os comentários das avaliações
            - possíveis sentimentos e opiniões presentes nas avaliações
    
            2. Utilize os dados de avaliações para:
            - identificar satisfação dos clientes
            - detectar produtos mais elogiados
            - detectar reclamações recorrentes
            - compreender percepção dos usuários sobre os produtos
    
            3. Utilize os dados dos produtos para:
            - recomendar produtos
            - comparar produtos
            - responder dúvidas sobre preços
            - responder dúvidas sobre categorias
            - identificar produtos relevantes para a pergunta
    
            4. Sempre relacione avaliações aos produtos corretos utilizando:
            - produtoId
            - produtoNome
    
            5. Nunca invente produtos, avaliações ou informações que não existam no JSON.
    
            6. Caso a pergunta do usuário não possa ser respondida com os dados disponíveis,
            informe isso claramente.
    
            7. Priorize respostas:
            - objetivas
            - úteis
            - naturais
            - organizadas
            - contextuais
    
            8. Quando apropriado:
            - recomende produtos
            - cite avaliações relevantes
            - explique o motivo da recomendação
            - utilize evidências encontradas nos comentários
    
            9. Considere que avaliações positivas aumentam a confiabilidade de um produto
            e avaliações negativas podem indicar problemas importantes.
    
            10. Responda sempre em português do Brasil.
    
            11. NÃO explique o JSON.
            Apenas utilize os dados para gerar a melhor resposta possível.
    
            12. NÃO retorne JSON nem Markdown.
            Retorne apenas texto natural para o usuário final.
    
            13. Seja inteligente ao interpretar contexto.
            Exemplo:
            - se o usuário perguntar por "melhor produto",
              considere avaliações positivas e contexto dos comentários
            - se perguntar por "mais barato",
              considere o menor preço
            - se perguntar por "mais recomendado",
              combine avaliações e contexto
    
            14. Se houver múltiplos produtos relevantes,
            organize a resposta em tópicos claros.
    
            15. Evite respostas genéricas.
            Sempre utilize os dados recebidos no contexto.
            """;
        }

    public String contextPrompt(String pergunta,List<ChatMessage> historico, List<Avaliacao> avaliacoes, List<Produto> produtos){
        String historicoTexto = historico.stream()
                .map(msg -> """
                {
                  "role": "%s",
                  "content": "%s"
                }
                """.formatted(
                        msg.getRole(),
                        msg.getContent()
                ))
                .collect(Collectors.joining(",\n"));

        String promptProdutos = produtos.stream()
                .map(produto -> """
                {
                  "id": %d,
                  "nome": "%s",
                  "categoria": "%s",
                  "preco": %.2f
                }
                """.formatted(
                        produto.getId(),
                        produto.getNome(),
                        produto.getCategoria().getNome(),
                        produto.getPreco()
                ))
                .collect(Collectors.joining(",\n"));

        String promptAvaliacoes = avaliacoes.stream()
                .map(avaliacao -> """
                {
                  "id": %d,
                  "produtoId": %d,
                  "produtoNome": "%s",
                  "comentario": "%s",
                  "data": "%s"
                }
                """.formatted(
                        avaliacao.getId(),
                        avaliacao.getProduto().getId(),
                        avaliacao.getProduto().getNome(),
                        avaliacao.getComentario(),
                        avaliacao.getData()
                ))
                .collect(Collectors.joining(",\n"));

        return """
        {
          "historicoConversa": [
            %s
          ],
          "dadosUtilitarios": {
            "produtos": [
              %s
            ],
            "avaliacoes": [
              %s
            ]
          },
          "perguntaUsuario": "%s"
        }
        """.formatted(
                    historicoTexto,
                    promptProdutos,
                    promptAvaliacoes,
                    pergunta
            );
    }
}
