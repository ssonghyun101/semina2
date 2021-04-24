package com.example.myfirst.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myfirst.databinding.ActivityHomeBinding

class MainActivity : AppCompatActivity() {
    //클래스의 private변수로 선언, 액티비티가 만들어지는 시기, 바인딩객체시기가 다름, 그래서 초기화가 늦어줘얗ㅁ
    //지연초기화 가능하게하는 것 타입도 무조건 써줘야함, 바인딩객체
    //어떤 레이아웃 이름이 카스멜케이스로 넣어준다음에 바인딩임
    //앱번들가서 그냥 true해주기
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
//그다음 초기화,
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //서버통신
        //mvc라는 아키택쳐, 가장 기초임.


        //버튼 클릭 리스너 눌렀을떄 행동 등록
        // 첫번째 로그인한후 아이디값을 가져온다
        // 2. 토스트에 그 값을 띄우게한다.
        //버튼에 setonClickListener 내부에서 동작을 정의한다
      /*  binding.etMainLogin.setOnClickListener{
            //1. 에딧텍스트 안에 있는 값을 가져온다
            //에딧텍스트 접근
            //값 가져오고 어디간에 저장하자
            //id값 가져와서 저장까지!!
            val id = binding.etMainId.text
            //토스트 띄우기\
            //디스=이 액티비티, id값을 , 짧은시간만큼 띄워준다
            Toast.makeText(this,id,Toast.LENGTH_SHORT).show()
        }*/
        //1.애딧텍스트값을 가져오고
        //2. 그값을 콘텐트액티비이로 넘겨주자
       /* binding.etMainLogin.setOnClickListener{
            val id = binding.etMainId.text.toString()
            //이걸 id를 컨텐트액티비티로 어떻게 넘겨주지?
            //intent 객체를 쓴다
            //intent(현재 context, 가고 싶은 클래스::class.java)
            //startActivity(intent(현재 context, 가고 싶은 클래스::class.java))
            //화면전환부터!
            val intent = Intent(this,ContentActivity::class.java)
            //화면을 전화하면서 데이터까지 보내고 싶을때
            //intent.putExtra(값의 이름, 실제값)
            intent.putExtra("ID",id)
            startActivity(intent)
            //이젠 콘텐트에서 꺼내오자


        }*/

        //로그인 제한조건
        //id/pwd에 적힌 값들이
        //모두 밑에있는 상수하고 같아야하지 않겠냐
        //1.로그인 버튼을 눌렀을 때
        //2. 적혀져있는 id,pwd가 밑에있는 상수와 같아야한다
        //같으면 로그인 성공! 아니면 로그인 실패
        binding.etMainLogin.setOnClickListener{
            //2번은 조건문임
            //로그인성공
            //근데 로그인 조건문이 길다
            //만약에 성공했을 떄 행동이 달라지면?
            //실패했을 떄도 마찬가지라면? 성공시 문장개길면 힘듦
            //그래서 각각 기능화를 할거임
            if (isLonginEnabled()){
                onLogSuccess()
            }else {
                //로그인 실패할떄 토스트 띄우기
                Toast.makeText(this,"로그인에 실패했습니다",Toast.LENGTH_SHORT).show()
            }

        }


        //아이디 비번은 상수, 이거 서버통신아님!!!!
        //클래스의 프라이빗 변수선언
        // 지연 초기화 선언 당시에는 초기ㅗ하를 못함
        //온크릿함수가 호출됄때 레이아웃 받아노느거라서 초기화를 늦게해줘야함
        //바인딩이란?
        //모든 버튼이 뷰다. 이 뷰에 접근하려면 FINDVIEWBYID 이렇게 접근해도 되는데 코드가 너무 길고, 복잡하다
        /* 바인딩객체로 메인XML로 접근 아이디값을 케멜케이스로 접근가능*/


    }

    //d이렇게 간단하게 줄여서 표현가능.!! 웬만하면 이렇게 할 것!!!
    //다른 개발자들과 원활하게 소통가능, 더 편하게 하는게 맞다고봄 배려임
    //로그인 리팩토링링
    private fun isLonginEnabled() = binding.etMainId.text.toString()== id &&
            binding.etMainPwd.text.toString()== pw

    private fun onLogSuccess() {
        val intent = Intent(this, ContentActivity::class.java)
        intent.putExtra("ID",binding.etMainId.text.toString())
        startActivity(intent)
        Toast.makeText(this,"로그인에 성공했습니다",Toast.LENGTH_SHORT).show()

    }




    companion object {
    const val id="song"
    const val pw = "1234"
    //컴파일 단계에서 이값이 결정됨.
    //
}
}
