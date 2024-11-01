package com.example.chefmate.core.domain.util.methodResult

import com.example.chefmate.R

sealed interface DataError: Error {
    enum class Network : DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        UNKNOWN;

        override fun getErrorMessageResId(): Int {
            return when (this) {
                REQUEST_TIMEOUT -> R.string.the_request_took_too_long_to_complete_please_try_again
                TOO_MANY_REQUESTS -> R.string.you_have_made_too_many_requests_in_a_short_time_please_wait_a_moment_and_try_again
                NO_INTERNET -> R.string.no_internet_connection_detected_please_check_your_network_settings_and_try_again
                PAYLOAD_TOO_LARGE -> R.string.the_data_you_are_trying_to_send_is_too_large_please_try_sending_smaller_data
                SERVER_ERROR -> R.string.an_error_occurred_on_the_server_please_try_again_later
                UNKNOWN -> R.string.an_unknown_error_occurred_please_try_again
            }
        }
    }
}