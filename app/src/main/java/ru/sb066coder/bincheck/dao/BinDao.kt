package ru.sb066coder.bincheck.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.sb066coder.bincheck.dto.CardInfo
import ru.sb066coder.bincheck.entity.BinEntity

@Dao
interface BinDao {

    @Query("SELECT * FROM BinEntity ORDER BY id DESC")
    fun getAll(): List<BinEntity>

    @Query("SELECT * FROM BinEntity WHERE bin = :bin")
    fun getByBin(bin: Int): BinEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(binEntity: BinEntity)
}
