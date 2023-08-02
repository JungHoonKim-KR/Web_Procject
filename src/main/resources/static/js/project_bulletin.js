// 광고 배너 파트 안에 배너 슬라이드 작업 

var slide_circles= document.querySelectorAll(".circle");
var project_pic=document.querySelector("#project_pic");
slide_circles.forEach(function(item,index){
    item.addEventListener("mouseover",function(){
        this.style.backgroundColor="white";
        this.style.cursor= "pointer";
    });
    item.addEventListener("mouseout",function(){
        this.style.backgroundColor="gray";
        this.style.cursor= "pointer";
    });
    
});
slide_circles[0].addEventListener("click",function(e){
    project_pic.src="./images/프로젝트사진1.jpg"
    slide_circles[0].style.backgroundColor="black"

});
slide_circles[1].addEventListener("click",function(e){
    project_pic.src="./images/프로젝트사진2.png"
    slide_circles[1].style.backgroundColor="black"

});
slide_circles[2].addEventListener("click",function(e){
    project_pic.src="./images/프로젝트사진3.png"


});

//5초마다 광고 변경
setInterval(() => sliding(), 1000);
var count_time=0;
function sliding(){
    if(slide_circles.length-1%count_time!=0){
        slide_circles[count_time].click();
        if(count_time-1==-1){
            slide_circles[slide_circles.length+count_time-1].style.backgroundColor="gray"
        }else {
            slide_circles[count_time-1].style.backgroundColor="gray"
        }
        count_time++;
        if(count_time==slide_circles.length){
            count_time=0;
        }
    }else {
        slide_circles[count_time].click();

    }
}


// 광고 변경시 스무스하게 변경되도록 이벤트 추가 할 것

//searching 검색이벤트 구현



var donga=document.querySelector('#donga');
donga.addEventListener('mouseover',function(){
    donga.style.cursor= "pointer";
})

donga.addEventListener('click',function(){
    location.href='main_post.html';
})

//북마크 이벤트연결

var heart=document.querySelectorAll('.heart1')
console.log("ㅅㅂ")
console.log(heart)
heart.forEach(function(item,index){
    heart[index].addEventListener('click',function(){
        if(heart[index].src=="http://localhost:8080/images/%ED%95%98%ED%8A%B8%EB%AA%A8%EC%96%91(%EB%B9%A8%EA%B0%95).jpg"){
            heart[index].src="./images/하트모양(회색).jpg";
        }else{

            heart[index].src="./images/하트모양(빨강).jpg";
        }
    })
})

// 검색 했을때 ,checked 유지되게 하는 기능 구현
var search_btn=document.querySelector(".w-btn");
var field=document.querySelectorAll('input[name="field"]');
var language=document.querySelectorAll('input[name="language"]');

console.log(search_btn);
search_btn.addEventListener("click", function(){
    var checked_field = ["프론트엔드","백엔드"];
    var checked_language = ["JAVA","C","CSS"];

    
    console.log(field[0].value);
    console.log(language[0].value);


    console.log(checked_field[0]);
    console.log(checked_language[0]);

    field.forEach(function(item,index1){
        checked_field.forEach(function(item,index2){
            if(field[index1].value== checked_field[index2]){
                console.log("여기 ㄴ");
                field[index1].checked=true;
            }
        })
    })

    language.forEach(function(item,index1){
        checked_language.forEach(function(item,index2){
            if(language[index1].value== checked_language[index2]){
                console.log("여기 ㅍ");
                language[index1].checked=true;
            }
        })
    })
})
