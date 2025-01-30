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
                balance = 0f
            )

            firestore.collection("users").document(firebaseUser.uid).set(user).await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getNameUser(): Result<String> {
        return try {
            val userId = firebaseAuth.currentUser?.uid
            val snapshot = firestore.collection("users").document(userId ?: "").get().await()
            val user = snapshot.toObject(User::class.java)

            Result.success(user?.name ?: "")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}