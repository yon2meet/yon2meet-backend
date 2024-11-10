package team.yon2meet.backend.infra.client.kakao

data class KakaoAuthListPublicJwksResponse(
    val keys: List<KakaoAuthPublicJwkResponse>,
)
