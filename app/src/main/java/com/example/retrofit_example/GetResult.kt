package com.example.retrofit_example

import com.google.gson.annotations.SerializedName

// 2.
class GetResult {
    @SerializedName("userId")
    private var userId = 0

    @SerializedName("id")
    private var id = 0
    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함

    private var title = ""
    // @SerializedName()로 변수명을 일치시켜주면 클래스 변수명이 달라도 알아서 매핑시켜줌

    @SerializedName("body")
    private var bodyValue = ""

    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    override fun toString(): String {
        return "GetResult{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", bodyValue='" + bodyValue + '\'' +
                '}'
    }



}


/**
 *
 *
// jsonplaceholder.typicode.com (Rest API 테스트 사이트의 응답 구조조
{
"userId": 1,
"id": 1,
"title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
"body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}
 *
 * */