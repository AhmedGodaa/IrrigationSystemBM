package com.banquemisr.alertservice.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "firebase.credential")
data class FirebaseCredentialTransformed(
    var type: String? = null,
    var project_id: String? = null,
    var private_key_id: String? = null,
    var private_key: String? = null,
    var client_email: String? = null,
    var client_id: String? = null,
    var auth_uri: String? = null,
    var token_uri: String? = null,
    var auth_provider_x509_cert_url: String? = null,
    var client_x509_cert_url: String? = null
) {
     fun convertToString(): String {
        return "{\n" +
                "  \"type\": \"$type\",\n" +
                "  \"project_id\": \"$project_id\",\n" +
                "  \"private_key_id\": \"$private_key_id\",\n" +
                "  \"private_key\": \"$private_key\",\n" +
                "  \"client_email\": \"$client_email\",\n" +
                "  \"client_id\": \"$client_id\",\n" +
                "  \"auth_uri\": \"$auth_uri\",\n" +
                "  \"token_uri\": \"$token_uri\",\n" +
                "  \"auth_provider_x509_cert_url\": \"$auth_provider_x509_cert_url\",\n" +
                "  \"client_x509_cert_url\": \"$client_x509_cert_url\"\n" +
                "}"
    }
}