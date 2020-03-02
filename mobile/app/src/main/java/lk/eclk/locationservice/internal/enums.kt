package lk.eclk.locationservice.internal

enum class AuthState {
    LOGGED_IN,
    NEED_LOGIN
}

enum class ResponseStates {
    AUTHENTICATED,
    UNAUTHENTICATED,
    NO_CONNECTIVITY,
    ERROR,
    OK,
}