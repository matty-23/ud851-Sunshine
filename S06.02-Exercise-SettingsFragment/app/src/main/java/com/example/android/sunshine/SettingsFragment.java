package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import java.util.List;

/**
 * Created by my on 2/19/18.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);


        PreferenceScreen prefScreen = getPreferenceScreen();
        SharedPreferences sharedPrefs = prefScreen.getSharedPreferences();


        for (int i = 0; i < prefScreen.getPreferenceCount(); i++) {

            Preference pref = prefScreen.getPreference(i);
            if (!(pref instanceof CheckBoxPreference)) {
                setPreferenceSummary(pref, sharedPrefs.getString(pref.getKey(), ""));
            }

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        Preference pref = findPreference(s);

        if (pref != null && !(pref instanceof CheckBoxPreference)) {
            setPreferenceSummary(pref, sharedPreferences.getString(pref.getKey(), ""));
        }
    }

    private void setPreferenceSummary(Preference pref, Object obj) {

        if (pref instanceof EditTextPreference) {

            pref.setSummary(obj.toString());

        } else if (pref instanceof ListPreference) {

            ListPreference listPreference = (ListPreference) pref;
            int prefIndex = listPreference.findIndexOfValue(obj.toString());

            if (prefIndex >= 0) {
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }

    }
}
