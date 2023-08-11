package com.banquemisr.alertservice.service

import com.banquemisr.alertservice.model.SendNotificationRequest
import com.banquemisr.alertservice.model.SendNotificationResponse
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Service

@Service
class FirebaseMessagingService {
    fun sendNotification(sendNotificationRequest: SendNotificationRequest?): SendNotificationResponse {
        if (sendNotificationRequest == null) {
            return SendNotificationResponse("No Body found")
        } else if (sendNotificationRequest.recipeientToken == null) {
            return SendNotificationResponse("No Token found")
        } else {
            val notification =
                Notification.builder()
                    .setTitle(sendNotificationRequest.title)
                    .setBody(sendNotificationRequest.body)
                    .build()

            val message = Message.builder()
                .setToken(sendNotificationRequest.recipeientToken)
                .setNotification(notification)
                .putAllData(sendNotificationRequest.data)
                .build()

            return try {
                FirebaseMessaging.getInstance().send(message)
                SendNotificationResponse("Notification sent successfully")

            } catch (e: Exception) {
                SendNotificationResponse("Notification failed to send with exception: ${e.message}")

            }
        }


    }
}