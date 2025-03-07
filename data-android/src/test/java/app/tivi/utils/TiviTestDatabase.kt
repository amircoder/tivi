/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.tivi.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.tivi.data.TiviDatabase
import app.tivi.data.TiviTypeConverters
import app.tivi.data.daos.ShowFtsDao
import app.tivi.data.entities.Episode
import app.tivi.data.entities.EpisodeWatchEntry
import app.tivi.data.entities.FollowedShowEntry
import app.tivi.data.entities.LastRequest
import app.tivi.data.entities.PopularShowEntry
import app.tivi.data.entities.RecommendedShowEntry
import app.tivi.data.entities.RelatedShowEntry
import app.tivi.data.entities.Season
import app.tivi.data.entities.ShowTmdbImage
import app.tivi.data.entities.TiviShow
import app.tivi.data.entities.TraktUser
import app.tivi.data.entities.TrendingShowEntry
import app.tivi.data.entities.WatchedShowEntry
import app.tivi.data.resultentities.ShowDetailed
import app.tivi.data.views.FollowedShowsLastWatched
import app.tivi.data.views.FollowedShowsNextToWatch
import app.tivi.data.views.FollowedShowsWatchStats

@Database(
    entities = [
        TiviShow::class,
        TrendingShowEntry::class,
        PopularShowEntry::class,
        TraktUser::class,
        WatchedShowEntry::class,
        FollowedShowEntry::class,
        Season::class,
        Episode::class,
        RelatedShowEntry::class,
        EpisodeWatchEntry::class,
        LastRequest::class,
        ShowTmdbImage::class,
        RecommendedShowEntry::class,
        FakeTiviShowFts::class,
    ],
    views = [
        FollowedShowsWatchStats::class,
        FollowedShowsLastWatched::class,
        FollowedShowsNextToWatch::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(TiviTypeConverters::class)
abstract class TiviTestDatabase : RoomDatabase(), TiviDatabase {
    override fun showFtsDao(): ShowFtsDao = object : ShowFtsDao() {
        override suspend fun search(filter: String): List<ShowDetailed> {
            return emptyList()
        }
    }
}
