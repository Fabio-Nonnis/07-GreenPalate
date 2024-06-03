package it.unimib.greenpalate.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Upsert;

import java.util.List;

import it.unimib.greenpalate.model.History;

@Dao
public interface HistoryDao {

    @Upsert
    public void upsert(History history);
    @Insert
    public void insert(History history);
    @Query("DELETE FROM history WHERE barcode = :barcode")
    public void delete(String barcode);
    @Query ("SELECT * FROM history WHERE barcode LIKE :id LIMIT 1")
    History findById(int id);
    @Query ("SELECT * FROM history")
    List<History> getAll();
    @Query ("SELECT * FROM history WHERE barcode IN (:foodIds)")
    List<History> loadAllByIds(int[]foodIds);
    @Query("DELETE FROM history where uid NOT IN (SELECT uid from history ORDER BY uid DESC LIMIT 7)")
    void clear();
}
