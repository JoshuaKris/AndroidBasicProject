package com.example.androidbasicproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.androidbasicproject.R;
import com.example.androidbasicproject.utils.AlarmReceiver;

/**
 * Alarm di aktifkan dahulu
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

        @Override
        public boolean onPreferenceTreeClick(Preference preference) {
            String key = preference.getKey();
            if (key.equals("language")) {
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                return true;
            } else if (key.equals("alarm")) {
                AlarmReceiver alarmReceiver = new AlarmReceiver();
                boolean check = getPreferenceManager().getSharedPreferences().getBoolean(key, false);
                if (check) {
                    alarmReceiver.setRepeatingAlarm(getContext(),
                            "Alarm 9:00 AM",
                            "This is Alarm for Github User App");
                } else {
                    alarmReceiver.cancelAlarm(getContext());
                }
                Toast.makeText(getContext(), String.valueOf(check), Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return true;
    }

}