package com.example.whatsappsample.data.local.converter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u0018\u0010\t\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\nH\u0007J\u0014\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007J\u0018\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/example/whatsappsample/data/local/converter/Converters;", "", "()V", "gson", "Lcom/google/gson/Gson;", "fromOutboxStatus", "", "value", "Lcom/example/whatsappsample/data/local/entity/OutboxStatus;", "fromStringList", "", "toOutboxStatus", "toStringList", "app_debug"})
public final class Converters {
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    public Converters() {
        super();
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromStringList(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> toStringList(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String fromOutboxStatus(@org.jetbrains.annotations.Nullable()
    com.example.whatsappsample.data.local.entity.OutboxStatus value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final com.example.whatsappsample.data.local.entity.OutboxStatus toOutboxStatus(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
}