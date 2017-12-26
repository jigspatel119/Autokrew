package com.autokrew.utils;



import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class Pref {
	/*---------------String--------------*/
	/*---------------int----------------*/
	/*---------------boolean----------------*/
	/*---------------XML----------------*/
	private static SharedPreferences sharedPreferences = null;

	public static void openPref(Context context) {

		sharedPreferences = context.getSharedPreferences(Constant.PREF_FILE,
				Context.MODE_PRIVATE);
		
	}

	public static String getValue(Context context, String key,
			String defaultValue) {
		Pref.openPref(context);
		String result = Pref.sharedPreferences.getString(key, defaultValue);
		Pref.sharedPreferences = null;
		return result;
	}

	public static void setValue(Context context, String key, int value) {
		Pref.openPref(context);
		Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
		prefsPrivateEditor.putInt(key, value);
		prefsPrivateEditor.commit();
		prefsPrivateEditor = null;
		Pref.sharedPreferences = null;
	}
	
	public static int getValue(Context context, String key,
			int defaultValue) {
		Pref.openPref(context);
		int result = Pref.sharedPreferences.getInt(key, defaultValue);
		Pref.sharedPreferences = null;
		return result;
	}

	public static void setValue(Context context, String key, String value) {
		Pref.openPref(context);
		Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
		prefsPrivateEditor.putString(key, value);
		prefsPrivateEditor.commit();
		prefsPrivateEditor = null;
		Pref.sharedPreferences = null;
	}
	public static boolean getValue(Context context, String key,
			boolean defaultValue) {
		Pref.openPref(context);
		boolean result = Pref.sharedPreferences.getBoolean(key, defaultValue);
		Pref.sharedPreferences = null;
		return result;
	}

	public static void setValue(Context context, String key, boolean value) {
		Pref.openPref(context);
		Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
		prefsPrivateEditor.putBoolean(key, value);
		prefsPrivateEditor.commit();
		prefsPrivateEditor = null;
		Pref.sharedPreferences = null;
	}
	
	public static void deletAll(Context context) {
	Pref.openPref(context);
	
	Pref.sharedPreferences.edit().clear().commit();
	}
}
