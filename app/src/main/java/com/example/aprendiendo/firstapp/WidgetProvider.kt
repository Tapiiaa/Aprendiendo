package com.example.aprendiendo.firstapp

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.aprendiendo.R

class WidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        ItemRepository.init(context) // Inicializar el repositorio

        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_layout)

            // Configurar texto del widget
            val items = ItemRepository.getItems()
            val displayText = if (items.isEmpty()) {
                "No hay items añadidos"
            } else {
                items.joinToString("\n")
            }
            views.setTextViewText(R.id.widgetText, displayText)

            // Configurar intent para redirigir a MainActivity
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widgetContainer, pendingIntent)

            // Actualizar widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

