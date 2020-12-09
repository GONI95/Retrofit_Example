package com.example.retrofit_example

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Path

// 1.
// 테스트 사이트 들어가면 ...com/posts/1으로 끝남(MainActivity에서 1을 호출함)
interface RetrofitService {

    // 4. 새로 생성
    @GET("posts")
    fun getPosts()



    // @GET(EndPoint - 자원위치(URI)) and GET, POST, PUT, DELETE, HEAD도 있음
    @GET("posts/{post}")
    // 전체 URI에서 URL을 제외한 End Point (즉, posts/1)
    fun getPost(@Path("post") post : String) : retrofit2.Call<GetResult>
    // @Path("post")의 post가 @GET()DML {}의 post로 매치가 됨

}