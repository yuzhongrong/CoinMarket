package com.zksg.lib_api.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.zksg.lib_api.song.AudioBean;
import com.zksg.lib_api.song.DailyRecommendSongsBean;
import com.zksg.lib_api.song.LatestAudioBean;

@Database(entities = {AudioBean.class, LatestAudioBean.class, DailyRecommendSongsBean.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {


    public abstract LatestDataDao getLatestSongDao();

    public abstract MusicPlayListDao getMusicPlayListDao();

    public abstract RecommendMusicDao getDailyRecommendDao();

    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "kudoud_data")
                    //允许在主线程访问数据库
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
