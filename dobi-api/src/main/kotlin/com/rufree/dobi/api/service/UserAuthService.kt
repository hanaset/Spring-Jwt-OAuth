package com.rufree.dobi.api.service

import com.rufree.dobi.api.client.kakao.dto.response.KakaoUserResponse
import com.rufree.dobi.common.entity.User
import com.rufree.dobi.common.entity.enums.AuthorityName
import com.rufree.dobi.common.entity.enums.SocialType
import com.rufree.dobi.common.repository.AuthorityRepository
import com.rufree.dobi.common.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserAuthService(
    private val userRepository: UserRepository,
    private val authorityRepository: AuthorityRepository
) {

    fun kakaoJoin(response: KakaoUserResponse): User{
        return createUser(response.kakaoAccount.email, response.properties.nickname, response.properties.profileImage, SocialType.KAKAO)
    }


    @Transactional
    fun createUser(email: String, profileImage: String, nickname: String, socialType: SocialType): User {
        val authorities = authorityRepository.findByAuthorityName(AuthorityName.LEVEL0)

        val user = User(
            email = email,
            profile = profileImage,
            nickname = nickname,
            socialType = SocialType.KAKAO,
            authorities = listOf(authorities)
        )

        return userRepository.save(user)
    }
}