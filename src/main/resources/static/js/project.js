//작은 사진 클릭시 큰사진과 교체후 테두리 표시
var small_pic=document.querySelectorAll('.small_pic');
var big_pic=document.querySelector('.big_pic');
var idx=0;
big_pic.src=small_pic[0].src;

small_pic[0].style.border="3px solid red";

small_pic.forEach(function(item,index){

    item.addEventListener("click",function(){
        small_pic[index].style.border="3px solid red";
        big_pic.src=small_pic[index].src;
        idx=index;

        small_pic.forEach(function(item,index1){

            if(idx!=index1){

                small_pic[index1].style.border="none";
            }

        });
    });
})

// 화살표 클릭시 다음 사진

var left_direction=document.querySelector("#left_direction");

var left_idx=0;
left_direction.addEventListener("click", function(){
    if(idx-1>0){
        left_idx=idx-1;
    }else{
        left_idx=0;
    }
    if(left_idx>=0){
        big_pic.src=small_pic[left_idx].src;
        idx=left_idx;
        small_pic[idx].style.border="3px solid red";
        small_pic.forEach(function(item,index){
            if(idx!=index){
                small_pic[index].style.border="none";
            }

        });
    }
});

var right_direction=document.querySelector("#right_direction");

var right_idx=0;
right_direction.addEventListener("click", function(){
    if(idx+1<small_pic.length-1){
        right_idx=idx+1;
    }else{
        right_idx=small_pic.length-1;
    }

    if(right_idx>=0){
        big_pic.src=small_pic[right_idx].src;
        idx=right_idx;
        small_pic[idx].style.border="3px solid red";
        small_pic.forEach(function(item,index){
            if(idx!=index){
                small_pic[index].style.border="none";
            }

        });
    }
});


var donga=document.querySelector('.donga');
donga.addEventListener('mouseover',function(){
    donga.style.cursor= "pointer";
})

