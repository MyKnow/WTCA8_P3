package lotto.constant

private const val ERROR_PREFIX = "[ERROR] "

enum class ErrorType(message: String) {
    EMPTY_INPUT("빈 문자열은 입력할 수 없습니다."),
    INVALID_INTEGER("해당 문자열은 정수가 아닙니다."),
    INVALID_INPUT_COUNT("로또 번호는 6개여야 합니다."),
    INVALID_NUMBER_RANGE("로또 번호는 1에서 45 사이여야 합니다."),
    DUPLICATE_NUMBER("로또 번호는 서로 겹치지 않아야 합니다."),
    NOT_POSITIVE_NUMBER("양의 정수가 입력되어야 합니다."),
    NOT_MULTIPLE_OF_1000("구매 금액은 1000의 배수여야 합니다."),
    MESSAGE_ARGUMENT_NOT_MATCHED("메세지의 매개변수 갯수가 일치하지 않습니다"),
    NOT_ASCENDING_ORDER("오름차순 정렬되어 있지 않습니다.");

    val message: String = ERROR_PREFIX + message
}
