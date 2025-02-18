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
package org.jreleaser.maven.plugin;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Andres Almiray
 * @since 0.3.0
 */
abstract class AbstractUploader implements Uploader {
    private final Map<String, Object> extraProperties = new LinkedHashMap<>();
    private String name;
    private boolean enabled;
    private Active active;
    private int connectTimeout;
    private int readTimeout;
    private Boolean artifacts;
    private Boolean files;
    private Boolean signatures;
    private Boolean checksums;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Active getActive() {
        return active;
    }

    @Override
    public void setActive(Active active) {
        this.active = active;
    }

    @Override
    public String resolveActive() {
        return active != null ? active.name() : null;
    }

    @Override
    public int getConnectTimeout() {
        return connectTimeout;
    }

    @Override
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    @Override
    public int getReadTimeout() {
        return readTimeout;
    }

    @Override
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    @Override
    public Map<String, Object> getExtraProperties() {
        return extraProperties;
    }

    @Override
    public void setExtraProperties(Map<String, Object> extraProperties) {
        this.extraProperties.clear();
        this.extraProperties.putAll(extraProperties);
    }

    @Override
    public Boolean isArtifacts() {
        return artifacts == null || artifacts;
    }

    @Override
    public void setArtifacts(Boolean artifacts) {
        this.artifacts = artifacts;
    }

    @Override
    public boolean isArtifactsSet() {
        return artifacts != null;
    }

    @Override
    public Boolean isFiles() {
        return files == null || files;
    }

    @Override
    public void setFiles(Boolean files) {
        this.files = files;
    }

    @Override
    public boolean isFilesSet() {
        return files != null;
    }

    @Override
    public Boolean isSignatures() {
        return signatures == null || signatures;
    }

    @Override
    public void setSignatures(Boolean signatures) {
        this.signatures = signatures;
    }

    @Override
    public boolean isSignaturesSet() {
        return signatures != null;
    }

    @Override
    public boolean isChecksumsSet() {
        return checksums != null;
    }

    @Override
    public Boolean isChecksums() {
        return checksums == null || checksums;
    }

    @Override
    public void setChecksums(Boolean checksums) {
        this.checksums = checksums;
    }
}
