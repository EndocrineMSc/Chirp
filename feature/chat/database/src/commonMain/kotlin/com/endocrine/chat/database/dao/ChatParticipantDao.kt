package com.endocrine.chat.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.endocrine.chat.database.entities.ChatParticipantEntity

@Dao
interface ChatParticipantDao {

    @Upsert
    suspend fun upsertParticipant(participant: ChatParticipantEntity)

    @Upsert
    suspend fun upsertParticipants(participants: List<ChatParticipantEntity>)

    @Query("SELECT * FROM chatparticipantentity")
    suspend fun getAllParticipants(): List<ChatParticipantEntity>
}