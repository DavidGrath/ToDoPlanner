package com.example.todoplanner.framework

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.todoplanner.Constants
import com.example.todoplanner.Constants.Companion.TASK_STATUS_DONE
import com.example.todoplanner.R
import com.example.todoplanner.TempObservable
import com.example.todoplanner.TempPlanner
import com.example.todoplanner.domain.entities.framework.database.TaskData

class DayPlannerService() : Service() {

    val CHANNEL_ID = "day_running"

    companion object {
        var dayPlannerBinder: DayPlannerBinder? = null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.extras?.let { bundle ->
            when (bundle.getInt(Constants.SERVICE_TYPE)) {
                Constants.SERVICE_TYPE_TASK_NEXT -> {
                    dayPlannerBinder?.setNextTask(bundle.getInt(Constants.TASK_ID))
                }
                Constants.SERVICE_TYPE_TASK_DONE -> {
                    dayPlannerBinder?.notifyTaskDone(bundle.getInt(Constants.TASK_ID))
                }
                else -> {

                }
            }

        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        if (dayPlannerBinder == null) dayPlannerBinder = DayPlannerBinder(this)
        return dayPlannerBinder
    }

    class DayPlannerBinder(val service: Service) : Binder(), TempPlanner {

        val CHANNEL_ID = "day_running"
        val MARK_DONE_REQ_CODE = 101

        val manager: AlarmManager? = ContextCompat.getSystemService(service, AlarmManager::class.java)

        val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(service)

        val idStream = TempObservable<Int>()
        val notificationStream = TempObservable<TaskData>()

        fun setNextTask(taskId: Int) {
            idStream.updateValue(taskId)
        }

        fun notifyTaskDone(id: Int) {
            //TODO Arbitrary values since only id and done are needed
            notificationStream.updateValue(TaskData(id, 0L, 0L, 0L, 0, "", TASK_STATUS_DONE))
        }

        override fun addTaskObserver(observer: TempObservable.TempObserver<TaskData>) {
            notificationStream.addObserver(observer)
        }

        override fun notify(task: TaskData) {
            task.let {
                notificationManager.notify(Constants.FOREGROUND_DAY_RUNNING, notifyTask(it))
            }
        }

        override fun addCurrentTaskIDListener(observer: TempObservable.TempObserver<Int>) {
            idStream.addObserver(observer)
        }

        fun notifyTask(task: TaskData): Notification {
            val markDoneAction = NotificationCompat.Action.Builder(
                    R.drawable.ic_baseline_check_24,
                    "Done",
                    PendingIntent.getService(service,
                            MARK_DONE_REQ_CODE,
                            Intent(service, DayPlannerService::class.java)
                                    .also {
                                        it.putExtras(Bundle().apply {
                                            putInt(Constants.TASK_ID, task.id)
                                            putString(Constants.TASK_TITLE, task.name)
                                            putInt(Constants.SERVICE_TYPE, Constants.SERVICE_TYPE_TASK_DONE)
                                        })
                                    },
                            PendingIntent.FLAG_ONE_SHOT)
            ).build()
            return NotificationCompat.Builder(service, CHANNEL_ID)
                    .setContentTitle(task.name)
                    .setSmallIcon(R.drawable.ic_baseline_dashboard_24)
                    .setContentIntent(NavDeepLinkBuilder(service)
                            .setGraph(R.navigation.nav_graph)
                            .setDestination(R.id.navbar_main_home)
                            .createPendingIntent())
                    .addAction(markDoneAction)
                    .build()

        }

        override fun dayStart(): Int {
            return 0
        }

        override fun dayEnd(): Int {
            return 960
        }

        override fun startDay(day: List<TaskData>) {
            notifyBulk(day)
            val first = day.first()
            first?.let {
                service.startForeground(Constants.FOREGROUND_DAY_RUNNING, notifyTask(it))
            }
        }

        override fun notifyBulk(tasks: List<TaskData>) {
            for (task in tasks) {
                val intent = Intent(service, TempBroadcastReceiver::class.java).also {
                    it.putExtras(
                            Bundle().apply {
                                putInt(Constants.TASK_ID, task.id)
//                                putString(Constants.TASK_TITLE, task.name)
                                putInt(Constants.SERVICE_TYPE, Constants.SERVICE_TYPE_TASK_NEXT)
                            }
                    )
                }
                manager?.setExact(AlarmManager.RTC_WAKEUP, task.start, PendingIntent
                        .getBroadcast(service, Constants.BROADCAST_TASK_CHANGED, intent, PendingIntent.FLAG_ONE_SHOT))
            }
        }

        override fun endDay() {

        }
    }
}