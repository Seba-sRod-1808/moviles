package plat.sebastian.lab9.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import plat.sebastian.lab9.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters ORDER BY id")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacterById(id: Int): Flow<CharacterEntity?>

    @Query("SELECT * FROM characters WHERE isFavorite = 1 ORDER BY id")
    fun getFavoriteCharacters(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("UPDATE characters SET isFavorite = :favorite WHERE id = :id")
    suspend fun updateFavorite(id: Int, favorite: Boolean)

    @Query("SELECT COUNT(*) FROM characters")
    suspend fun countCharacters(): Int

    @Query("DELETE FROM characters")
    suspend fun deleteAll()
}