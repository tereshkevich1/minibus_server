package com.example

import com.example.dao.DatabaseFactory
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.network.tls.certificates.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory
import java.io.File

fun main() {
    // https
    val keyStoreFile = File("build/keystore.jks")
    val keyStore = buildKeyStore {
        certificate("sampleAlias") {
            password = "foobar"
            domains = listOf("127.0.0.1", "0.0.0.0", "localhost", "192.168.100.4")
        }
    }
    keyStore.saveToFile(keyStoreFile, "123456")

    val environment = applicationEngineEnvironment {
        log = LoggerFactory.getLogger("ktor.application")
        connector {
            port = 8080
        }
        sslConnector(
            keyStore = keyStore,
            keyAlias = "sampleAlias",
            keyStorePassword = { "123456".toCharArray() },
            privateKeyPassword = { "foobar".toCharArray() }) {
            port = 8443
            keyStorePath = keyStoreFile
        }
        module(Application::module)
    }

    embeddedServer(Netty, environment).start(wait = true)
}
    // cl

fun Application.module() {
    DatabaseFactory.init()
    configureSerialization()
    configureRouting()
}
