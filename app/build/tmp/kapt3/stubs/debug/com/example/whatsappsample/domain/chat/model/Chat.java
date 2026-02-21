package com.example.whatsappsample.domain.chat.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b!\b\u0087\b\u0018\u00002\u00020\u0001Bk\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0011J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010%\u001a\u00020\tH\u00c6\u0003J\t\u0010&\u001a\u00020\u000bH\u00c6\u0003J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u000fH\u00c6\u0003J\t\u0010*\u001a\u00020\u000fH\u00c6\u0003Jo\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u00c6\u0001J\u0013\u0010,\u001a\u00020\u000b2\b\u0010-\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010.\u001a\u00020\tH\u00d6\u0001J\t\u0010/\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0018R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u0015R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0013\u00a8\u00060"}, d2 = {"Lcom/example/whatsappsample/domain/chat/model/Chat;", "", "id", "", "participants", "", "lastMessage", "Lcom/example/whatsappsample/domain/chat/model/Message;", "unreadCount", "", "isGroup", "", "groupName", "groupPictureUrl", "createdAt", "", "updatedAt", "(Ljava/lang/String;Ljava/util/List;Lcom/example/whatsappsample/domain/chat/model/Message;IZLjava/lang/String;Ljava/lang/String;JJ)V", "getCreatedAt", "()J", "getGroupName", "()Ljava/lang/String;", "getGroupPictureUrl", "getId", "()Z", "getLastMessage", "()Lcom/example/whatsappsample/domain/chat/model/Message;", "name", "getName", "getParticipants", "()Ljava/util/List;", "getUnreadCount", "()I", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class Chat {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> participants = null;
    @org.jetbrains.annotations.Nullable()
    private final com.example.whatsappsample.domain.chat.model.Message lastMessage = null;
    private final int unreadCount = 0;
    private final boolean isGroup = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String groupName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String groupPictureUrl = null;
    private final long createdAt = 0L;
    private final long updatedAt = 0L;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.whatsappsample.domain.chat.model.Message component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    public final long component8() {
        return 0L;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.whatsappsample.domain.chat.model.Chat copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> participants, @org.jetbrains.annotations.Nullable()
    com.example.whatsappsample.domain.chat.model.Message lastMessage, int unreadCount, boolean isGroup, @org.jetbrains.annotations.Nullable()
    java.lang.String groupName, @org.jetbrains.annotations.Nullable()
    java.lang.String groupPictureUrl, long createdAt, long updatedAt) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    public Chat(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> participants, @org.jetbrains.annotations.Nullable()
    com.example.whatsappsample.domain.chat.model.Message lastMessage, int unreadCount, boolean isGroup, @org.jetbrains.annotations.Nullable()
    java.lang.String groupName, @org.jetbrains.annotations.Nullable()
    java.lang.String groupPictureUrl, long createdAt, long updatedAt) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getParticipants() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.whatsappsample.domain.chat.model.Message getLastMessage() {
        return null;
    }
    
    public final int getUnreadCount() {
        return 0;
    }
    
    public final boolean isGroup() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getGroupName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getGroupPictureUrl() {
        return null;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final long getUpdatedAt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    public Chat() {
        super();
    }
}