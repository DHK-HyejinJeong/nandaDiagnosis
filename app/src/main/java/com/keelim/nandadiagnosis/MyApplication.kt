/*
 * Designed and developed by 2020 keelim (Jaehyun Kim)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.keelim.nandadiagnosis

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.keelim.nandadiagnosis.data.repository.theme.ThemeRepository
import com.keelim.nandadiagnosis.utils.AppOpenManager
import com.keelim.nandadiagnosis.utils.ComponentLogger
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {

  @Inject
  lateinit var themeRepository: ThemeRepository

  @Inject
  lateinit var componentLogger: ComponentLogger

  private val appCoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

  private lateinit var appOpenManager: AppOpenManager

  override fun onCreate() {
    super.onCreate()

    appOpenManager = AppOpenManager(this) // 콜드 부팅에서 복귀시 ad
    componentLogger.initialize(this)

    appCoroutineScope.launch {
      AppCompatDelegate.setDefaultNightMode(
        themeRepository.getUserTheme().firstOrNull() ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
      )
    }
  }
}
