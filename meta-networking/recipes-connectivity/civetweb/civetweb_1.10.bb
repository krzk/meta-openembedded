SUMMARY = "Civetweb embedded web server"
HOMEPAGE = "https://github.com/civetweb/civetweb"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=da079d81be91ff1d1ca56dcd751f897f"

SRCREV = "0f1b43536d97d9311c73b658b86a9d44be9e5e82"
SRC_URI = "git://github.com/civetweb/civetweb.git"
SRC_URI_append_class-native = " \
    file://0001-Test-Fix-missing-librt-and-libm-during-native-compil.patch \
"
S = "${WORKDIR}/git"

# civetweb supports building with make or cmake (although cmake lacks few features)
inherit cmake

# Disable Lua and Duktape because they do not compile from CMake (as of v1.8, v1.9 and v1.10).
# Disable ASAN as it is included only in Debug build.
# As of v1.10, SSL does not work. Bring it back with v1.11.
EXTRA_OECMAKE = " \
    -DBUILD_SHARED_LIBS=ON \
    -DCIVETWEB_ENABLE_DUKTAPE=OFF \
    -DCIVETWEB_ENABLE_LUA=OFF \
    -DCIVETWEB_ENABLE_ASAN=OFF \
    -DCIVETWEB_ENABLE_SSL=OFF \
"
EXTRA_OECMAKE_class-native = " \
    -DBUILD_SHARED_LIBS=ON \
    -DCIVETWEB_ENABLE_DUKTAPE=OFF \
    -DCIVETWEB_ENABLE_LUA=OFF \
    -DCIVETWEB_ENABLE_ASAN=OFF \
    -DCIVETWEB_ENABLE_SSL=OFF \
"

PACKAGECONFIG ??= "caching cgi cpp debug ipv6 server websockets"
PACKAGECONFIG[caching] = "-DCIVETWEB_DISABLE_CACHING=OFF,-DCIVETWEB_DISABLE_CACHING=ON,"
PACKAGECONFIG[cgi] = "-DCIVETWEB_DISABLE_CGI=OFF,-DCIVETWEB_DISABLE_CGI=ON,"
PACKAGECONFIG[cpp] = "-DCIVETWEB_ENABLE_CXX=ON,-DCIVETWEB_ENABLE_CXX=OFF,"
PACKAGECONFIG[debug] = "-DCIVETWEB_ENABLE_MEMORY_DEBUGGING=ON,-DCIVETWEB_ENABLE_MEMORY_DEBUGGING=OFF,"
PACKAGECONFIG[ipv6] = "-DCIVETWEB_ENABLE_IPV6=ON,-DCIVETWEB_ENABLE_IPV6=OFF,"
PACKAGECONFIG[server] = "-DCIVETWEB_INSTALL_EXECUTABLE=ON,-DCIVETWEB_INSTALL_EXECUTABLE=OFF,"
# PACKAGECONFIG[ssl] = "-DCIVETWEB_ENABLE_SSL=ON -DCIVETWEB_SSL_OPENSSL_API_1_1=ON -DCIVETWEB_ENABLE_SSL_DYNAMIC_LOADING=OFF,-DCIVETWEB_ENABLE_SSL=OFF,openssl"
PACKAGECONFIG[websockets] = "-DCIVETWEB_ENABLE_WEBSOCKETS=ON,-DCIVETWEB_ENABLE_WEBSOCKETS=OFF,"

BBCLASSEXTEND = "native"
