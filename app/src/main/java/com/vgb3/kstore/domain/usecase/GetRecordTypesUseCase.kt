package com.vgb3.kstore.domain.usecase

import com.vgb3.kstore.domain.model.output.RecordType
import com.vgb3.kstore.domain.repository.RecordTypeRepository
import kotlinx.coroutines.flow.Flow

class GetRecordTypesUseCase(
    private val recordTypeRepository: RecordTypeRepository
) {
    operator fun invoke(): Flow<List<RecordType>> = recordTypeRepository.observeRecordType()
}