package ru.sb066coder.bincheck.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sb066coder.bincheck.db.AppDb
import ru.sb066coder.bincheck.dto.CardInfo
import ru.sb066coder.bincheck.repository.AppRepository
import ru.sb066coder.bincheck.repository.Repository

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: Repository = AppRepository(AppDb.getInstance(application).binDao())

    private val _data = MutableLiveData(CardInfo())
    val data: LiveData<CardInfo>
        get() = _data
    private val _history = MutableLiveData(emptyList<Int>())
    val history: LiveData<List<Int>>
        get() = _history
    val added = repository.added

    init {
        getHistory()
    }

    fun getHistory() {
        _history.value = repository.getHistory()
    }

    fun getCardInfo(bin: Int) = viewModelScope.launch {
        try {
            _data.postValue(repository.getBinInfo(bin))
        } catch (e: Exception) {
            Log.i("AAA", "error in viewModel")
        }
    }


    fun clearCardInfo() {
        _data.postValue(CardInfo())
    }
}