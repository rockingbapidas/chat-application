package com.example.whatsappsample.data.repository;

import androidx.work.WorkManager;
import com.example.whatsappsample.data.local.AppPreferences;
import com.example.whatsappsample.data.local.wrapper.ChatDaoWrapper;
import com.example.whatsappsample.data.remote.ChatRemoteDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ChatRepositoryImpl_Factory implements Factory<ChatRepositoryImpl> {
  private final Provider<ChatDaoWrapper> localDataSourceProvider;

  private final Provider<ChatRemoteDataSource> remoteDataSourceProvider;

  private final Provider<WorkManager> workManagerProvider;

  private final Provider<AppPreferences> appPreferencesProvider;

  private ChatRepositoryImpl_Factory(Provider<ChatDaoWrapper> localDataSourceProvider,
      Provider<ChatRemoteDataSource> remoteDataSourceProvider,
      Provider<WorkManager> workManagerProvider, Provider<AppPreferences> appPreferencesProvider) {
    this.localDataSourceProvider = localDataSourceProvider;
    this.remoteDataSourceProvider = remoteDataSourceProvider;
    this.workManagerProvider = workManagerProvider;
    this.appPreferencesProvider = appPreferencesProvider;
  }

  @Override
  public ChatRepositoryImpl get() {
    return newInstance(localDataSourceProvider.get(), remoteDataSourceProvider.get(), workManagerProvider.get(), appPreferencesProvider.get());
  }

  public static ChatRepositoryImpl_Factory create(Provider<ChatDaoWrapper> localDataSourceProvider,
      Provider<ChatRemoteDataSource> remoteDataSourceProvider,
      Provider<WorkManager> workManagerProvider, Provider<AppPreferences> appPreferencesProvider) {
    return new ChatRepositoryImpl_Factory(localDataSourceProvider, remoteDataSourceProvider, workManagerProvider, appPreferencesProvider);
  }

  public static ChatRepositoryImpl newInstance(ChatDaoWrapper localDataSource,
      ChatRemoteDataSource remoteDataSource, WorkManager workManager,
      AppPreferences appPreferences) {
    return new ChatRepositoryImpl(localDataSource, remoteDataSource, workManager, appPreferences);
  }
}
