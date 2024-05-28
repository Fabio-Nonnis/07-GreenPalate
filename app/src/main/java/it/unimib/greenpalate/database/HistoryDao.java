package it.unimib.greenpalate.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Upsert;

import java.util.List;

import it.unimib.greenpalate.model.History;

@Dao
public interface HistoryDao {

    @Upsert
    public void upsert(History history);
    @Delete
    public void delete(History history);
    @Query ("SELECT * FROM history WHERE id LIKE :id LIMIT 1")
    History findById(int id);
    @Query ("SELECT * FROM history")
    List<History> getAll();
    @Query ("SELECT * FROM history WHERE id IN (:foodIds)")
    List<History> loadAllByIds(int[]foodIds);
}
