package com.example.whatsappsample.domain.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class OutboxWorker_AssistedFactory_Impl implements OutboxWorker_AssistedFactory {
  private final OutboxWorker_Factory delegateFactory;

  OutboxWorker_AssistedFactory_Impl(OutboxWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public OutboxWorker create(Context arg0, WorkerParameters arg1) {
    return delegateFactory.get(arg0, arg1);
  }

  public static Provider<OutboxWorker_AssistedFactory> create(
      OutboxWorker_Factory delegateFactory) {
    return InstanceFactory.create(new OutboxWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<OutboxWorker_AssistedFactory> createFactoryProvider(
      OutboxWorker_Factory delegateFactory) {
    return InstanceFactory.create(new OutboxWorker_AssistedFactory_Impl(delegateFactory));
  }
}
