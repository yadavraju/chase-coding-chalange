package com.relayapp.live.domain.exception

import com.relayapp.live.domain.annotation.ExceptionType
import com.relayapp.live.domain.model.Dialog
import com.relayapp.live.domain.model.Redirect
import com.relayapp.live.domain.model.Tag

// These exception handle code I have written long time ago putting all of those here too
sealed class BaseException(
    open val code: Int,
    @ExceptionType val type: Int,
    override val message: String?
) : Throwable(message) {

    data class AlertException(
        override val code: Int,
        override val message: String,
        val title: String? = null
    ) : BaseException(code, ExceptionType.ALERT, message)

    data class InlineException(
        override val code: Int,
        val tags: List<Tag>
    ) : BaseException(code, ExceptionType.INLINE, null)

    data class RedirectException(
        override val code: Int,
        val redirect: Redirect
    ) : BaseException(code, ExceptionType.REDIRECT, null)

    data class SnackBarException(
        override val code: Int = -1,
        override val message: String
    ) : BaseException(code, ExceptionType.SNACK, message)

    data class ToastException(
        override val code: Int,
        override val message: String
    ) : BaseException(code, ExceptionType.TOAST, message)

    data class DialogException(
        override val code: Int,
        val dialog: Dialog
    ) : BaseException(code, ExceptionType.DIALOG, null)

    data class OnPageException(
        override val code: Int,
        override val message: String
    ) : BaseException(code, ExceptionType.ON_PAGE, message)
}
