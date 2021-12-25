package dev.dslam.newsapi.models

import androidx.room.TypeConverter
import org.json.JSONObject

class SourceTypeConverters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromSource(source: Source): String {
            return JSONObject().apply {
                put("id", source.id)
                put("name", source.name)
            }.toString()
        }

        @TypeConverter
        @JvmStatic
        fun toSource(source: String): Source {
            val json = JSONObject(source)
            return Source(json.getString("id"), json.getString("name"))
        }
    }
}