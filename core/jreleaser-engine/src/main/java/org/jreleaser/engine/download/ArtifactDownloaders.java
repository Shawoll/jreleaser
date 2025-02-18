/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020-2022 The JReleaser authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jreleaser.engine.download;

import org.jreleaser.bundle.RB;
import org.jreleaser.model.Downloader;
import org.jreleaser.model.JReleaserContext;
import org.jreleaser.model.downloader.spi.ArtifactDownloader;
import org.jreleaser.model.downloader.spi.ArtifactDownloaderFactory;
import org.jreleaser.util.JReleaserException;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Andres Almiray
 * @since 1.1.0
 */
public class ArtifactDownloaders {
    public static <D extends Downloader> ArtifactDownloader<D> findDownloader(JReleaserContext context, D downloader) {
        Map<String, ArtifactDownloader> downloaders = StreamSupport.stream(ServiceLoader.load(ArtifactDownloaderFactory.class,
                ArtifactDownloaders.class.getClassLoader()).spliterator(), false)
            .collect(Collectors.toMap(ArtifactDownloaderFactory::getName, factory -> factory.getArtifactDownloader(context)));

        if (downloaders.containsKey(downloader.getType())) {
            ArtifactDownloader<D> artifactDownloader = downloaders.get(downloader.getType());
            artifactDownloader.setDownloader(downloader);
            return artifactDownloader;
        }

        throw new JReleaserException(RB.$("ERROR_unsupported_downloader", downloader.getType()));
    }
}
