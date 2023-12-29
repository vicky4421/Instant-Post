package com.vismijatech.instantpost.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import com.vismijatech.instantpost.R
import com.vismijatech.instantpost.model.NewsletterQuery
import com.vismijatech.instantpost.model.queries.BlogQuery
import com.vismijatech.instantpost.model.queries.BusinessQuery
import com.vismijatech.instantpost.model.queries.PersonalQuery
import com.vismijatech.instantpost.model.queries.PromotionalQuery
import com.vismijatech.instantpost.model.queries.SocialMediaQuery
import com.vismijatech.instantpost.model.queries.TravelBlogQuery
import com.vismijatech.instantpost.model.queries.WhatsappReplyQuery

/**
 * Created by Vivek Sawant on 22-12-2023.
 */

// Prompts
// Social media prompt
fun getSocialMediaPrompt(query: SocialMediaQuery?) =
    "You will act as a professional content writer.Write 5 social media " +
            "posts with hashtags, considering the following points:\n\n" +
            "Start with a strong hook.Keep it clear and concise. " +
            "Use simple language and avoid jargon. Maximum characters: 280\n\n" +
            "Title: Social Media \n" +
            "Image Description: ${query?.imageDescription} \n" +
            "Emotions or Mood: ${query?.emotions} \n" +
            "Caption style: ${query?.captionStyle} \n" +
            "Key keywords: ${query?.keywords} \n" +
            "Message or story: ${query?.messageOrStory} \n" +
            "Location: ${query?.location} \n" +
            "Call to action: ${query?.callToAction} \n" +
            "Length: ${query?.length} \n" +
            "Goal: ${query?.goal} \n" +
            "Target Audience: ${query?.targetAudience} \n" +
            "Brand or personal tagline: ${query?.tagline}"

fun getWhatsappReplyPrompt(query: WhatsappReplyQuery?) =
    "You are responding to a WhatsApp message. Your reply should be tailored, engaging, and relevant. " +
            "Consider the following details to create an appropriate response:\n\n" +
            "Title: Whatsapp \n" +
            "Message Content: ${query?.messageContent} \n" +
            "Sender\'s message tone: ${query?.sendersTone} \n" +
            "Relationship: ${query?.relationship} \n" +
            "Goal: ${query?.goal} \n" +
            "Your tone: ${query?.yourTone} \n" +
            "Occasion: ${query?.occasion} \n" +
            "Special instructions: ${query?.specialInstructions} \n" +
            "Length: ${query?.length}"

fun getPersonalPrompt(query: PersonalQuery?) =
    "You will act as a thoughtful email writer. Compose a heartwarming email to ${query?.recipientsName} for " +
            "${query?.occasion} considering with the following points. \n\n" +
            "Begin with a warm and personal greeting. Maintain a genuine and heartfelt tone throughout the email. " +
            "Keep the email concise and focused. \n" +
            "Title: Personal \n" +
            "Relationship with recipient: ${query?.relationship} \n" +
            "Main points to include: ${query?.mainPoints} \n" +
            "Positive qualities of recipient: ${query?.positiveQualities} \n" +
            "Emotional tone: ${query?.emotionalTone} \n" +
            "Closing sentiment: ${query?.closingSentiment} \n" +
            "Feel free to draw inspiration from your relationship and shared experiences."

fun getBusinessPrompt(query: BusinessQuery?) =
    "You are tasked with composing a business email. Write an email that includes the following elements: \n" +
            "Title: Business \n" +
            "Recipient\'s Name and Title: ${query?.recipientsName} \n" +
            "Purpose: ${query?.purpose} \n" +
            "Background information: ${query?.backgroundInfo} \n" +
            "Main points to include: ${query?.mainPoints} \n" +
            "Professional tone: ${query?.professionalTone} \n" +
            "Call to action: ${query?.callToAction} \n" +
            "Closing sentiment: ${query?.closingSentiment} \n" +
            "Signature: ${query?.signature} \n" +
            "Please ensure the email is well-structured, free from errors, and professionally composed."

