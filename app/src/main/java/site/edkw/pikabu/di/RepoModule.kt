package site.edkw.pikabu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import site.edkw.pikabu.repositories.ImageRepository
import site.edkw.pikabu.repositories.PostRepository
import site.edkw.pikabu.repositories.impl.ImageRepositoryImpl
import site.edkw.pikabu.repositories.impl.PostRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindImageRepository(
        imageRepositoryImpl: ImageRepositoryImpl
    ): ImageRepository

    @Binds
    @Singleton
    abstract fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository

}