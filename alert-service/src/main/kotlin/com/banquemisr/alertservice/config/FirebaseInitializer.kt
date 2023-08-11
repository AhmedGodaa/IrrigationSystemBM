package com.banquemisr.alertservice.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import javax.annotation.PostConstruct

@Service
@EnableConfigurationProperties(FirebaseCredentialTransformed::class)
class FirebaseInitializer(private val firebaseCredentialTransformed: FirebaseCredentialTransformed) {

    private val logger = LoggerFactory.getLogger(FirebaseInitializer::class.java)

    @PostConstruct
    fun initializeFirebaseApp() {
        logger.info("Initializing Firebase Credentails for the App...")
        try {
            val credentials = loadFirebaseCredentials()
            val options = buildFirebaseOptions(credentials)
            FirebaseApp.initializeApp(options)
        } catch (e: IOException) {
            logger.error("Initializing Firebase App $e")
        }
    }

    @Throws(IOException::class)
    private fun loadFirebaseCredentials(): GoogleCredentials {
        val inputStream: InputStream = firebaseCredentialTransformed.convertToString().convertStringToStream()
        return GoogleCredentials.fromStream(inputStream)
    }

    private fun buildFirebaseOptions(credentials: GoogleCredentials): FirebaseOptions {
        return FirebaseOptions.builder()
            .setCredentials(credentials)
            .build()
    }

}

fun String.convertStringToStream(charset: Charset = Charsets.UTF_8): ByteArrayInputStream =
    ByteArrayInputStream(toByteArray(charset))