fun getPromotionalPrompt(query: PromotionalQuery?) =
    "You will act as a skilled marketing copywriter. Compose a persuasive promotional email for a ${query?.product} " +
            "targeted towards ${query?.targetAudience}. \n" +
            "The email should highlight ${query?.keyPoints} include a compelling call to action. \n" +
            "Consider the following points while composing the email: \n" +
            "Title: Promotional \n" +
            "Call to action: ${query?.callToAction} \n" +
            "Discounts or promotions: ${query?.discounts} \n" +
            "Email subject line: ${query?.subjectLine} \n" +
            "Personalization: ${query?.personalization} \n" +
            "Email tone: ${query?.tone} \n" +
            "Closing: ${query?.closing} \n" +
            "Length: ${query?.length} \n" +
            "Write a compelling promotional email that engages the reader and encourages them to take the desired action. " +
            "Ensure that the email showcases the product's unique benefits and clearly presents any discounts or promotions. " +
            "Use a friendly and persuasive tone that resonates with the target audience. The email should be clear and concise, " +
            "and the call to action should be prominent. Personalize the email as appropriate and end with a strong closing."

fun getNewsletterPrompt(query: NewsletterQuery?) =
    "You are tasked with creating a newsletter on the theme or topic of ${query?.themeOrTopic}. \n" +
            "Craft a compelling newsletter with the following elements: \n" +
            "Title: ${query?.title} \n" +
            "Title: Newsletter \n" +
            "Sections and content: ${query?.sectionAndContents} \n" +
            "Call to action: ${query?.callToAction} \n" +
            "Links and resources: ${query?.links} \n" +
            "Email subject line: ${query?.subjectLine} \n" +
            "Tone and style: ${query?.tone} \n" +
            "Personalization: ${query?.personalization} \n" +
            "Frequency: ${query?.frequency} \n" +
            "Length: ${query?.length}"

fun getBlogPrompt(query: BlogQuery?) =
    "Title: ${query?.title} \n" +
            "Title: blog \n" +
            "Introduction: ${query?.introduction} \n" +
            "Sections and content: ${query?.sections} \n" +
            "Tone and style: ${query?.tone} \n" +
            "Personalization: ${query?.personalization} \n" +
            "Call to action: ${query?.callToAction} \n" +
            "Links and resources: ${query?.links} \n" +
            "Target audience: ${query?.targetAudience} \n" +
            "Length: ${query?.length} \n" +
            "SEO keywords: ${query?.seo} \n" +
            "Supporting data: ${query?.supportingData} \n" +
            "Formatting preferences: ${query?.formatting} \n" +
            "Key messages: ${query?.keyMessage} \n" +
            "Conclusion: ${query?.conclusion} \n" +
            "Please generate the blog content with the given elements"

fun getTravelBlogPrompt(query: TravelBlogQuery?) =
    "You are a passionate travel blogger, and you're sharing your recent travel adventure with your audience. \n" +
            "Craft a captivating travel blog post using the following points as guidelines:\n" +
            "Title: ${query?.title} \n" +
            "Introduction: ${query?.introduction} \n" +
            "Destinations and Itinerary: ${query?.destinations} \n" +
            "Travel experience: ${query?.travelExperience} \n" +
            "Accommodations and food: ${query?.food} \n" +
            "Travel tips: ${query?.tips} \n" +
            "Personal reflections: ${query?.reflections} \n" +
            "Recommendations: ${query?.recommendations} \n" +
            "Conclusions: ${query?.conclusion} \n" +
            "Call to action: ${query?.callToAction} \n" +
            "Links and resources: ${query?.links} \n" +
            "Tone and style: ${query?.tone} \n" +
            "Personalization: ${query?.personalization} \n" +
            "Frequency: ${query?.frequency} \n" +
            "Length: ${query?.length}"

// fun to copy text from result screen
fun copyToClipboard(context: Context, text: String){
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("Copied text", text)
    clipboardManager.setPrimaryClip(clipData)
    Toast.makeText(context, context.getText(R.string.resultScreen_textCopiedToast), Toast.LENGTH_LONG).show()
}