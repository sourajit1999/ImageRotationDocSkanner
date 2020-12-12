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

package com.paulrybitskyi.docskanner.data.usecases

import android.os.FileObserver
import com.paulrybitskyi.docskanner.core.AppStorageFolderProvider
import com.paulrybitskyi.docskanner.core.DispatcherProvider
import com.paulrybitskyi.docskanner.domain.ObserveAppStorageFolderFilesUseCase
import com.paulrybitskyi.docskanner.core.utils.fileList
import com.paulrybitskyi.docskanner.core.utils.newFileObserver
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ObserveAppStorageFolderFilesUseCaseImpl @Inject constructor(
    private val appStorageFolderProvider: AppStorageFolderProvider,
    private val dispatcherProvider: DispatcherProvider
) : ObserveAppStorageFolderFilesUseCase {


    override suspend fun execute(params: Unit): Flow<List<File>> {
        return callbackFlow {
            val appStorageFolder = appStorageFolderProvider.getAppStorageFolder()
            val events = (
                FileObserver.MODIFY or FileObserver.ATTRIB or
                FileObserver.MOVED_FROM or FileObserver.MOVED_TO or
                FileObserver.CREATE or FileObserver.DELETE or
                FileObserver.DELETE_SELF
            )
            val fileObserver = appStorageFolder.newFileObserver(events) { _, _ ->
                sendBlocking(appStorageFolder.fileList())
            }

            fileObserver.startWatching()

            sendBlocking(appStorageFolder.fileList())
            awaitClose { fileObserver.stopWatching() }
        }
        .flowOn(dispatcherProvider.computation)
    }


}