package com.vgb3.kstore.domain.repository

import com.vgb3.kstore.domain.model.output.RecordType
import kotlinx.coroutines.flow.Flow

interface RecordTypeRepository {
    fun observeRecordType(): Flow<List<RecordType>>
}