package com.rmxdev.pagosven.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.rmxdev.pagosven.domain.entities.User
import com.rmxdev.pagosven.domain.repository.TransferMoneyRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TransferMoneyRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : TransferMoneyRepository {
    override suspend fun getUserById(userId: String): User? {
        return firestore.collection("users").document(userId).get().await()
            .toObject(User::class.java)
    }

    override suspend fun findUserByIdentifier(identifier: String): User? {
        val snapshot = firestore.collection("users")
            .whereEqualTo("dni", identifier)
            .get().await()

        return snapshot.documents.firstOrNull()?.toObject(User::class.java)
            ?: firestore.collection("users").whereEqualTo("alias", identifier).get()
                .await().documents.firstOrNull()?.toObject(User::class.java)
            ?: firestore.collection("users").whereEqualTo("phone", identifier).get()
                .await().documents.firstOrNull()?.toObject(User::class.java)
    }

    override suspend fun executeTransaction(
        senderId: String,
        newSenderBalance: Float,
        receiverId: String,
        newReceiverBalance: Float
    ): Result<Unit> {
        return try {
            firestore.runTransaction{transaction ->
                val senderRef = firestore.collection("users").document(senderId)
                val receiverRef = firestore.collection("users").document(receiverId)

                transaction.update(senderRef, "balance", newSenderBalance)
                transaction.update(receiverRef, "balance", newReceiverBalance)
            }.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}