package com.example.retrofit_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

// https://jaejong.tistory.com/33
// https://jaejong.tistory.com/38?category=873924
// https://blog.naver.com/ciscovoip/222139353338
// https://zzandoli.tistory.com/48
// 3.
class MainActivity : AppCompatActivity() {
    var TAG = "Log data :"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView = findViewById<TextView>(R.id.textView)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
                // url 등록
            .addConverterFactory(GsonConverterFactory.create())
                // JSON을 변환해줄 Gson 변환기 등록
                // Converter는 여러개 등록 가능, 등록 순서대로 변환 가능 여부를 판단하고 불가능하면 다음 컨버터를 확인
            .build()

        var service = retrofit.create(RetrofitService::class.java)
        // Retrofit 인스턴스로 인터페이스 객체 구현

        var calls : Call<List<GetResult>> = service.getPosts()
        // 추상 메소드 중 사용할 메소드를 Call 객체에 등록 
        // service.getPost("1")을 아래의 call 대신 사용해도 무방

        calls.enqueue(object : Callback<List<GetResult>>{
            override fun onResponse(call: Call<List<GetResult>>, response: Response<List<GetResult>>)
            {
               val response : List<GetResult>? = response.body()
                var reponseStr = ""
                response?.forEach { reponseStr += "userId : ${it.userId}\n" +
                        "id : ${it.id}\n" +
                        "title : ${title}\n" +
                        "body : ${it.body}\n\n" }
                println(reponseStr)
            }

            override fun onFailure(call: Call<List<GetResult>>, t: Throwable) {
                    // 실패
            }
        })

        var call : Call<GetResult> = service.getPost("1")

        call.enqueue(object : Callback<GetResult>{
            override fun onResponse(call: Call<GetResult>, response: Response<GetResult>) {
                if(response.isSuccessful()){
                    var result = response.body()?.let {
                        val userId = it.userId
                        val id = it.id
                        val title = it.title
                        val body = it.body
                        textView.text = "userId : ${userId.toString()}\n" +
                                "id : ${id.toString()}\n" +
                                "title : ${title}\n" +
                                "body : ${body}"
                    }
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