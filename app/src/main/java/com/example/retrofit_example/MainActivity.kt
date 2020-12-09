package com.example.retrofit_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

// https://jaejong.tistory.com/33
// https://jaejong.tistory.com/38?category=873924

// 3.
class MainActivity : AppCompatActivity() {
    var TAG = "Log data :"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
                // url 등록
            .addConverterFactory(GsonConverterFactory.create())
                // JSON을 변환해줄 Gson 변환기 등록
                // Converter는 여러개 등록 가능, 등록 순서대로 변환 가능 여부를 판단하고 불가능하면 다음 컨버터를 확인
            .build()

        var service = retrofit.create(RetrofitService::class.java)
        // Retrofit 인스턴스로 인터페이스 객체 구현

        var call = service.getPost("1") as Call<GetResult>
        // 추상 메소드 중 사용할 메소드를 Call 객체에 등록 
        // service.getPost("1")을 아래의 call 대신 사용해도 무방

        call.enqueue(object : Callback<GetResult>{
            override fun onResponse(call: Call<GetResult>, response: Response<GetResult>) {
                if(response.isSuccessful()){
                    var result = response.body()
                    Log.d(TAG, "성공 결과 : " + result.toString())
                }else{
                    Log.d(TAG, "실패 ")
                }
            }

            override fun onFailure(call: Call<GetResult>, t: Throwable) {
                Log.d(TAG, "onResponse : 실패")
            }
        })
    }
}