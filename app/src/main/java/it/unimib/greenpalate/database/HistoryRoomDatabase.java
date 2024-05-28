package it.unimib.greenpalate.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import it.unimib.greenpalate.model.History;

@Database(entities = {History.class}, version = 1)
public abstract class HistoryRoomDatabase extends RoomDatabase {

    public abstract HistoryDao historyDao();

    private static volatile HistoryRoomDatabase INSTANCE;

    public static HistoryRoomDatabase getInstance(Context context){
        if(INSTANCE == null)
            INSTANCE = Room
                    .databaseBuilder(context.getApplicationContext(),
                            HistoryRoomDatabase.class, "history_database")
                    .build();
        return INSTANCE;
    }
}
