package com.rufree.dobi.common.entity.enums

enum class AuthorityName(val key: String, val title: String) {
    LEVEL0("ROLE_LEVEL0", "임시 사용자"),
    LEVEL1("ROLE_LEVEL1", "일반 사용자"),
    ADMIN("ROLE_ADMIN", "어드민")
}