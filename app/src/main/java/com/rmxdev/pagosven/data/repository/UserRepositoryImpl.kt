package com.rmxdev.pagosven.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rmxdev.pagosven.domain.entities.User
import com.rmxdev.pagosven.domain.repository.UserRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : UserRepository {
    override suspend fun loginUser(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signupUser(
        name: String,
        surname: String,
        dni: String,
        phone: String,
        alias: String,
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user ?: throw Exception("User is null")

            val user = User(
                id = firebaseUser.uid,
                name = name,
                surname = surname,
                dni = dni,
                phone = phone,
                email = email,
                password = password,
                alias = alias,
                balance = 0.0
            )

            firestore.collection("users").document(firebaseUser.uid).set(user).await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getNameUser(): String {
        return try {
            val userId = firebaseAuth.currentUser?.uid ?: throw Exception("Usuario no autenticado")
            val snapshot = firestore.collection("users").document(userId).get().await()
            snapshot.getString("name") ?: "Usuario desconocido"
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }

    override suspend fun getBalanceUser(): Double {
        val userId = firebaseAuth.currentUser?.uid
        return if (userId != null) {
            // Obtener el balance del usuario desde Firestore o tu fuente de datos
            val userDoc = firestore.collection("users").document(userId).get().await()
            userDoc.getDouble("balance") ?: 0.0
        } else {
            0.0 // Si no hay usuario logueado, retornamos un balance de 0.0
        }
    }
}