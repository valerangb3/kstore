package com.vgb3.kstore.presentation.map

import com.vgb3.kstore.domain.model.output.RecordType
import com.vgb3.kstore.presentation.ui.model.RecordTypeUi

fun RecordType.toUi() = RecordTypeUi(
    id = this.id,
    type = this.type
)