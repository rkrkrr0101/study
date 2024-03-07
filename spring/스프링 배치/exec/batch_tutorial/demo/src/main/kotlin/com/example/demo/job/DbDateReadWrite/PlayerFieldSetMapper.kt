package com.example.demo.job.DbDateReadWrite

import com.example.demo.job.FileDataReadWrite.dto.Player
import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.transform.FieldSet

class PlayerFieldSetMapper:FieldSetMapper<Player> {
    override fun mapFieldSet(fieldSet: FieldSet): Player {
        return Player(
            fieldSet.readString(0),
            fieldSet.readString(1),
            fieldSet.readString(2),
            fieldSet.readString(3),
            fieldSet.readInt(4),
            fieldSet.readInt(5),
        )
    }
}