package com.banquemisr.alertservice.controller

import com.banquemisr.alertservice.model.SendNotificationRequest
import com.banquemisr.alertservice.model.SendNotificationResponse
import com.banquemisr.alertservice.service.FirebaseMessagingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AlertControllerImpl(
    private val firebaseMessagingService: FirebaseMessagingService
) : AlertController {


    override fun sendNotification(sendNotificationRequest: SendNotificationRequest): ResponseEntity<SendNotificationResponse> {
        return ResponseEntity.ok(firebaseMessagingService.sendNotification(sendNotificationRequest))
    }
}