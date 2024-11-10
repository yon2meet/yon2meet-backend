package team.yon2meet.backend.infra.client.kakao

data class KakaoAuthPublicJwkResponse(
    val kid: String,
    val kty: String,
    val alg: String,
    val use: String,
    val n: String,
    val e: String,
)
