package com.rufree.dobi.api.client.kakao.dto

import java.time.ZonedDateTime

class KakaoUserResponse(
        val id: Long,
        val connectedAt: ZonedDateTime,
        val properties: KakaoUserProperties,
        val kakaoAccount: KakaoUserAccount
)

class KakaoUserProperties(
        val nickname: String,
        val profileImage: String,
        val thumbnailImage: String
)

class KakaoUserAccount(
        val profileNeedsAgreement: Boolean,
        val profile: KakaoUserProfile,
        val hasEmail: Boolean,
        val isEmailValid: Boolean,
        val isEmailVerfied: Boolean,
        val email: String,
        val hasAgeRange: Boolean,
        val ageRangeNeedsAgreement: Boolean,
        val ageRange: String,
        val hasBirthday: Boolean,
        val birthdayNeedsAgreement: Boolean,
        val birthday: String,
        val birthdayType: String,
        val hasGender: Boolean,
        val genderNeedsAgreement: Boolean,
        val gender: String
)

class KakaoUserProfile(
        val nickname: String,
        val thumbnailImageUrl: String,
        val profileImageUrl: String
)


