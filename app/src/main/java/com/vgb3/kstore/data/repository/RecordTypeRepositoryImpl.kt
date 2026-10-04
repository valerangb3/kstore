package com.vgb3.kstore.data.repository

import com.vgb3.kstore.data.datasource.local.db.dao.VaultDao
import com.vgb3.kstore.data.utils.map.toRecordType
import com.vgb3.kstore.domain.model.output.RecordType
import com.vgb3.kstore.domain.repository.RecordTypeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecordTypeRepositoryImpl(
    private val dao: VaultDao
): RecordTypeRepository {
    override fun observeRecordType(): Flow<List<RecordType>> {
        return dao
            .getTypes()
            .map { types ->
                types.map {
                    it.toRecordType()
                }
            }
    }
}