package com.namseox.app_tcare.module

import android.content.Context
import com.namseox.app_tcare.data.api.retrofit.apihelper.ApiHelper
import com.namseox.app_tcare.data.repository.ApiRepository
import com.namseox.app_tcare.data.repository.RoomRepository
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModeule {
    @Singleton
    @Provides
    fun providerSharedPreference(@ApplicationContext appContext: Context): SharedPreferenceUtils {
        return SharedPreferenceUtils.getInstance(appContext)
    }
    @Singleton
    @Provides
    fun providerApi(@ApplicationContext context: Context): ApiHelper {
        return ApiHelper(context)
    }

    @Singleton
    @Provides
    fun providerApiRepository( apiHelper: ApiHelper): ApiRepository {
        return ApiRepository(apiHelper)
    }
    @Singleton
    @Provides
    fun providerRoomRepository(@ApplicationContext context: Context): RoomRepository {
        return RoomRepository(context)
    }
}