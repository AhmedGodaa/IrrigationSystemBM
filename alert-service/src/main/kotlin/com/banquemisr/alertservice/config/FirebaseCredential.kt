package com.banquemisr.alertservice.config

import org.springframework.boot.context.properties.ConfigurationProperties

/* In This class I inject firebase credentials from application.properties file
*  This way I will prevent provide firebase credentials in the project directory
*  then wen use kubernetes secrets to provide firebase secrets by using environment variables
* */
@ConfigurationProperties(prefix = "firebase")
data class FirebaseCredential(
    val type: String,
    val project_id: String,
    val private_key_id: String,
    val private_key: String,
    val client_email: String,
    val client_id: String,
    val auth_uri: String,
    val token_uri: String,
    val auth_provider_x509_cert_url: String,
    val client_x509_cert_url: String
) {


//    Instead serialize the object of firebase credentials to send as InputStream to FirebaseOptions.Builder()
//    I converted the object to json then to string and send this string as InputStream to FirebaseOptions.Builder()
//    Serialize the object may lead to corrupted data
    override fun toString(): String {
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


