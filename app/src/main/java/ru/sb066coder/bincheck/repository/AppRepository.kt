package ru.sb066coder.bincheck.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.sb066coder.bincheck.api.BinApi
import ru.sb066coder.bincheck.dao.BinDao
import ru.sb066coder.bincheck.dto.CardInfo
import ru.sb066coder.bincheck.entity.BinEntity

class AppRepository(private val binDao: BinDao) : Repository {

    private val _added = MutableLiveData(false)
    override val added: LiveData<Boolean>
        get() = _added

    override suspend fun getBinInfo(bin: Int): CardInfo {
        try {
            val response = BinApi.service.getBinInfo(bin)
            if (!response.isSuccessful) {
                throw RuntimeException(response.message())
            }
            return response.body()?.also {
                if (!getHistory().contains(bin)) {
                    binDao.insert(BinEntity.toEntity(bin))
                    _added.value = true
                } else {
                    _added.value = false
                }
            } ?: throw RuntimeException("body is null")
        } catch (e: Exception) {
            throw RuntimeException("unknown exception")
        }

    }

    override fun getHistory(): List<Int> {
        return binDao.getAll().map { it.fromEntity() }
    }

}
