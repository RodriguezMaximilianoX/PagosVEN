package com.rmxdev.pagosven.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.rmxdev.pagosven.data.repository.TransferMoneyRepositoryImpl
import com.rmxdev.pagosven.data.repository.UserRepositoryImpl
import com.rmxdev.pagosven.domain.repository.TransferMoneyRepository
import com.rmxdev.pagosven.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideUserRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): UserRepository {
        return UserRepositoryImpl(auth, firestore)
    }

    @Provides
    @Singleton
    fun provideTransferMoneyRepository(firestore: FirebaseFirestore): TransferMoneyRepository {
        return TransferMoneyRepositoryImpl(firestore)
    }

}