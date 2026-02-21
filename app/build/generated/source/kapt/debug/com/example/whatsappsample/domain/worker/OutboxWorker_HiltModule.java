package com.example.whatsappsample.domain.worker;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.annotation.processing.Generated;

@Generated("androidx.hilt.AndroidXHiltProcessor")
@Module
@InstallIn(SingletonComponent.class)
@OriginatingElement(
    topLevelClass = OutboxWorker.class
)
public interface OutboxWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.example.whatsappsample.domain.worker.OutboxWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(OutboxWorker_AssistedFactory factory);
}
