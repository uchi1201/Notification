package com.android.example.notification


import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.android.example.notification.databinding.ActivityMainBinding
import com.android.example.notification.ui.notification.NotificationManageFragment
import com.android.example.notification.ui.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("UnspecifiedImmutableFlag")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView
        navView.labelVisibilityMode= BottomNavigationView.LABEL_VISIBILITY_LABELED
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)


//        ローカルプッシュ通知機能
        val alarmMgr: AlarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent: PendingIntent = Intent(application, AlarmReceiver::class.java).let { intent ->
            var yosangaku =  27000
            var riyougaku = 1000

//                デバッグは条件変更のたびにアンスト＋通知ONすること
            var msg1 = if (yosangaku < riyougaku){
                "予算額を超過しているよ！"
            } else {
                var kekka = yosangaku - riyougaku
                kekka.toString()+ "円余裕があります。"
            }

            var msg2 = if (yosangaku < riyougaku){
                "お得にポイント活用して節約しよう！！"
            } else {
                "まだまだ余裕があるよ。気になってたものが買えちゃうかも！"
            }

            intent.putExtra("TITLE",msg1)
            intent.putExtra("BODY",msg2)
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }

        // 今月の25日15:00に設定
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.DATE, 10)
        calendar.set(Calendar.HOUR_OF_DAY, 12)
        calendar.set(Calendar.MINUTE,38)
        calendar.set(Calendar.SECOND, 0)

        // 現在日時が今月の25日15:00を過ぎていた場合は、翌月の25日15:00を設定する
        if (System.currentTimeMillis() > calendar.timeInMillis) {
            calendar.add(Calendar.MONTH, 1)
        }

        //calendar.set(2023, 1, 3, 16, 8, 0) // テスト用、Month値は0始まり

        //設定日時にアラームを実行
        alarmMgr.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            alarmIntent
        )
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel( // 一意のチャンネルID
            "channel_id_sample",  // 設定に表示されるチャンネル名
            "プッシュ通知",  // チャンネルの重要度
            // 重要度によって表示箇所が異なる
            NotificationManager.IMPORTANCE_DEFAULT
        )
        // チャンネルの登録
        manager.createNotificationChannel(channel)


    }

}