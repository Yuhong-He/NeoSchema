package org.yunnanhistory.neoschema

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NeoSchemaApplication

fun main(args: Array<String>) {
    runApplication<NeoSchemaApplication>(*args)
}
