package org.yunnanhistory.neoschema.exceptions

import org.springframework.http.HttpStatus
import kotlin.reflect.KClass

open class ClientErrorException(
    val errorCode: HttpStatus = HttpStatus.BAD_REQUEST,
    message: String?
) : RuntimeException(message)

class ResourceNotFoundException(
    kClass: KClass<*>,
    identifier: String
) : ClientErrorException(
    errorCode=HttpStatus.NOT_FOUND,
    message="Resource not found: ${kClass.simpleName}($identifier)"
)

class ResourceExistsException(
    kClass: KClass<*>,
    identifier: String
) : ClientErrorException(
    errorCode=HttpStatus.CONFLICT,
    message="Resource already exists: ${kClass.simpleName}($identifier)"
)
