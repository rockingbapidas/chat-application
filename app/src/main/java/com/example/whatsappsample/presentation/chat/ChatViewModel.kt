package com.example.whatsappsample.presentation.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.whatsappsample.domain.auth.usecase.GetCurrentUserIdUseCase
import com.example.whatsappsample.domain.chat.model.Message
import com.example.whatsappsample.domain.chat.usecase.GetChatUseCase
import com.example.whatsappsample.domain.chat.usecase.GetMessagesUseCase
import com.example.whatsappsample.domain.chat.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ChatState(
    val chatId: String = "",
    val chatName: String = "",
    val currentUserId: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatUseCase: GetChatUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val chatId: String = checkNotNull(savedStateHandle["chatId"])

    private val _state = MutableStateFlow(ChatState(chatId = chatId))
    val state: StateFlow<ChatState> = _state.asStateFlow()

    val messages: Flow<PagingData<Message>> = getMessagesUseCase(chatId)
        .cachedIn(viewModelScope)

    init {
        loadChat()
        _state.value = _state.value.copy(currentUserId = getCurrentUserIdUseCase())
    }

    private fun loadChat() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                getChatUseCase(chatId).collect { chat ->
                    _state.value = _state.value.copy(
                        chatName = chat.name,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun sendMessage(content: String) {
        viewModelScope.launch {
            try {
                sendMessageUseCase(chatId, content)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message)
            }
        }
    }
}

