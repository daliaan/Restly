package dalian.razvan.cucer.core.baseClasses

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {

        if (event.action == MotionEvent.ACTION_DOWN) {

            val view = currentFocus

            if (view is EditText) {
                val rect = Rect()
                view.getGlobalVisibleRect(rect)
                val isKeyboardOpen = !rect.contains(event.rawX.toInt(), event.rawY.toInt())

                if (isKeyboardOpen) {
                    view.clearFocus()
                    (view.parent as View).requestFocus()
                    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}