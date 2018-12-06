/*
package com.autokrew.utils;

*/
/**
 * Created by rana on 8/29/17.
 *//*


import android.Manifest;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import java.util.Random;

public class MyService extends Service {
    public static NotificationManager notificationManager=null;
    private LocationManager locManager;
    private LocationListener locListener = new myLocationListener();
    static final Double EARTH_RADIUS = 6371.00;
    private static final int GPS_TIME_INTERVAL = 60000; // get gps location every 1 min
    private static final int GPS_DISTANCE= 50;
    private boolean gps_enabled = false;
    private boolean network_enabled = false;

    private Handler handler = new Handler();
    private Handler handler1 = new Handler();
    Thread t;
    int kkkaa=0;
    public String latiude_current,longtityude_current;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart(Intent intent, int startid) {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Toast.makeText(getBaseContext(), "Service Started", Toast.LENGTH_SHORT).show();

        final Runnable r = new Runnable() {
            public void run() {
                Log.e("Debug", "Hello");
              location();

                handler1.postDelayed(this, 60000);
            }
        };
                handler1.postDelayed(r, 60000);
        return START_STICKY;
    }

    public void location() {
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         long INTERVAL = 1000 * 2;
        long FASTEST_INTERVAL = 1000 * 1;
        try {
            gps_enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        Log.e("Debug", "in on create.. 2");
        if (gps_enabled) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, GPS_TIME_INTERVAL, GPS_DISTANCE, locListener);
//            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
            Log.v("Debug", "Enabled..");
        }
        if (network_enabled) {
            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, GPS_TIME_INTERVAL, GPS_DISTANCE, locListener);
//            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
            Log.e("Debug", "Disabled..");
        }
        Log.e("Debug", "in on create..3");
    }

    private class myLocationListener implements LocationListener {
        double lat_old = 0.0;
        double lon_old = 0.0;
        double lat_new;
        double lon_new;
        double time = 10;
        double speed = 0.0;

        @Override
        public void onLocationChanged(Location location) {

            if (location != null) {
                if (ActivityCompat.checkSelfPermission(MyService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MyService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locManager.removeUpdates(locListener);
               final  double longitude = location.getLongitude();
                final double latitude =  location.getLatitude();

                DatabaseHandler dbQuery = null;
                kkkaa=0;
                try {
                   Log.e("mapdirection>>>","mapdirection>>>123>>>");
                    dbQuery = new DatabaseHandler(App.getContext());
                    dbQuery.getReadableDatabase();
                    dbQuery.getAllbyRandomID_service(App.getContext());

                }catch (Exception e )
                {
//                    Log.e("selected----","selected----exc3----"+e);
                }
                finally {
                    if (dbQuery != null) {
                        dbQuery.close();
                        dbQuery = null;
                    }
                }



        long val =(LocationsFragment.alList_driveList.size()+1)*3000;
                new CountDownTimer(val, 3000) {

                    public void onTick(long millisUntilFinished) {

                        //here you can have your logic to set text to edittext

                        Settings_variables.Lati_Detial="";
                        Settings_variables.Longti_Detial="";
                        Log.e("Debug", "mservice>>checking_lon>>if_kka>0>list>>"+LocationsFragment.alList_driveList.size()+">>>"+kkkaa+">>>");
                        if(LocationsFragment.alList_driveList!=null && LocationsFragment.alList_driveList.size()>0 && kkkaa< LocationsFragment.alList_driveList.size())
                        {
                            Log.e("Debug", "mservice>>checking_lon>>if_kka>0>list>123>"+LocationsFragment.alList_driveList.size());
                        */
