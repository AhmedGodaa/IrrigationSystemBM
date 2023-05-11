package com.banquemisr.alertservice.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service
import java.io.*
import javax.annotation.PostConstruct

@Service
@EnableConfigurationProperties(FirebaseCredential::class)
class FirebaseInitializer(private val firebaseCredential: FirebaseCredential) {


    @PostConstruct
    fun onStart() {
        logger.info("Initializing Firebase App...")
        try {
            initializeFirebaseApp()
        } catch (e: IOException) {
            logger.error("Initializing Firebase App $e")
        }
    }

    @Throws(IOException::class)
    private fun initializeFirebaseApp() {
        val inputStream = firebaseCredential.toString().byteInputStream().readBytes().inputStream()
        val credentials = GoogleCredentials.fromStream(inputStream)
        val options = FirebaseOptions.Builder()
            .setCredentials(credentials)
            .build()
        FirebaseApp.initializeApp(options)
    }


    companion object {
        private val logger = LoggerFactory.getLogger(FirebaseInitializer::class.java)
    }
}