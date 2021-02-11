package dalian.razvan.cucer.core.baseClasses

import android.content.DialogInterface

interface BaseFragmentView {

    fun showProgressBar(show : Boolean)

    fun showPopup(text : String)
    fun showPopup(resId : Int)
    fun showPopup(text: String, onClickListener: DialogInterface.OnClickListener?, onDismissClickListener: DialogInterface.OnDismissListener?)

    fun goBack()

    fun isActivityStateSaved() : Boolean

    fun isActivityVisible() : Boolean
}