/*    Log.e("Debug", "mservice>>checking_lon>>if_kka>0>>>"+kkkaa+"<<<<");
                            Log.e("Debug", "mservice>>checking_lon>>if_kka>0>>>"+kkkaa+"<<<<"+LocationsFragment.alList_driveList.size()+"<<name>>"+LocationsFragment.alList_driveList.get(kkkaa).get(DatabaseHandler.KEY_NAME).toString()+"<def_lat>>"+latitude+"<<def_lo>"+longitude+"<<<>>"+LocationsFragment.alList_driveList.get(kkkaa).get(DatabaseHandler.KEY_LAT).toString()+"<<>>>"+LocationsFragment.alList_driveList.get(kkkaa).get(DatabaseHandler.KEY_LONGI).toString());
*//*

                            Location loc1 = new Location("");
                            loc1.setLatitude(latitude);
                            loc1.setLongitude(longitude);

                            Location loc2 = new Location("");
                            loc2.setLatitude(Double.parseDouble(LocationsFragment.alList_driveList.get(kkkaa).get(DatabaseHandler.KEY_LAT).toString()));
                            loc2.setLongitude(Double.parseDouble(LocationsFragment.alList_driveList.get(kkkaa).get(DatabaseHandler.KEY_LONGI).toString()));

                            float distanceInMeters = loc1.distanceTo(loc2);
                            latiude_current= String.valueOf(latitude);
                            latiude_current= String.valueOf(latitude);
                            longtityude_current= String.valueOf(longitude);

                            float distancemeter = Float.valueOf((LocationsFragment.alList_driveList.get(kkkaa).get(DatabaseHandler.KEY_OLDDISTANCE).toString()));




                            DatabaseHandler db = new DatabaseHandler(App.getContext());
                            db.updateolddistance(""+distanceInMeters,LocationsFragment.alList_driveList.get(kkkaa).get(DatabaseHandler.KEY_ID_RANDOM_UNIQUE).toString());
//                            if(distanceInMeters<=Settings_variables.radiusdistance && distancemeter>=Settings_variables.radiusdistance)
//                           Toast.makeText(App.getContext(),"notification"+distanceInMeters+">>>"+Settings_variables.radiusdistance+">>>"+distancemeter+">>",Toast.LENGTH_LONG).show();
                        if(distanceInMeters<=Settings_variables.radiusdistance && distancemeter>=Settings_variables.radiusdistance)
                          {

                                String name__ = LocationsFragment.alList_driveList.get(kkkaa).get(DatabaseHandler.KEY_NAME).toString();
                                Settings_variables.Lati_Detial = LocationsFragment.alList_driveList.get(kkkaa).get(DatabaseHandler.KEY_LAT).toString();
                                Settings_variables.Longti_Detial = LocationsFragment.alList_driveList.get(kkkaa).get(DatabaseHandler.KEY_LONGI).toString();
                                addNotification(name__, Settings_variables.Lati_Detial, Settings_variables.Longti_Detial,latiude_current,longtityude_current);
                    }



                        }

                        kkkaa=  kkkaa+1;
                    }

                    public void onFinish()
                    {

                    }

                }.start();






            }
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

               private void createGeofencePendingIntent()
               {
                Intent intent = new Intent( App.getContext(), GeofenceTrasitionService.class);
                 PendingIntent.getService(
                App.getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
    }

    public void createGeofencePendingIntent2(int i) {
        Intent intent = new Intent( App.getContext(), GeofenceTrasitionService.class);
        PendingIntent.getService(
                App.getContext(), i, intent, PendingIntent.FLAG_UPDATE_CURRENT );
    }

    public double CalculationByDistance(double lat1, double lon1, double lat2, double lon2) {
        double Radius = EARTH_RADIUS;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return Radius * c;
    }

    */
