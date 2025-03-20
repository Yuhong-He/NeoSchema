package org.yunnanhistory.neoschema.exceptions

import org.springframework.http.HttpStatus

open class ClientErrorException(
    val errorCode: HttpStatus = HttpStatus.BAD_REQUEST,
    message: String?
) : RuntimeException(message)

class ResourceExistsException(
    identifier: String
) : ClientErrorException(
    errorCode=HttpStatus.CONFLICT,
    message="Resource already exists: $identifier"
)
