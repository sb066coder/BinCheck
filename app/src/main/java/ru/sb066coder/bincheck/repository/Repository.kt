package ru.sb066coder.bincheck.repository

import androidx.lifecycle.LiveData
import ru.sb066coder.bincheck.dto.CardInfo

interface Repository {
    val added: LiveData<Boolean>
    suspend fun getBinInfo(bin: Int): CardInfo
    fun getHistory(): List<Int>?
}