SUMMARY = "Wireless Central Regulatory Domain Database"
HOMEPAGE = "http://wireless.kernel.org/en/developers/Regulatory/CRDA"
SECTION = "net"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=07c4f6dea3845b02a18dc00c8c87699c"

SRC_URI = "https://www.kernel.org/pub/software/network/${BPN}/${BP}.tar.xz"
SRC_URI[md5sum] = "0cbbdc21186c76cf58aba01b03f3dc5b"
SRC_URI[sha256sum] = "e1dfbc3b97771373077f430c3c05082fae883145b37db5b2cfd12c56676fbe7b"

inherit bin_package

do_install() {
    install -d -m0755 ${D}${libdir}/crda
    install -d -m0755 ${D}${sysconfdir}/wireless-regdb/pubkeys
    install -m 0644 regulatory.bin ${D}${libdir}/crda/regulatory.bin
    install -m 0644 sforshee.key.pub.pem ${D}${sysconfdir}/wireless-regdb/pubkeys/sforshee.key.pub.pem

    install -d -m0755 ${D}${nonarch_base_libdir}/firmware/
    install -m 0644 regulatory.db ${D}${nonarch_base_libdir}/firmware/regulatory.db
    install -m 0644 regulatory.db.p7s ${D}${nonarch_base_libdir}/firmware/regulatory.db.p7s
}

# Install static regulatory DB in /lib/firmware for kernel to load.
# This requires Linux kernel >= v4.15.
PACKAGES =+ "${PN}-static"
RCONFLICTS_${PN} = "${PN}-static"

FILES_${PN}-static = " \
    ${nonarch_base_libdir}/firmware/regulatory.db \
    ${nonarch_base_libdir}/firmware/regulatory.db.p7s \
"

RSUGGESTS_${PN} = "crda"
