package com.banquemisr.alertservice

import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class AlertServiceApplication

fun main(args: Array<String>) {
    runApplication<AlertServiceApplication>(*args)
}

