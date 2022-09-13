/*
 * Copyright 2019, The Android Open Source Project
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

package com.example.android.trackmysleepquality.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SleepDatabaseDao{

    // 1 entity 1 dao
    //1. insert new night into table
     @Insert
     suspend fun insert(night: SleepNight)

    //2. Update Night in table
    @Update
    suspend fun update(night: SleepNight)

    //3. Get specific Night based on key
    @Query("SELECT * from daily_sleep_quality_table WHERE nightId = :key")
    suspend fun get(key: Long): SleepNight?

    //4. get all nights to display
    @Query("SELECT * from daily_sleep_quality_table ORDER BY nightId DESC")
    suspend  fun getAllNights(): LiveData<List<SleepNight>>

    // 5. Get the most recent night
    @Query("SELECT * from daily_sleep_quality_table ORDER BY nightID DESC LIMIT 1")
    suspend  fun getTonight(): SleepNight?

    //6. Delete All Entries
    @Query("DELETE FROM daily_sleep_quality_table")
    suspend  fun clear()
}


