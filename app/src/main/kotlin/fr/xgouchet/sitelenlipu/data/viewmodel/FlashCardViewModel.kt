package fr.xgouchet.sitelenlipu.data.viewmodel

import android.app.Application
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akuleshov7.ktoml.Toml
import com.akuleshov7.ktoml.TomlInputConfig
import com.akuleshov7.ktoml.source.decodeFromStream
import fr.xgouchet.sitelenlipu.data.model.CardDisplay
import fr.xgouchet.sitelenlipu.data.model.WordInfo
import fr.xgouchet.sitelenlipu.data.model.WordId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.Boolean
import kotlin.random.Random

class FlashCardViewModel(application: Application) : AndroidViewModel(application) {

    private val toml = Toml(
        inputConfig = TomlInputConfig(
            // allow/prohibit unknown names during the deserialization, default false
            ignoreUnknownNames = true,
            // allow/prohibit empty values like "a = # comment", default true
            allowEmptyValues = true,
            // allow/prohibit null values like "a = null", default true
            allowNullValues = true,
            // allow/prohibit escaping of single quotes in literal strings, default true
            allowEscapedQuotesInLiteralStrings = true,
            // allow/prohibit processing of empty toml, if false - throws an InternalDecodingException exception, default is true
            allowEmptyToml = true,
            // allow/prohibit default values during the deserialization, default is false
            ignoreDefaultValues = false,
        ),
    )

    private val _wordInfo = MutableLiveData<WordInfo>()
    private val _secretDisplay = MutableLiveData<CardDisplay>()
    private val _fullDisplay = MutableLiveData<CardDisplay>()

    val wordInfo: LiveData<WordInfo> = _wordInfo
    val secretDisplay: LiveData<CardDisplay> = _secretDisplay
    val fullDisplay: LiveData<CardDisplay> = _fullDisplay

    fun fetchWord(wordId: WordId) {
        CoroutineScope(Dispatchers.IO).launch {
            val word = try {
                readWordData(wordId)

            } catch (e: Exception) {
                Log.e("FlashCardViewModel", "Error parsing file for $wordId", e)
                WordInfo(
                    id = wordId.name.lowercase(),
                    puVerbatim = emptyMap(),
                    representations = emptyMap(),
                    seeAlso = emptyList(),
                    deprecated = false
                )
            }

            viewModelScope.launch {
                _wordInfo.value = word
            }
        }
    }

    fun fetchRandomWord() {
        CoroutineScope(Dispatchers.IO).launch {
            val randomWordId = WordId.getRandom()
            val word = try {
                readWordData(randomWordId)

            } catch (e: Exception) {
                Log.e("FlashCardViewModel", "Error parsing file for $randomWordId", e)
                WordInfo(
                    id = randomWordId.name.lowercase(),
                    puVerbatim = emptyMap(),
                    representations = emptyMap(),
                    seeAlso = emptyList(),
                    deprecated = false
                )
            }
            viewModelScope.launch {
                _wordInfo.value = word
            }
        }
    }


    @Suppress("DEPRECATION")
    fun refreshDisplayStates() {
        val preferencesName = PreferenceManager.getDefaultSharedPreferencesName(getApplication())
        val sharedPrefs = getApplication<Application>().getSharedPreferences(preferencesName, 0)

        val secret = CardDisplay(
            sitelenPona = sharedPrefs.getBoolean("secret-display:sitelen-pona", true),
            sitelenPilin = sharedPrefs.getBoolean("secret-display:sitelen-pilin", true),
            sitelenJelo = sharedPrefs.getBoolean("secret-display:sitelen-jelo", true),
            tokiPona = sharedPrefs.getBoolean("secret-display:toki-pona", true),
            tokiJan = sharedPrefs.getBoolean("secret-display:toki-jan", false),
        )
        val full = CardDisplay(
            sitelenPona = sharedPrefs.getBoolean("full-display:sitelen-pona", true),
            sitelenPilin = sharedPrefs.getBoolean("full-display:sitelen-pilin", true),
            sitelenJelo = sharedPrefs.getBoolean("full-display:sitelen-jelo", true),
            tokiPona = sharedPrefs.getBoolean("full-display:toki-pona", true),
            tokiJan = sharedPrefs.getBoolean("full-display:toki-jan", true),
        )
        viewModelScope.launch {
            _secretDisplay.value = secret
            _fullDisplay.value = full
        }
    }

    private suspend fun readWordData(id: WordId): WordInfo {
        val fileName = "metadata/" + id.name.lowercase() + ".toml"
        val assetInputStream = getApplication<Application>().assets.open(fileName)
        return toml.decodeFromStream<WordInfo>(assetInputStream)
    }
}