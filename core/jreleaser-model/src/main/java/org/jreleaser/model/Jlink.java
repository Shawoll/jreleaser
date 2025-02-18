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
package org.jreleaser.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.jreleaser.util.StringUtils.isBlank;
import static org.jreleaser.util.StringUtils.isNotBlank;
import static org.jreleaser.util.Templates.resolveTemplate;

/**
 * @author Andres Almiray
 * @since 0.2.0
 */
public class Jlink extends AbstractJavaAssembler<Jlink> {
    public static final String TYPE = "jlink";

    private final Set<Artifact> targetJdks = new LinkedHashSet<>();
    private final Set<String> moduleNames = new LinkedHashSet<>();
    private final Set<String> additionalModuleNames = new LinkedHashSet<>();
    private final List<String> args = new ArrayList<>();
    private final Artifact jdk = new Artifact();
    private final Jdeps jdeps = new Jdeps();

    private String imageName;
    private String imageNameTransform;
    private Boolean copyJars;

    public Jlink() {
        super(TYPE);
    }

    @Override
    public Distribution.DistributionType getDistributionType() {
        return Distribution.DistributionType.JLINK;
    }

    @Override
    public void merge(Jlink jlink) {
        super.merge(jlink);
        this.imageName = merge(this.imageName, jlink.imageName);
        this.imageNameTransform = merge(this.imageNameTransform, jlink.imageNameTransform);
        this.copyJars = merge(this.copyJars, jlink.copyJars);
        setJdeps(jlink.jdeps);
        setJdk(jlink.jdk);
        setTargetJdks(merge(this.targetJdks, jlink.targetJdks));
        setModuleNames(merge(this.moduleNames, jlink.moduleNames));
        setAdditionalModuleNames(merge(this.additionalModuleNames, jlink.additionalModuleNames));
        setArgs(merge(this.args, jlink.args));
    }

    public String getResolvedImageName(JReleaserContext context) {
        Map<String, Object> props = context.getModel().props();
        props.putAll(props());
        return resolveTemplate(imageName, props);
    }

    public String getResolvedImageNameTransform(JReleaserContext context) {
        if (isBlank(imageNameTransform)) return null;
        Map<String, Object> props = context.getModel().props();
        props.putAll(props());
        return resolveTemplate(imageNameTransform, props);
    }

    public Jdeps getJdeps() {
        return jdeps;
    }

    public void setJdeps(Jdeps jdeps) {
        this.jdeps.merge(jdeps);
    }

    public Artifact getJdk() {
        return jdk;
    }

