package lk.eclk.locationservice.internal

enum class AuthState {
    LOGGED_IN,
    NEED_LOGIN
}

enum class AuthResponseState {
    AUTHENTICATED,
    UNAUTHENTICATED,
    NO_CONNCECTIVITY,
    ERROR,
}