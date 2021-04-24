package com.example.myfirst.data.singleton

import com.example.myfirst.data.api.PeopleService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    //uri받아오기
    //객체반환
    //객체 생성자

        private const val BASE_URL = "https://reqres.in/"
    //레트로핏에서 빌터를 이용해서 이 베이스 유알을 가진
    //API명세서에 있는 내용을 서버통신을 하겠다
        val peopleInstance by lazy<PeopleService> {
        //BY레이즈 : 지연초기, 당장 생성하지않고 한번 호출됐을떄
        //피플서비스를 만들어보리겠다. 그 이후엔 안에있는것만 쓰겠다
        //동시에 여러 사용자들이 조작할수있음. 에러나면 안되니까 레이즐르 통해 순서를 정해줌
        //그래서 안전하게 여러사요자들이 객체에 접근가능함
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PeopleService::class.java)
        }
    }