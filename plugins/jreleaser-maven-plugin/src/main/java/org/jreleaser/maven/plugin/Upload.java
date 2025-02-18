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
public class Upload implements Activatable {
    private final Map<String, Artifactory> artifactory = new LinkedHashMap<>();
    private final Map<String, FtpUploader> ftp = new LinkedHashMap<>();
    private final Map<String, HttpUploader> http = new LinkedHashMap<>();
    private final Map<String, S3> s3 = new LinkedHashMap<>();
    private final Map<String, ScpUploader> scp = new LinkedHashMap<>();
    private final Map<String, SftpUploader> sftp = new LinkedHashMap<>();
    private Active active;

    void setAll(Upload upload) {
        this.active = upload.active;
        setArtifactory(upload.artifactory);
        setFtp(upload.ftp);
        setHttp(upload.http);
        setS3(upload.s3);
        setScp(upload.scp);
        setSftp(upload.sftp);
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

    public Map<String, Artifactory> getArtifactory() {
        return artifactory;
    }

    public void setArtifactory(Map<String, Artifactory> artifactory) {
        this.artifactory.clear();
        this.artifactory.putAll(artifactory);
    }

    public Map<String, FtpUploader> getFtp() {
        return ftp;
    }

    public void setFtp(Map<String, FtpUploader> ftp) {
        this.ftp.clear();
        this.ftp.putAll(ftp);
    }

    public Map<String, HttpUploader> getHttp() {
        return http;
    }

    public void setHttp(Map<String, HttpUploader> http) {
        this.http.clear();
        this.http.putAll(http);
    }

    public Map<String, S3> getS3() {
        return s3;
    }

    public void setS3(Map<String, S3> s3) {
        this.s3.clear();
        this.s3.putAll(s3);
    }

    public Map<String, ScpUploader> getScp() {
        return scp;
    }

    public void setScp(Map<String, ScpUploader> scp) {
        this.scp.clear();
        this.scp.putAll(scp);
    }

    public Map<String, SftpUploader> getSftp() {
        return sftp;
    }

    public void setSftp(Map<String, SftpUploader> sftp) {
        this.sftp.clear();
        this.sftp.putAll(sftp);
    }
}
