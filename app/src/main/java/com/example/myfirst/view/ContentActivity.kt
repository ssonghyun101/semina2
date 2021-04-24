package com.example.myfirst.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myfirst.adapter.ProjectAdapter.ProjectAdapter
import com.example.myfirst.data.singleton.RetrofitObject
import com.example.myfirst.databinding.ActivityContentBinding
import com.example.myfirst.model.People
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding
    private lateinit var adapter: ProjectAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //마찬가지로 바인딩 설정해주기!!!!
        val id = intent.getStringExtra("ID") ?: ""
        Log.d("ID","안됨") //안될떄 출력
        //아이디값을 잘못 쳤을경우를 대비해서 널너블함. val로 가면 형식이 널너블이라고 뜸
        binding.txtContentsId.text = id
        getPeopleFromServer()

        /*val projectAdapter = ProjectAdapter()
        val projectList = listOf(
            ProjectData(
                imgProject = R.drawable.beme,
                txtProjectTitle = "BeMe",
                txtProjectContents = "비미가 짱이다 비켜"
            ),
            ProjectData(
                imgProject = R.drawable.wh,
                txtProjectTitle = "칭찬할고래",
                txtProjectContents = "칭찬할고래가 짱이야 비켜"
            )

            )
        projectAdapter.replaceList(projectList)
        binding.rvProjectList.adapter = projectAdapter
        */



    }//널일땐 id를 가져오고, 널일 경우 옆에꺼를 가져오라

    private fun setAdapter(people: People) {
        adapter = ProjectAdapter()
        adapter.replaceList(people.people)
        binding.rvProjectList.adapter = adapter
    }

    private fun getPeopleFromServer() {
        RetrofitObject.peopleInstance //피플인스태스 객체
            .getPeople(2) //api명세서 가져오기
            .enqueue(object : Callback <People> { //인큐, 백그라운드에서 작업됨. 서버통신이 끝난 후에 행동들을 밑에 정의해줌
                //서버통신이 끝난 후에 이 callback을 실행해주세요 이 밑에꺼 다 !
                override fun onResponse(
                    call : Call<People>,
                    response: Response<People>
                ) {
                    setAdapter(response.body()!!) //가져오면 널러블형태은 리스폰스테이블이옴
                }

                override fun onFailure(call: Call<People>, t: Throwable) {
                    t.printStackTrace()
                }


            })
    }



}