package ai.ftech.travelluxury.login

enum class LOGIN_STATE {
    EMPTY_EMAIL_FIELD,
    INVALID_EMAIL_FORMAT,
    EMPTY_PASSWORD_FIELD,
    INVALID_PASSWORD_FORMAT,
    WRONG_EMAIL_OR_PASSWORD,
    SUCCESS
}