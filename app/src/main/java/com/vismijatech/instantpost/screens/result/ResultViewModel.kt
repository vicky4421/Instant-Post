package com.vismijatech.instantpost.screens.result

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.vismijatech.instantpost.data.CONTENT_COST_10
import com.vismijatech.instantpost.data.CONTENT_COST_3
import com.vismijatech.instantpost.data.COST_10_MESSAGE
import com.vismijatech.instantpost.data.COST_3_MESSAGE
import com.vismijatech.instantpost.data.LOW_CREDIT_MESSAGE
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
import com.vismijatech.instantpost.user.UserUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vivek Sawant on 23-12-2023.
 */
@HiltViewModel
class ResultViewModel @Inject constructor(
    private val repository: OpenAiRepository,
    private val database: InstantPostDatabase,
    private val userUtils: UserUtils
) : ViewModel() {
    val response: StateFlow<OpenAiResponse?>
        get() = repository.openAiResponse

    val loading = mutableStateOf(false)
    val creditMessage = mutableStateOf("")
    private val currentCredits = mutableIntStateOf(0)

    init {
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseDatabase.getInstance()
        val currentUser = auth.currentUser
        val uid = currentUser?.uid.toString()
        val dbRef = db.getReference("users").child(uid)
        viewModelScope.launch {
            dbRef.child("credit").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    currentCredits.intValue = snapshot.getValue(Int::class.java) ?: 0
                }

                override fun onCancelled(error: DatabaseError) {
                    creditMessage.value = "Something went wrong"
                }

            })
        }
    }

    fun getResponse(prompt: String) {
        viewModelScope.launch {
            delay(500)
            loading.value = true

            when {
                prompt.contains("Title: Social Media", ignoreCase = true) -> {
                    if (currentCredits.intValue < CONTENT_COST_3) {
                        creditMessage.value = LOW_CREDIT_MESSAGE
                        loading.value = false
                        return@launch
                    } else {
                        userUtils.costUserCredit(credit = CONTENT_COST_3)
                        creditMessage.value = COST_3_MESSAGE
                    }
                }

                prompt.contains("Title: Whatsapp", ignoreCase = true) -> {
                    if (currentCredits.intValue < CONTENT_COST_3) {
                        creditMessage.value = LOW_CREDIT_MESSAGE
                        loading.value = false
                        return@launch
                    } else {
                        userUtils.costUserCredit(credit = CONTENT_COST_3)
                        creditMessage.value = COST_3_MESSAGE
                    }
                }

                prompt.contains("Title: Personal", ignoreCase = true) -> {
                    if (currentCredits.intValue < CONTENT_COST_3) {
                        creditMessage.value = COST_3_MESSAGE
                        loading.value = false
                        return@launch
                    } else {
                        userUtils.costUserCredit(credit = CONTENT_COST_3)
                        creditMessage.value = "Your account is now 5 credits lighter"
                    }
                }

                prompt.contains("Title: Business", ignoreCase = true) -> {
                    if (currentCredits.intValue < CONTENT_COST_3) {
                        creditMessage.value = LOW_CREDIT_MESSAGE
                        loading.value = false
                        return@launch
                    } else {
                        userUtils.costUserCredit(credit = CONTENT_COST_3)
                        creditMessage.value = COST_3_MESSAGE
                    }
                }

                prompt.contains("Title: Promotional", ignoreCase = true) -> {
                    if (currentCredits.intValue < CONTENT_COST_3) {
                        creditMessage.value = LOW_CREDIT_MESSAGE
                        loading.value = false
                        return@launch
                    } else {
                        userUtils.costUserCredit(credit = CONTENT_COST_3)
                        creditMessage.value = COST_3_MESSAGE
                    }
                }

                prompt.contains("Title: Newsletter", ignoreCase = true) -> {
                    if (currentCredits.intValue < CONTENT_COST_3) {
                        creditMessage.value = LOW_CREDIT_MESSAGE
                        loading.value = false
                        return@launch
                    } else {
                        userUtils.costUserCredit(credit = CONTENT_COST_3)
                        creditMessage.value = COST_3_MESSAGE
                    }
                }

                prompt.contains("Title: blog", ignoreCase = true) -> {
                    Log.d("TAG", "getResponse: blog")
                    if (currentCredits.intValue < CONTENT_COST_10) {
                        creditMessage.value = LOW_CREDIT_MESSAGE
                        loading.value = false
                        return@launch
                    } else {
                        userUtils.costUserCredit(credit = CONTENT_COST_10)
                        creditMessage.value = COST_10_MESSAGE
                    }
                }
            }
            repository.getResponse(prompt)
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