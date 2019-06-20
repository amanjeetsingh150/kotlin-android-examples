package pramonow.com.widgetexample

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import android.content.Intent
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var editText = findViewById<EditText>(R.id.edit_text)
        var button = findViewById<Button>(R.id.button)

        val intent = Intent(this, SampleWidget::class.java)
        intent.action = "ACTIVITY_ACTION"

        //This action will send broadcast to update the widget
        button.setOnClickListener { _ ->
            AppWidgetManager.getInstance(application).getAppWidgetIds(ComponentName(application,SampleWidget::class.java))
            intent.putExtra("name", editText.text.toString())
            sendBroadcast(intent)}

    }
}