    public void setJdk(Artifact jdk) {
        this.jdk.merge(jdk);
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageNameTransform() {
        return imageNameTransform;
    }

    public void setImageNameTransform(String imageNameTransform) {
        this.imageNameTransform = imageNameTransform;
    }


    public Set<Artifact> getTargetJdks() {
        return Artifact.sortArtifacts(targetJdks);
    }

    public void setTargetJdks(Set<Artifact> targetJdks) {
        this.targetJdks.clear();
        this.targetJdks.addAll(targetJdks);
    }

    public void addTargetJdks(Set<Artifact> targetJdks) {
        this.targetJdks.addAll(targetJdks);
    }

    public void addTargetJdk(Artifact jdk) {
        if (null != jdk) {
            this.targetJdks.add(jdk);
        }
    }

    public Set<String> getModuleNames() {
        return moduleNames;
    }

    public void setModuleNames(Set<String> moduleNames) {
        this.moduleNames.clear();
        this.moduleNames.addAll(moduleNames);
    }

    public void addModuleNames(List<String> moduleNames) {
        this.moduleNames.addAll(moduleNames);
    }

    public void addModuleName(String moduleName) {
        if (isNotBlank(moduleName)) {
            this.moduleNames.add(moduleName.trim());
        }
    }

    public void removeModuleName(String moduleName) {
        if (isNotBlank(moduleName)) {
            this.moduleNames.remove(moduleName.trim());
        }
    }

    public Set<String> getAdditionalModuleNames() {
        return additionalModuleNames;
    }

    public void setAdditionalModuleNames(Set<String> additionalModuleNames) {
        this.additionalModuleNames.clear();
        this.additionalModuleNames.addAll(additionalModuleNames);
    }

    public void addAdditionalModuleNames(List<String> additionalModuleNames) {
        this.additionalModuleNames.addAll(additionalModuleNames);
    }

    public void addAdditionalModuleName(String additionalModuleName) {
        if (isNotBlank(additionalModuleName)) {
            this.additionalModuleNames.add(additionalModuleName.trim());
        }
    }

    public void removeAdditionalModuleName(String additionalModuleName) {
        if (isNotBlank(additionalModuleName)) {
            this.additionalModuleNames.remove(additionalModuleName.trim());
        }
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args.clear();
        this.args.addAll(args);
    }

    public void addArgs(List<String> args) {
        this.args.addAll(args);
    }

    public void addArg(String arg) {
        if (isNotBlank(arg)) {
            this.args.add(arg.trim());
        }
    }

    public void removeArg(String arg) {
        if (isNotBlank(arg)) {
            this.args.remove(arg.trim());
        }
    }

    public Boolean isCopyJars() {
        return copyJars == null || copyJars;
    }

    public void setCopyJars(Boolean copyJars) {
        this.copyJars = copyJars;
    }

    public boolean isCopyJarsSet() {
        return copyJars != null;
    }

    @Override
    protected void asMap(boolean full, Map<String, Object> props) {
        super.asMap(full, props);
        props.put("imageName", imageName);
        props.put("imageNameTransform", imageNameTransform);
        props.put("moduleNames", moduleNames);
        props.put("additionalModuleNames", additionalModuleNames);
        props.put("args", args);
        props.put("jdeps", jdeps.asMap(full));
        Map<String, Map<String, Object>> mappedJdks = new LinkedHashMap<>();
        int i = 0;
        for (Artifact targetJdk : getTargetJdks()) {
            mappedJdks.put("jdk " + (i++), targetJdk.asMap(full));
        }
        props.put("jdk", jdk.asMap(full));
        props.put("targetJdks", mappedJdks);
        props.put("copyJars", isCopyJars());
    }

    public static class Jdeps extends AbstractModelObject<Jdeps> implements Domain {
        private final Set<String> targets = new LinkedHashSet<>();
        private String multiRelease;
        private Boolean ignoreMissingDeps;
        private Boolean useWildcardInPath;

        @Override
        public void merge(Jdeps jdeps) {
            this.multiRelease = this.merge(this.multiRelease, jdeps.multiRelease);
            this.ignoreMissingDeps = this.merge(this.ignoreMissingDeps, jdeps.ignoreMissingDeps);
            this.useWildcardInPath = this.merge(this.useWildcardInPath, jdeps.useWildcardInPath);
            setTargets(merge(this.targets, jdeps.targets));
        }

        public String getMultiRelease() {
            return multiRelease;
        }

        public void setMultiRelease(String multiRelease) {
            this.multiRelease = multiRelease;
        }

        public Boolean isIgnoreMissingDeps() {
            return ignoreMissingDeps != null && ignoreMissingDeps;
        }

        public void setIgnoreMissingDeps(Boolean ignoreMissingDeps) {
            this.ignoreMissingDeps = ignoreMissingDeps;
        }

        public boolean isIgnoreMissingDepsSet() {
            return ignoreMissingDeps != null;
        }

        public Boolean isUseWildcardInPath() {
            return useWildcardInPath == null || useWildcardInPath;
        }

        public void setUseWildcardInPath(Boolean useWildcardInPath) {
            this.useWildcardInPath = useWildcardInPath;
        }

        public boolean isUseWildcardInPathSet() {
            return useWildcardInPath != null;
        }

        public Set<String> getTargets() {
            return targets;
        }

        public void setTargets(Set<String> targets) {
            this.targets.clear();
            this.targets.addAll(targets);
        }

        public void addTargets(List<String> targets) {
            this.targets.addAll(targets);
        }

        public void addTarget(String target) {
            if (isNotBlank(target)) {
                this.targets.add(target.trim());
            }
        }

        public void removeTarget(String target) {
            if (isNotBlank(target)) {
                this.targets.remove(target.trim());
            }
        }

        @Override
        public Map<String, Object> asMap(boolean full) {
            Map<String, Object> props = new LinkedHashMap<>();
            props.put("multiRelease", multiRelease);
            props.put("ignoreMissingDeps", isIgnoreMissingDeps());
            props.put("useWildcardInPath", isUseWildcardInPath());
            props.put("targets", targets);
            return props;
        }
    }
}
