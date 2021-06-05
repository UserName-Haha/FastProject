package com.haha.fastproject.base.widget.materialedittext.validation.impl

import com.haha.fastproject.base.R
import com.haha.fastproject.base.widget.materialedittext.validation.METValidator
import me.goldze.mvvmhabit.utils.ResUtils

/**
 * @author zhe.chen
 * @date 2021/5/13 10:34
 * Des:
 */
class NickNameValidator : METValidator("昵称为空或超长") {

    private val MAX_LENGHT = 25

    override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
        if (isEmpty) {
            setErrorMessage(ResUtils.getString(R.string.nickanme_validator_empty))
        }
        if (text.length > MAX_LENGHT) {
            setErrorMessage(ResUtils.getString(R.string.nickanme_validator_exceed_length))
        }
        return !isEmpty && text.length <= MAX_LENGHT
    }


}