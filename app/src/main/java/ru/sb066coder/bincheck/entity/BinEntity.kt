package ru.sb066coder.bincheck.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sb066coder.bincheck.dto.*
import ru.sb066coder.bincheck.dto.Number

@Entity
data class BinEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bin: Int
) {
    fun fromEntity() = bin

    companion object {
        fun toEntity(bin: Int) = BinEntity(0, bin)
    }
}