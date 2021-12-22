# -*- coding: utf-8; mode: tcl; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*- vim:fenc=utf-8:ft=tcl:et:sw=4:ts=4:sts=4

PortSystem       1.0
PortGroup        java 1.0

name             {{distributionName}}
version          {{projectVersion}}
revision         {{macportsRevision}}

categories       {{macportsCategories}}
license          {{projectLicense}}
maintainers      {{macportsMaintainers}}
platforms        darwin
supported_archs  noarch

description      {{projectDescription}}
long_description {{projectLongDescription}}

homepage         {{projectWebsite}}

master_sites     {{macportsDistributionUrl}}
distname         {{macportsDistname}}
use_zip          yes

checksums        rmd160 {{distributionChecksumRmd160}} \
                 sha256 {{distributionChecksumSha256}} \
                 size   {{distributionSize}}

java.version     {{macportsJavaVersion}}

use_configure    no

build {}

destroot {
    set target ${destroot}${prefix}/share/${name}

    # Create the target directory
    xinstall -m 755 -d ${target}

    # Copy over the needed elements of our directory tree
    foreach f [glob -dir ${worksrcpath} *] {
        copy ${f} ${target}
    }

    # Remove extraneous files
    foreach f [glob -directory ${target}/bin *.{{distributionExecutableExtension}}] {
        delete ${f}
    }

    ln -s ../share/${name}/bin/{{distributionExecutable}} ${destroot}${prefix}/bin/{{distributionExecutable}}
}

livecheck.type   regex
livecheck.url    https://jreleaser.org/releases/latest/download/VERSION
livecheck.regex  (\[0-9.\]+\\.\[0-9.\]+\\.\[0-9.\]+)
