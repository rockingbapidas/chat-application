package com.example.whatsappsample.data.remote.xmpp

import com.example.whatsappsample.data.remote.UserRemoteDataSource
import com.example.whatsappsample.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jivesoftware.smack.roster.Roster
import org.jivesoftware.smackx.vcardtemp.VCardManager
import org.jxmpp.jid.impl.JidCreate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class XmppUserDataSourceImpl @Inject constructor(
    private val xmppManager: XmppManager
) : UserRemoteDataSource {

    private val connection get() = xmppManager.getConnection()
    private val roster get() = connection?.let { Roster.getInstanceFor(it) }
    private val vCardManager get() = connection?.let { VCardManager.getInstanceFor(it) }

    override fun getUsers(): Flow<List<UserDto>> = flow {
        val users = roster?.entries?.map { entry ->
            UserDto(
                id = entry.jid.toString(),
                name = entry.name ?: entry.jid.toString(),
                status = "" // In a real app, get presence status
            )
        } ?: emptyList()
        emit(users)
    }

    override suspend fun getUser(userId: String): Flow<UserDto> = flow {
        val jid = JidCreate.entityBareFrom(userId)
        val vCard = vCardManager?.loadVCard(jid)
        emit(
            UserDto(
                id = userId,
                name = vCard?.nickName ?: vCard?.firstName ?: userId,
                status = vCard?.getField("status") ?: ""
            )
        )
    }

    override suspend fun updateProfile(user: UserDto) {
        val vCard = vCardManager?.loadVCard()
        vCard?.nickName = user.name
        vCard?.setField("status", user.status)
        vCardManager?.saveVCard(vCard)
    }

    override suspend fun searchUsers(query: String): Flow<List<UserDto>> = flow {
        // XMPP search is complex (XEP-0055), returning empty for now
        emit(emptyList())
    }
}
