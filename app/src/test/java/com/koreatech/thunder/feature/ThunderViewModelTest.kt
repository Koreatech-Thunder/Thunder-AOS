package com.koreatech.thunder.feature

import com.koreatech.thunder.domain.repository.ThunderRepository
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ThunderViewModelTest {
    @MockK
    lateinit var thunderRepository: ThunderRepository

    @DisplayName("서버 통신 결과 해시태그 리스트가 비어있다면 전체 해시태그 리스트를 저장한다.")
    @Test
    fun test1() {
    }

    @DisplayName("서버 통신 결과 해시태그 리스트가 있다면 해당 해시태그 리스트를 저장한다.")
    @Test
    fun test2() {
    }

    @DisplayName("서버 통신 결과 통신을 실패 했으면 전체 해시태그 리스트를 저장한다.")
    @Test
    fun test3() {
    }

    @DisplayName("전체 번개 리스트를 저장한다.")
    @Test
    fun test4() {
    }

    @DisplayName("유저의 정보를 불러올 수 있다.")
    @Test
    fun test5() {
    }

    @DisplayName("특정 번개를 참여하지 않은 유저는 NON_MEMBER 상태이고 해당 유저가 참여하기를 누르면 MEMBER 상태가 된다.")
    @Test
    fun test6() {
    }

    @DisplayName("특정 번개를 참여 중인 유저는 MEMBER 상태이고 해당 유저가 취소하기를 누르면 NON_MEMBER 상태가 된다.")
    @Test
    fun test7() {
    }

    @DisplayName("특정 번개를 개최한 유저는 HOST 상태다.")
    @Test
    fun test8() {
    }

    @DisplayName("특정 해시태그를 클릭하면 번개 리스트가 특정 해시태그를 포함하고 있는 번개 리스트만 보여준다.")
    @Test
    fun test9() {
    }
}
