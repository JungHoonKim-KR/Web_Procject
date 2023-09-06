
//프로젝트가 몇개인지

//프로젝트 개수를 누르면 프로젝트 관리페이지로 이동


//수정하기 버튼
var edit=document.querySelector(".edit");
//원래 내 개인정보
var inform_container=document.querySelector(".inform_container");
//수정하기를 눌렀을때, 뜰 수정용 개인정보
var edit_inform_container=document.querySelector(".edit_inform_container");

edit_inform_container.style.display="none";
//수정하기를 누르면 원래개인정보는 안보이고 수정용 개인정보를 띄움
edit.addEventListener("click",function(){
    inform_container.style.display="none";
    edit_inform_container.style.display="block";
});


var edit_save=document.querySelector(".edit_save");
var edit_cancle=document.querySelector(".edit_cancle");

edit_cancle.addEventListener("click",function(){
    inform_container.style.display="block";
    edit_inform_container.style.display="none";
});

//계정탈퇴
var remove_account=document.querySelector(".remove_account");
remove_account.addEventListener("click",function(){
    location.href='remove_account.html';
});

