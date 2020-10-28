package com.rufree.dobi.api.service

import com.rufree.dobi.api.client.kakao.dto.response.KakaoUserResponse
import com.rufree.dobi.api.utils.KakaoAccountUtils
import com.rufree.dobi.common.entity.User
import com.rufree.dobi.common.entity.enums.AuthorityName
import com.rufree.dobi.common.entity.enums.SocialType
import com.rufree.dobi.common.repository.AuthorityRepository
import com.rufree.dobi.common.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserAuthService(
    private val userRepository: UserRepository,
    private val authorityRepository: AuthorityRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun kakaoJoin(response: KakaoUserResponse): User {
        return createUser(
            providerId = response.id,
            email = response.kakaoAccount.email,
            nickname = response.properties.nickname,
            profileImage = response.properties.profileImage,
            socialType = SocialType.KAKAO
        )
    }


    @Transactional
    fun createUser(providerId: Long, email: String, profileImage: String, nickname: String, socialType: SocialType): User {
        val authorities = authorityRepository.findByAuthorityName(AuthorityName.ROLE_LEVEL0)

        val user = User(
            providerId = providerId.toString(),
            email = email,
            username = KakaoAccountUtils.getUsernameByKakaoAccount(providerId),
            password = passwordEncoder.encode(KakaoAccountUtils.getPasswordByKakaoAccount(providerId)),
            profileImage = profileImage,
            nickname = nickname,
            socialType = SocialType.KAKAO,
            authorities = listOf(authorities)
        )

        return userRepository.save(user)
    }
}