/**
     * Gets distance in meters, coordinates in RADIAN
     *//*

    private static double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371000; // for haversine use R = 6372.8 km instead of 6371 km
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        //double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return 2 * R * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        // simplify haversine:
        //return 2 * R * 1000 * Math.asin(Math.sqrt(a));
    }

    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }


    public void AlarmactivationMorning2() {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            //final int _id2 = (int) System.currentTimeMillis();
            Random random = new Random();
            int m = random.nextInt(9999 - 1000) + 1000;

            Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
            notificationIntent.addCategory("android.intent.category.DEFAULT");
            notificationIntent.putExtra("DATA", "aman");
                PendingIntent.getBroadcast(App.getContext(), m, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


    }




    public  void addNotification(String name, String lati_Detial, String longi_detail,String latiude_current,String longtityude_current) {

        Log.e("miles", "mservice>>checking_lon>>if_kka>>addnotif>>helper"+latiude_current+">>>>"+longtityude_current );
//        Utils.setlocation(latiude_current,longtityude_current,App.getContext());
        if(Utils.getlogincredet(App.getContext()))
        {
            Log.e("miles", "mservice>>checking_lon>>if_kka>>addnotif>>helper>>!23"+latiude_current+">>>>"+longtityude_current );

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Random r = new Random();
                int i1 = r.nextInt(80 - 65) + 65;
                Intent notificationIntent = new Intent(App.getContext(), Helper_fragment.class);
                notificationIntent.putExtra("notification", "Yes");
                notificationIntent.putExtra("Lat_i",lati_Detial);
                notificationIntent.putExtra("Long_i",longi_detail);
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                int color = getResources().getColor(R.color.app_header_color);
                String CHANNEL_ID = "GDL";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "GDL", importance);
                Notification notification = new Notification.Builder(this)
                        .setContentText("You are near to "+name)
                        .setColor(color)
                        .setSmallIcon(getNotificationIcon())
                        .setChannelId(CHANNEL_ID)
                        .setContentIntent(pendingIntent).build();

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.createNotificationChannel(mChannel);

                mNotificationManager.notify(i1 , notification);
            }
            else{
                Random r = new Random();
                int i1 = r.nextInt(80 - 65) + 65;

                Intent intent = new Intent(App.getContext(), Helper_fragment.class);
                intent.putExtra("notification", "Yes");
                intent.putExtra("Lat_i",lati_Detial);
                intent.putExtra("Long_i",longi_detail);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(App.getContext(), 0 */
/*Request code*//*
, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                      NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(App.getContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("GDL")
                        .setContentText("You are near to "+name)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);
                     notificationManager =
                        (NotificationManager) App.getContext().getSystemService(Context.NOTIFICATION_SERVICE);


                notificationManager.notify(i1, notificationBuilder.build());
            }



        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Random r = new Random();
                int i1 = r.nextInt(80 - 65) + 65;
                Intent notificationIntent = new Intent(App.getContext(), Login.class);
                notificationIntent.putExtra("notification", "Yes");
                notificationIntent.putExtra("Lat_i", lati_Detial);
                notificationIntent.putExtra("Long_i", longi_detail);
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                int color = getResources().getColor(R.color.app_header_color);
                String CHANNEL_ID = "GDL";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "GDL", importance);
                Notification notification = new Notification.Builder(this)
                        .setContentText("You are near to " + name)
                        .setColor(color)
                        .setSmallIcon(getNotificationIcon())
                        .setChannelId(CHANNEL_ID)
                        .setContentIntent(pendingIntent).build();

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.createNotificationChannel(mChannel);

                mNotificationManager.notify(i1, notification);
            } else {
                Random r = new Random();
                int i1 = r.nextInt(80 - 65) + 65;

                Intent intent = new Intent(App.getContext(), Login.class);
                intent.putExtra("notification", "Yes");
                intent.putExtra("Lat_i", lati_Detial);
                intent.putExtra("Long_i", longi_detail);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(App.getContext(), 0 */
/*Request code*//*
, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(App.getContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("GDL")
                        .setContentText("You are near to " + name)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);
                notificationManager =
                        (NotificationManager) App.getContext().getSystemService(Context.NOTIFICATION_SERVICE);


                notificationManager.notify(i1, notificationBuilder.build());
            }
        }

}

    private int getNotificationIcon() {
        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.mipmap.logo_1 : R.mipmap.logo_1;
    }

}
*/
