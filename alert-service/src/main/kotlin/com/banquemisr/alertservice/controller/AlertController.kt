package com.banquemisr.alertservice.controller

import com.banquemisr.alertservice.model.SendNotificationRequest
import com.banquemisr.alertservice.model.SendNotificationResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api")
interface AlertController {

    @PostMapping("/sendNotification")
    fun sendNotification(@RequestBody(required = false) sendNotificationRequest: SendNotificationRequest): ResponseEntity<SendNotificationResponse>
}