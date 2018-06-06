DESCRIPTION = "A small tool to provide detailed information on the hardware \
configuration of the machine. It can report exact memory configuration, \
firmware version, mainboard configuration, CPU version and speed, cache \
configuration, bus speed, etc. on DMI-capable or EFI systems."
SUMMARY = "Hardware lister"
HOMEPAGE = "http://ezix.org/project/wiki/HardwareLiSter"
SECTION = "console/tools"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PACKAGECONFIG ??= "pciutils usbutils zlib"
# Choose between bundled or external pci.ids and usb.ids:
PACKAGECONFIG[pciutils] = ",,,pciutils"
PACKAGECONFIG[usbutils] = ",,,usbutils"
PACKAGECONFIG[zlib] = "ZLIB=1,,zlib,"
COMPATIBLE_HOST = "(i.86|x86_64|arm|aarch64).*-linux"

SRC_URI = " \
    http://ezix.org/software/files/lshw-B.${PV}.tar.gz \
    file://cross-compile.patch \
    file://0001-Makefile-Use-supplied-LDFLAGS-to-silence-OE-GNU_HASH.patch \
"
SRC_URI[md5sum] = "8671c6d94d6324a744b7f21f1bfecfd2"
SRC_URI[sha256sum] = "ae22ef11c934364be4fd2a0a1a7aadf4495a0251ec6979da280d342a89ca3c2f"

S = "${WORKDIR}/lshw-B.${PV}"

do_compile() {
    # build core only - don't ship gui
    oe_runmake -C src core ${PACKAGECONFIG_CONFARGS}
}

do_install() {
    oe_runmake install DESTDIR=${D} ${PACKAGECONFIG_CONFARGS}

    if ${@bb.utils.contains('PACKAGECONFIG', 'pciutils', 'true', 'false', d)}; then
        rm -rf ${D}${datadir}/lshw/pci.ids*
    fi
    if ${@bb.utils.contains('PACKAGECONFIG', 'usbutils', 'true', 'false', d)}; then
        rm -rf ${D}${datadir}/lshw/usb.ids*
    fi
}
