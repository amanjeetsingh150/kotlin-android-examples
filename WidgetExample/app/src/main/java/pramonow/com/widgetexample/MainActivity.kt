package pramonow.com.widgetexample

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.support.v4.view.accessibility.AccessibilityEventCompat.setAction
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
        button.setOnClickListener { View ->
            AppWidgetManager.getInstance(application).getAppWidgetIds(ComponentName(application,SampleWidget::class.java))
            intent.putExtra("name", editText.text.toString())
            sendBroadcast(intent)}

    }
}
