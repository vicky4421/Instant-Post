package com.vismijatech.instantpost.screens.result

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vismijatech.instantpost.db.database.InstantPostDatabase
import com.vismijatech.instantpost.db.entities.BlogContent
import com.vismijatech.instantpost.db.entities.BusinessContent
import com.vismijatech.instantpost.db.entities.NewsletterContent
import com.vismijatech.instantpost.db.entities.PersonalContent
import com.vismijatech.instantpost.db.entities.PromotionalContent
import com.vismijatech.instantpost.db.entities.SocialMediaContent
import com.vismijatech.instantpost.db.entities.WhatsappContent
import com.vismijatech.instantpost.model.openai.OpenAiResponse
import com.vismijatech.instantpost.repository.OpenAiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 23-12-2023.
 */
@HiltViewModel
class ResultViewModel @Inject constructor(
    private val repository: OpenAiRepository,
    private val database: InstantPostDatabase
) : ViewModel() {
    val response: StateFlow<OpenAiResponse?>
        get() = repository.openAiResponse

    val loading = mutableStateOf(false)

    fun getResponse(prompt: String) {
        viewModelScope.launch {
            loading.value = true
            repository.getResponse(prompt = prompt)
            loading.value = false
        }
    }

    fun saveResponse(prompt: String) {
        val blogDao = database.blogDao()
        val socialMediaDao = database.socialMediaDao()
        val whatsappDao = database.whatsappDao()
        val personalDao = database.personalDao()
        val businessDao = database.businessDao()
        val promotionalDao = database.promotionalDao()
        val newsletterDao = database.newsletterDao()

        when {
            prompt.contains("Title: Social Media", ignoreCase = true) -> {
                val socialMediaContent = SocialMediaContent(
                    date = response.value?.created ?: 0,
                    content = response.value?.choices?.first()?.message?.content ?: ""
                )
                viewModelScope.launch { socialMediaDao.insertSocialMediaContent(socialMediaContent = socialMediaContent) }
            }

            prompt.contains("Title: Whatsapp", ignoreCase = true) -> {
                val whatsappContent = WhatsappContent(
                    date = response.value?.created ?: 0,
                    content = response.value?.choices?.first()?.message?.content ?: ""
                )
                viewModelScope.launch { whatsappDao.insertWhatsappContent(whatsappContent = whatsappContent) }
            }

            prompt.contains("Title: Personal", ignoreCase = true) -> {
                val personalContent = PersonalContent(
                    date = response.value?.created ?: 0,
                    content = response.value?.choices?.first()?.message?.content ?: ""
                )
                viewModelScope.launch { personalDao.insertPersonalContent(personalContent = personalContent) }
            }

            prompt.contains("Title: Business", ignoreCase = true) -> {
                val businessContent = BusinessContent(
                    date = response.value?.created ?: 0,
                    content = response.value?.choices?.first()?.message?.content ?: ""
                )
                viewModelScope.launch { businessDao.insertBusinessContent(businessContent = businessContent) }
            }

            prompt.contains("Title: Promotional", ignoreCase = true) -> {
                val promotionalContent = PromotionalContent(
                    date = response.value?.created ?: 0,
                    content = response.value?.choices?.first()?.message?.content ?: ""
                )
                viewModelScope.launch { promotionalDao.insertPromotionalContent(promotionalContent = promotionalContent) }
            }

            prompt.contains("Title: Newsletter", ignoreCase = true) -> {
                val newsletterContent = NewsletterContent(
                    date = response.value?.created ?: 0,
                    content = response.value?.choices?.first()?.message?.content ?: ""
                )
                viewModelScope.launch { newsletterDao.insertNewsletterContent(newsletterContent = newsletterContent) }
            }

            prompt.contains("Title: blog", ignoreCase = true) -> {
                val blogContent = BlogContent(
                    date = response.value?.created ?: 0,
                    content = response.value?.choices?.first()?.message?.content ?: ""
                )
                viewModelScope.launch { blogDao.insertBlogContent(blogContent = blogContent) }
            }
        }
    }
}