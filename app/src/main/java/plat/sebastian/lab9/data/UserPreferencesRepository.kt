package plat.sebastian.lab9.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settings"
)

class UserPreferencesRepository(
    private val context: Context
) {

    private val nameKey = stringPreferencesKey("name")

    val userName: Flow<String?> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(androidx.datastore.preferences.core.emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[nameKey]?.takeIf { it.isNotBlank() }
        }

    suspend fun saveName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[nameKey] = name.trim()
        }
    }

    suspend fun clearName() {
        context.dataStore.edit { preferences ->
            preferences.remove(nameKey)
        }
    }
}