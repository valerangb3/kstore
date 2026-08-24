package com.vgb3.kstore.data.datasource.local.db.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.vgb3.kstore.data.model.VaultItemType


@Entity(
    tableName = "vault_table",
    foreignKeys = [
        ForeignKey(
            entity = VaultCategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index(value = ["categoryId"]),
    ],
)
data class VaultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val createdAt: Long,
    val updatedAt: Long,
    val isFavorite: Boolean,
    val categoryId: Long?,
    val type: VaultItemType
)

@Entity(
    tableName = "vault_login_table",
    foreignKeys = [
        ForeignKey(
            entity = VaultEntity::class,
            parentColumns = ["id"],
            childColumns = ["vaultItemId"],
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class VaultLoginEntity(
    @PrimaryKey
    val vaultItemId: Long,
    val login: String,
    val password: String,
    val urlString: String,
)

data class VaultLoginItemEntity(
    @Embedded val vaultItem: VaultEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "vaultItemId"
    )
    val loginInfo: VaultLoginEntity
)

@Entity(tableName = "vault_bank_card_table")
data class VaultBankCardEntity(
    @PrimaryKey
    val vaultItemId: Long,
    val cardNumber: String,
    val cardholderName: String,
    val expirationMonth: Int,
    val expirationYear: Int,
    val cvv: String,
)

@Entity(tableName = "vault_secure_note_table")
data class VaultSecureNoteEntity(
    @PrimaryKey
    val vaultItemId: Long,
    val note: String,
)


@Entity(
    tableName = "vault_category_table",
    indices = [
        Index(value = ["key"], unique = true)
    ]
)
data class VaultCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val key: String,
    val sortOrder: Int,
)