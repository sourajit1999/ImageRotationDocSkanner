/*
 * Copyright 2020 Paul Rybitskyi, paul.rybitskyi.work@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.paulrybitskyi.docskanner.data.di

import com.paulrybitskyi.docskanner.data.usecases.*
import com.paulrybitskyi.docskanner.domain.*
import com.paulrybitskyi.docskanner.domain.ClearAppCacheUseCase
import com.paulrybitskyi.docskanner.domain.CreateAppStorageFolderUseCase
import com.paulrybitskyi.docskanner.domain.CreatePdfDocumentUseCase
import com.paulrybitskyi.docskanner.domain.ObserveAppStorageFolderFilesUseCase
import com.paulrybitskyi.docskanner.domain.SaveBitmapToFileUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
internal interface UseCasesModule {


    @Binds
    fun bindInitOpenCvLibraryUseCase(
        useCase: InitOpenCvLibraryUseCaseImpl
    ): InitOpenCvLibraryUseCase


    @Binds
    fun bindCreateAppStorageFolderUseCase(
        useCase: CreateAppStorageFolderUseCaseImpl
    ): CreateAppStorageFolderUseCase


    @Binds
    fun bindClearAppCacheUseCase(
        useCase: ClearAppCacheUseCaseImpl
    ): ClearAppCacheUseCase


    @Binds
    fun bindObserveAppStorageFolderFilesUseCase(
        useCase: ObserveAppStorageFolderFilesUseCaseImpl
    ): ObserveAppStorageFolderFilesUseCase


    @Binds
    fun bindSaveBitmapToFileUseCase(
        useCase: SaveBitmapToFileUseCaseUseCaseImpl
    ): SaveBitmapToFileUseCase


    @Binds
    fun bindCreatePdfDocumentUseCase(
        useCase: CreatePdfDocumentUseCaseImpl
    ): CreatePdfDocumentUseCase


}