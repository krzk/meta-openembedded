SUMMARY = "Wireless Central Regulatory Domain Database"
HOMEPAGE = "http://wireless.kernel.org/en/developers/Regulatory/CRDA"
SECTION = "net"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=07c4f6dea3845b02a18dc00c8c87699c"

SRC_URI = "https://www.kernel.org/pub/software/network/${BPN}/${BP}.tar.xz"
SRC_URI[md5sum] = "4bada6171bf89a2926bddcaebde448b5"
SRC_URI[sha256sum] = "ed5706eab62a0985ecfaf1a6055fd402882d4c7ffee0b473f39291142aba6435"

inherit bin_package

do_install() {
    install -d -m0755 ${D}${libdir}/crda
    install -d -m0755 ${D}${sysconfdir}/wireless-regdb/pubkeys
    install -m 0644 regulatory.bin ${D}${libdir}/crda/regulatory.bin
    install -m 0644 sforshee.key.pub.pem ${D}${sysconfdir}/wireless-regdb/pubkeys/sforshee.key.pub.pem
}

# Native users might want to use source db.txt
do_install_append_class-native() {
    install -d -m 0755 ${D}${libdir}/crda
    install -m 0644 db.txt ${D}${libdir}/crda/db.txt
}

RSUGGESTS_${PN} = "crda"

BBCLASSEXTEND = "native"
