package com.strong.blog.powerblog.exception

enum class ErrorType(val message: String) {
    RESOURCE_NOT_FOUND("리소스를 찾을 수 없습니다."),
    RESOURCE_UPDATE_ERROR("리소스 업데이트에 실패했습니다."),
    ILLEGAL_ARGUMENT("잘못된 요청입니다."),
    ILLEGAL_STATEMENT("잘못된 상태변경 요청입니다."),
    REQUEST_PARSE_ERROR("정보가 입력되지 않았거나, 잘못된 요청입니다."),
    EXTERNAL_API_ERROR("외부 API 호출 에러입니다."),

    UNKNOWN("알 수 없는 에러입니다."),
}
