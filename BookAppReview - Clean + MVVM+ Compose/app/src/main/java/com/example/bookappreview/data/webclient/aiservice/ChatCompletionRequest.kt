package com.example.bookappreview.data.webclient.aiservice

//-----------------------------Request from the user-----------------------------------------
data class ChatCompletionRequest(
    val messages: List<Message>,
    val model: String
)

data class Message(
    val role: String,
    val content: String
)

// ------------------------------ Resposta da Requisicao ----------------------------------

/**
 * Resposta da requisicao: Uma lista com as opcoes das respostas  dadas pela escolhas
 */
data class ChatCompletionResponse(
    val choices: List<Choice>
)

/**
 * Escolha - uma resposta da llm
 */
data class Choice(
    val message: Message
)
