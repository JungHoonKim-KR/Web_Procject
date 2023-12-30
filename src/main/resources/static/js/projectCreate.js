// 업로드사진 개체들
let profiles=document.querySelectorAll('.upload-name');
console.log(profiles);

//업로드 한 사진 개수
let profiles_number=profiles.length;


//사진 추가하기 버튼
let add_pic=document.querySelector('.add_pic');
console.log("dd"+profiles_number);
//사진 업로드 페이지에 테두리 개체
let container3=document.querySelector('.container3');
let bar=document.querySelector('#bar');

let upload_pictures=document.querySelectorAll('.upload-hidden')

upload_pictures.forEach(function(item,index) {

    upload_pictures[index].addEventListener('change',function(){
        change_p(index)
    })
})

function change_p(index) {
    profiles[index].value= upload_pictures[index].value
}

add_pic.addEventListener('click',function(e){

    const testDiv = document.querySelectorAll('.upload_profile')[profiles_number-2]

    const newNode = testDiv.cloneNode(true);

    testDiv.after(newNode);
    let upload_div=document.querySelectorAll('.upload_profile');
    profiles=document.querySelectorAll('.upload-name');

    profiles_number+=1;
    upload_pictures=document.querySelectorAll('.upload-hidden')
    let upload=document.querySelectorAll('.upload')
    upload_pictures.forEach(function(item,index) {

        upload[profiles_number-2].setAttribute('for',"ex_filename"+index)
        upload_pictures[index].setAttribute('id',"ex_filename"+index)
        profiles[profiles_number-1].value="파일선택"


        upload_pictures[index].addEventListener('change',function(){
            change_p(index)
        })

    })


    console.log(profiles_number);
    if (profiles_number>3){
        var currentHeight = parseInt(getComputedStyle(container3).height, 10);
        var newHeight = currentHeight + 60;
        container3.style.height = newHeight + 'px';
    }

});