package com.example.myfirst.adapter.ProjectAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirst.databinding.ItemProjectInfoBinding
import com.example.myfirst.model.People
import com.example.myfirst.model.ProjectData

class ProjectAdapter : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {
    private var projectlist = listOf<People.Data>()
    //피필 데이터로 이름 바꿔주기
    //어댑터가 프로젝트 리스트 데이터를 갖고있어야해서 해줌
    //뷰홀더 : 자기뷰에 대한 참조 때문에 바인딩이있음
    //뷰홀더 내부에 객체가 있는데 내부설정 아무것도 안되어있음. 걍 이미지 텍스트 이런것만 있음.
    //바인드 함수보면은
    class ProjectViewHolder(
        private val binding: ItemProjectInfoBinding,
    private val context : Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(peopleData: People.Data) {
            //세개를 바인딩으로 묶음.
            with(binding) {
               // imgProjectInfo.setImageResource(ProjectData.imgProject)
                //사진 리소스 넣어주기위함
                Glide.with(context).load(peopleData.avatar).into(binding.imgProjectInfo)
                txtProjectContent.text = ("${peopleData.firstName} + ${peopleData.lastName}")
                //제목 타이틀 넣어주기위함
                txtProjectTitle.text = peopleData.email
                //내용도 집어넣어줌
                //이게 뷰홀더가 하는 역할. 뷰에대한 정보를 넣어주는 역할
                //이미지 드로우볼 아이디값 때문에 int 형임
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectAdapter.ProjectViewHolder {
        //뷰에대한 참조객체 넣어줌
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProjectInfoBinding.inflate(layoutInflater,parent,false)
        return ProjectViewHolder(binding, parent.context)



    }
//뷰에 관련된 데이터를
    //뷰에다 넣어주기
    override fun onBindViewHolder(holder: ProjectAdapter.ProjectViewHolder, position: Int) {
        holder.bind(projectlist[position]) //뷰정보를 하나하나씩넣어줌

    }

    override fun getItemCount()=projectlist.size
//걍 뷰가 몇개냐고

    fun replaceList(list: List<People.Data>) {
        projectlist = list.toList()
            //리스트가 들어오면 외부에있는 공유해서 조작하면 안됨 이걸 막아줘야함
        //그래서 복사본을 넘겨주는거임....????
        //데이터가 들어가야하는타입,
        notifyDataSetChanged()
    }
}