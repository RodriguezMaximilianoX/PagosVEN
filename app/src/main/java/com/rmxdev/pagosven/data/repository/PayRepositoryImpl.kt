package com.rmxdev.pagosven.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.rmxdev.pagosven.domain.repository.PayRepository
import javax.inject.Inject

class PayRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : PayRepository {

    override suspend fun pay(payerId: String, receiverId: String, amount: Double): Result<Unit> {
        return try {
            firestore.runTransaction { transaction ->
                val payerRef = firestore.collection("users").document(payerId)
                val receiverRef = firestore.collection("users").document(receiverId)

                val payerSnapshot = transaction.get(payerRef)
                val receiverSnapshot = transaction.get(receiverRef)

                val payerBalance = payerSnapshot.getDouble("balance") ?: 0.0
                val receiverBalance = receiverSnapshot.getDouble("balance") ?: 0.0

                if (payerBalance < amount) {
                    throw Exception("Saldo insuficiente para realizar el pago")
                }

                // Actualizar los saldos
                transaction.update(payerRef, "balance", payerBalance - amount)
                transaction.update(receiverRef, "balance", receiverBalance + amount)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}