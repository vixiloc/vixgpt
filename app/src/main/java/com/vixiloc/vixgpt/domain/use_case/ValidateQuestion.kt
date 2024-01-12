package com.vixiloc.vixgpt.domain.use_case

import android.content.Context
import com.vixiloc.vixgpt.R
import com.vixiloc.vixgpt.utils.Resource

class ValidateQuestion(private val context: Context) {
    operator fun invoke(question: String): Resource<Boolean> {
        when {
            question.isEmpty() -> {
                return Resource.Error(context.getString(R.string.question_blank_error))
            }

            question.length > 2000 -> {
                return Resource.Error(context.getString(R.string.question_exceed_max_error))
            }

            else -> {
                return Resource.Success(true)
            }
        }
    }
}