package com.iapps.libs.helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.iapps.common_library.R;

import org.joda.time.DateTime;

public abstract class NotificationGenerator {
    private Context ctx;
    private Intent  intent;

    public NotificationGenerator(Context ctx, Intent intent) {
        this.ctx = ctx;
        this.intent = intent;
    }

    public Notification build() {
        NotificationManager notificationManager = (NotificationManager) ctx
                .getSystemService(Context.NOTIFICATION_SERVICE);
        int          currentapiVersion = Build.VERSION.SDK_INT;
        Notification notification      = null;

        PendingIntent pendingIntent = null;
        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_UPDATE_CURRENT);
            pendingIntent = PendingIntent.getActivity(ctx, 0, intent, 0);
        }

        if (currentapiVersion < Build.VERSION_CODES.HONEYCOMB) {
            // Not supporting this
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                    ctx);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (pendingIntent != null)
                    builder = builder.setContentIntent(pendingIntent);

                builder.setSmallIcon(icon()).setTicker(title()).setWhen(when())
                       .setAutoCancel(true).setContentTitle(title())
                       .setPriority(Notification.PRIORITY_HIGH)
                       .setContentText(content());
            } else {
                if (pendingIntent != null)
                    builder = builder.setContentIntent(pendingIntent);

                builder.setSmallIcon(icon()).setTicker(title()).setWhen(when())
                       .setAutoCancel(true).setContentTitle(title())
                       .setContentText(content());
            }

            if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP && color() > 0)
                builder.setColor(ctx.getResources().getColor(color()));

            if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP)
                builder.setSmallIcon(iconLollipop());

            if (doSound()) {
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                builder.setSound(defaultSoundUri);
            }

            if (doVibrate()) {
                builder.setVibrate(new long[]{1000, 1000});
            }

            notification = builder.build();
            notificationManager.notify((int) when(), notification);
        }

        return notification;
    }

    public String title() {
        return ctx.getString(R.string.app_name);
    }

    public int icon() {
        return R.drawable.ic_logo;
    }

    public int iconLollipop() {
        return R.drawable.ic_logo;
    }

    public abstract String content();

    public long when() {
        return DateTime.now().getMillis();
    }

    public int color() {
        return 0;
    }

    public boolean doVibrate() {
        return true;
    }

    public boolean doSound() {
        return true;
    }

}
