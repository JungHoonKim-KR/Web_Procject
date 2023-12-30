


/*---------------------------------------------------------------------*/

/* 개발자를 찾으시나요?? 파트------------------------------------*/

var no_hard2=document.querySelector("#no_hard2");



no_hard2.addEventListener("click",function(){
    window.scrollTo({ left: 0, top: 750, behavior: "smooth" });
})



//프로젝트 단계별 step 파트 
/*프로젝트 step 이벤트 처리작업------------------------------------------*/
var step_circles=document.querySelectorAll(".step");
var gap=0;

step_circles[0].addEventListener("click",function(){
    console.log("dd");
    window.scrollTo({ left: 0, top: 1510, behavior: "smooth" });
})
step_circles[1].addEventListener("click",function(){
    window.scrollTo({ left: 0, top: 2270, behavior: "smooth" });
})
step_circles[2].addEventListener("click",function(){
    window.scrollTo({ left: 0, top: 3025, behavior: "smooth" });
})

/*---------------------------------------------------------------------*/

/*위아래 슬라이딩*/
var up_direction=document.querySelectorAll(".up_direction");
up_direction.forEach(function(item,index){
    up_direction[index].addEventListener("click",function(){

        if(index==0){
            window.scrollTo({ left: 0, top: 0, behavior: "smooth" });
        }
        if(index==1){
            window.scrollTo({ left: 0, top: 750, behavior: "smooth" });
        }
        if(index==2){
            window.scrollTo({ left: 0, top: 1510, behavior: "smooth" });

        }
        if(index==3){
            window.scrollTo({ left: 0, top: 2270, behavior: "smooth" });
        }

    })
})

var down_direction=document.querySelectorAll(".down_direction");
down_direction.forEach(function(item,index){
    down_direction[index].addEventListener("click",function(){

        if(index==0){
            window.scrollTo({ left: 0, top: 1510, behavior: "smooth" });
        }
        if(index==1){
            window.scrollTo({ left: 0, top: 2270, behavior: "smooth" });
        }
        if(index==2){
            window.scrollTo({ left: 0, top: 3025, behavior: "smooth" });
        }
        if(index==3){
            window.scrollTo({ left: 0, top: 4000, behavior: "smooth" });
        }

    })
})
/* step 부분 화살표 이벤트*/
var left=document.querySelector("#left");
var right=document.querySelector("#right");
var step_part_page=0;
// 원 위에 텍스트들의 객체
var step_text1=document.querySelector("#step_text1");
var step_text2=document.querySelector("#step_text2");
var step_text3=document.querySelector("#step_text3");
// step 원 안에 텍스트
var step1=document.querySelector("#step1");
var step2=document.querySelector("#step2");
var step3=document.querySelector("#step3");



var donga=document.querySelector('.donga');
donga.addEventListener('mouseover',function(){
    donga.style.cursor= "pointer";
})

donga.addEventListener('click',function(){
    location.href='first_main.html';
})

var my_pic=document.querySelector('.my_pic');
my_pic.addEventListener('mouseover',function(){
    my_pic.style.cursor= "pointer";
})

my_pic.addEventListener('click',function(){
    location.href='new_my_page.html';
})