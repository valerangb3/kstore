package com.vgb3.kstore.data.utils.map

import com.vgb3.kstore.data.datasource.local.db.entities.VaultRecordTypeEntity
import com.vgb3.kstore.domain.model.output.RecordType

fun VaultRecordTypeEntity.toRecordType(): RecordType = RecordType(
    id = this.id,
    type = this.code
)