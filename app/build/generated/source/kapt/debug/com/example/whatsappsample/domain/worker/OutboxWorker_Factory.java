package com.example.whatsappsample.domain.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.example.whatsappsample.data.local.dao.OutboxMessageDao;
import com.example.whatsappsample.data.remote.ChatRemoteDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class OutboxWorker_Factory {
  private final Provider<OutboxMessageDao> outboxMessageDaoProvider;

  private final Provider<ChatRemoteDataSource> chatRemoteDataSourceProvider;

  private OutboxWorker_Factory(Provider<OutboxMessageDao> outboxMessageDaoProvider,
      Provider<ChatRemoteDataSource> chatRemoteDataSourceProvider) {
    this.outboxMessageDaoProvider = outboxMessageDaoProvider;
    this.chatRemoteDataSourceProvider = chatRemoteDataSourceProvider;
  }

  public OutboxWorker get(Context appContext, WorkerParameters workerParams) {
    return newInstance(appContext, workerParams, outboxMessageDaoProvider.get(), chatRemoteDataSourceProvider.get());
  }

  public static OutboxWorker_Factory create(Provider<OutboxMessageDao> outboxMessageDaoProvider,
      Provider<ChatRemoteDataSource> chatRemoteDataSourceProvider) {
    return new OutboxWorker_Factory(outboxMessageDaoProvider, chatRemoteDataSourceProvider);
  }

  public static OutboxWorker newInstance(Context appContext, WorkerParameters workerParams,
      OutboxMessageDao outboxMessageDao, ChatRemoteDataSource chatRemoteDataSource) {
    return new OutboxWorker(appContext, workerParams, outboxMessageDao, chatRemoteDataSource);
  }
}
