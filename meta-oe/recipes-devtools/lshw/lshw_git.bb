DESCRIPTION = "A small tool to provide detailed information on the hardware \
configuration of the machine. It can report exact memory configuration, \
firmware version, mainboard configuration, CPU version and speed, cache \
configuration, bus speed, etc. on DMI-capable or EFI systems."
SUMMARY = "Hardware lister"
HOMEPAGE = "http://ezix.org/project/wiki/HardwareLiSter"
SECTION = "console/tools"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

COMPATIBLE_HOST = "(i.86|x86_64|arm|aarch64).*-linux"

SRCREV = "20cda77239e8604e798b87a0441e694edb0214d1"
PV = "02.18+git${SRCPV}"
SRC_URI = "git://github.com/lyonel/lshw.git \
           file://cross-compile.patch \
           file://0001-Makefile-Use-supplied-LDFLAGS-to-silence-OE-GNU_HASH.patch \
           file://0003-sysfs-Fix-basename-build-with-musl.patch \
           file://0004-Revert-fix-714-system-width-detection.patch \
           "

S = "${WORKDIR}/git"

do_compile() {
    # build core only - don't ship gui
    oe_runmake -C src core
}

do_install() {
    oe_runmake install DESTDIR=${D}
}
