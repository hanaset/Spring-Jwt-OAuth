package com.rufree.dobi.api.client.kakao.dto.response

import com.google.gson.annotations.SerializedName


data class KakaoUserResponse(
    val id: Long,
    @SerializedName("connected_at")
    val connectedAt: String,
    val properties: KakaoUserProperties,
    @SerializedName("kakao_account")
    val kakaoAccount: KakaoUserAccount
)

data class KakaoUserProperties(
    val nickname: String,
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("thumbnail_image")
    val thumbnailImage: String
)

data class KakaoUserAccount(
    @SerializedName("profile_needs_agreement")
    val profileNeedsAgreement: Boolean,
    val profile: KakaoUserProfile,
    @SerializedName("has_email")
    val hasEmail: Boolean,
    @SerializedName("is_email_valid")
    val isEmailValid: Boolean,
    @SerializedName("is_email_verfied")
    val isEmailVerfied: Boolean,
    val email: String,
    @SerializedName("has_age_range")
    val hasAgeRange: Boolean,
    @SerializedName("age_range_needs_agreement")
    val ageRangeNeedsAgreement: Boolean,
    @SerializedName("age_range")
    val ageRange: String,
    @SerializedName("has_birthday")
    val hasBirthday: Boolean,
    @SerializedName("birthday_needs_agreement")
    val birthdayNeedsAgreement: Boolean,
    val birthday: String,
    @SerializedName("birthday_type")
    val birthdayType: String,
    @SerializedName("has_gender")
    val hasGender: Boolean,
    @SerializedName("gender_needs_agreement")
    val genderNeedsAgreement: Boolean,
    val gender: String
)

data class KakaoUserProfile(
    val nickname: String,
    @SerializedName("thumbnail_image_url")
    val thumbnailImageUrl: String,
    @SerializedName("profile_image_url")
    val profileImageUrl: String
)


