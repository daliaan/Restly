package dalian.razvan.cucer.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dalian.razvan.cucer.R

class RestlySearchView: TextInputLayout {

    private var textInputEditText: TextInputEditText
    private var textInputLayout: TextInputLayout

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.restly_search_view, this, true)
        textInputLayout = findViewById(R.id.text_input_layout)
        textInputEditText = findViewById(R.id.text_input_edit_text)
    }
}