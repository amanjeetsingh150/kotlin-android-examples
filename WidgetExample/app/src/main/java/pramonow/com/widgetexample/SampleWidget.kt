package pramonow.com.widgetexample

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.content.ComponentName

/**
 * Implementation of App Widget functionality.
 */

class SampleWidget : AppWidgetProvider() {

    private val ACTION_SIMPLEAPPWIDGET = "ACTION_BROADCASTWIDGETSAMPLE"
    private val WIDGET_TAG = "SAMPLE_WIDGET_TAG"

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            Log.d(WIDGET_TAG,"update")
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        Log.d(WIDGET_TAG,"enabled")
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
        Log.d(WIDGET_TAG,"disabled")
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        //This will be called when the button is clicked
        if ((intent.action.equals(ACTION_SIMPLEAPPWIDGET))) {
            val views = RemoteViews(context.packageName, R.layout.sample_widget)
            views.setTextViewText(R.id.widget_button, "HIT ME AGAIN!")
            val appWidget = ComponentName(context, SampleWidget::class.java!!)
            val appWidgetManager = AppWidgetManager.getInstance(context)

            appWidgetManager.updateAppWidget(appWidget, views)
        }
        //This will be called by broadcast inside the activity
        else if ((intent.action.equals("ACTIVITY_ACTION"))) {
            val views = RemoteViews(context.packageName, R.layout.sample_widget)

            val text = intent.getStringExtra("name")

            views.setTextViewText(R.id.widget_button, text)
            val appWidget = ComponentName(context, SampleWidget::class.java!!)
            val appWidgetManager = AppWidgetManager.getInstance(context)

            appWidgetManager.updateAppWidget(appWidget, views)
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

        // Construct the RemoteViews object
        val views = RemoteViews(context.packageName, R.layout.sample_widget)
        views.setTextViewText(R.id.appwidget_text, "WIDGET INITIALIZED")

        //Sample intent for action example
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        views.setOnClickPendingIntent(R.id.widget_button, pendingIntent)

        // Construct an Intent which is pointing this class.
        val intentTwo = Intent(context, SampleWidget::class.java)
        intentTwo.action = ACTION_SIMPLEAPPWIDGET
        val pendingIntentTwo = PendingIntent.getBroadcast(context, 0, intentTwo, PendingIntent.FLAG_UPDATE_CURRENT)

        views.setOnClickPendingIntent(R.id.google_button, pendingIntent)
        views.setOnClickPendingIntent(R.id.widget_button, pendingIntentTwo)

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}

