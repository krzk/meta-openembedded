# Copies the regulatory plaintext database to kernel sources before compiling.
# Linux kernel must be compiled with CONFIG_CFG80211_INTERNAL_REGDB to use it.

DEPENDS += "wireless-regdb-native"

SRCTREECOVEREDTASKS += "do_kernel_add_regdb"
do_kernel_add_regdb() {
    cp ${STAGING_LIBDIR_NATIVE}/crda/db.txt ${S}/net/wireless/db.txt
}
do_kernel_add_regdb[dirs] = "${S}"
addtask kernel_add_regdb before do_configure after do_patch
