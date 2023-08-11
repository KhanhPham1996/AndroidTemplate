package com.hrs.network
sealed class Failure : Throwable() {

    object NetworkConnection : Failure()

    object TimeoutConnection : Failure()

    class UnknownFailure(val unknown: Throwable?) : Failure()

    class ServerError(val code: Int?, val error: String?) : Failure()

    /**
     * Extend this class for feature specific failures.
     */
    abstract class FeatureFailure : Failure()
}
