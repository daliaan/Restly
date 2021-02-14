package dalian.razvan.cucer.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import dalian.razvan.cucer.R

class RestlySearchView: ConstraintLayout {

    private var textInputEditText: TextInputEditText
    private var searchIcon: ImageView
    private var searchClear: TextView

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.restly_search_view, this, true)
        textInputEditText = findViewById(R.id.search_edit_text)
        searchIcon = findViewById(R.id.search_icon)
        searchClear = findViewById(R.id.search_clear)

        searchClear.setOnClickListener {
            textInputEditText.setText("")
        }
    }

    fun setHint(hint: String) {
        textInputEditText.hint = hint
    }
}