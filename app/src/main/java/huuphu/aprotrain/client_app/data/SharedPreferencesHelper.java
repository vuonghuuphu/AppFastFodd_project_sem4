package huuphu.aprotrain.client_app.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.google.gson.Gson;

import java.util.Objects;

import huuphu.aprotrain.client_app.Model.EnumApp;

public class SharedPreferencesHelper {
    public static void Save (String key, String value, View view){
        SharedPreferences preferences = view.getContext().getSharedPreferences(EnumApp.SharedPreferencesName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String ReadUser (String key,Context context){
        SharedPreferences pref = context.getSharedPreferences(EnumApp.SharedPreferencesName,Context.MODE_MULTI_PROCESS);
        if (pref.getString(key, null) == null){
            return null;
        }else {
            return pref.getString(key, null);
        }
    }

    public static void delete(String key, Context context){
        SharedPreferences preferences = context.getSharedPreferences(EnumApp.SharedPreferencesName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
        SharedPreferencesHelper.ReadUser(EnumApp.KeyToken,context);
    }

    public static void SaveCart (String key, String value, View view){
        SharedPreferences preferences = view.getContext().getSharedPreferences(EnumApp.order, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String data = ReadUser(key,view.getContext());
        if (!Objects.equals(data, "")){

        }
        editor.putString(key, value);
        editor.apply();
    }

    public static void Save1 (String key, String value,Context context){
        SharedPreferences preferences = context.getSharedPreferences(EnumApp.SharedPreferencesName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
