package com.example.learningdatastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import kotlinx.coroutines.flow.first

class GenericPreferenceDataStore {

    private suspend inline fun <reified Q : Any> save(key: String, value: Q, dataStore: DataStore<Preferences>) {
        val dataStoreKey = preferencesKey<Q>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    private suspend fun read(key: String, dataStore: DataStore<Preferences>): String? {
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}
