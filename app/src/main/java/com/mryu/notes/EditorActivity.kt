package com.mryu.notes

import android.content.res.Configuration
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.text.set
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.activity_editor.*

class EditorActivity : AppCompatActivity() {
    private var mode = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
//        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        window.statusBarColor= Color.TRANSPARENT
        val nightmode = applicationContext.getResources().getConfiguration().uiMode and  Configuration.UI_MODE_NIGHT_MASK
        if(nightmode == Configuration.UI_MODE_NIGHT_YES) setTheme(R.style.AppTheme_Dark) else setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        toolbar2.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item!!.itemId){
                    R.id.action_save -> saveNote()
                    R.id.action_edit -> editNote()
                }
                return true
            }

        })
        mode.value = intent.getStringExtra("mode")
        mode.observeForever {
            toolbar2.menu.clear()
            when(it){
                "EDITOR" -> {
                    toolbar2.inflateMenu(R.menu.menu_editor)
                    txtTitle.visibility=View.GONE
                    edtTitle.visibility=View.VISIBLE
                    txtContent.visibility=View.GONE
                    edtContent.visibility=View.VISIBLE
                    bottombar.visibility=View.VISIBLE
                }
                "PREVIEW" ->{
                    toolbar2.inflateMenu(R.menu.menu_preview)
                    txtTitle.visibility=View.VISIBLE
                    edtTitle.visibility=View.GONE
                    txtContent.visibility=View.VISIBLE
                    edtContent.visibility=View.GONE
                    bottombar.visibility=View.GONE
                }
            }
        }
    }

    fun saveNote(){
//        val title = txtTitle.text
//        val content = editText.text
//        val file= File(applicationContext.filesDir,"$title.rtf")
//        OutputStreamWriter(FileOutputStream(file)).write(content.toString())
        txtTitle.text=edtTitle.text
        txtContent.text=edtContent.text
        mode.value="PREVIEW"
    }
    fun editNote(){
        mode.value="EDITOR"
    }
}
