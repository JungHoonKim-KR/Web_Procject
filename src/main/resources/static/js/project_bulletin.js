// 광고 배너 파트 안에 배너 슬라이드 작업 

var slide_circles = document.querySelectorAll(".circle");
var project_pic = document.querySelector("#project_pic");
slide_circles.forEach(function (item, index) {
    item.addEventListener("mouseover", function () {
        this.style.backgroundColor = "white";
        this.style.cursor = "pointer";
    });
    item.addEventListener("mouseout", function () {
        this.style.backgroundColor = "gray";
        this.style.cursor = "pointer";
    });

});
slide_circles[0].addEventListener("click", function (e) {
    project_pic.src = "./images/프로젝트사진1.jpg"
    slide_circles[0].style.backgroundColor = "black"

});
slide_circles[1].addEventListener("click", function (e) {
    project_pic.src = "./images/프로젝트사진2.png"
    slide_circles[1].style.backgroundColor = "black"

});
slide_circles[2].addEventListener("click", function (e) {
    project_pic.src = "./images/프로젝트사진3.png"


});

//5초마다 광고 변경
setInterval(() => sliding(), 1000);
var count_time = 0;

function sliding() {
    if (slide_circles.length - 1 % count_time != 0) {
        slide_circles[count_time].click();
        if (count_time - 1 == -1) {
            slide_circles[slide_circles.length + count_time - 1].style.backgroundColor = "gray"
        } else {
            slide_circles[count_time - 1].style.backgroundColor = "gray"
        }
        count_time++;
        if (count_time == slide_circles.length) {
            count_time = 0;
        }
    } else {
        slide_circles[count_time].click();

    }
}


// 광고 변경시 스무스하게 변경되도록 이벤트 추가 할 것

//searching 검색이벤트 구현


var donga = document.querySelector('#donga');
donga.addEventListener('mouseover', function () {
    donga.style.cursor = "pointer";
})

donga.addEventListener('click', function () {
    location.href = 'main_post.html';
})
//북마크 이벤트연결
var username = document.querySelector('#username').value

var heart = document.querySelectorAll('.heart1')
var bookmarkImg = [];
bookmarkImg = document.querySelector("#bookmarkImg").value.replace('[', '').replace(']', '').split(',');


// 검색 했을때 ,checked 유지되게 하는 기능 구현

var field = document.getElementsByName('field');
var language = document.getElementsByName('language');
var searchField = []
searchField = document.querySelector('#searchField').value.replace('[', '').replace(']', '').replaceAll(' ', '').split(',');
var searchLanguage = []
searchLanguage = document.querySelector('#searchLanguage').value.replace('[', '').replace(']', '').replaceAll(' ', '').split(',');

if (searchField.length != 0) {
    for (let index1 = 0; index1 < searchField.length; index1++) {
        for (let index2 = 0; index2 < field.length; index2++) {
            if (searchField[index1]==field[index2].value) {
                field[index2].checked = true;
                break;
            }
        }
    }
}

if (searchLanguage.length != 0) {
    for (let index3 = 0; index3 < searchLanguage.length; index3++) {

        for (let index4 = 0; index4 < language.length; index4++) {

            if (searchLanguage[index3] == language[index4].value) {
                console.log(language[index4])
                language[index4].checked = true;
                break;
            }
        }
    }
}


heart.forEach(function (item, index) {
    //includes 하는 대상(bookmarkImg)이 null이면 includes가 에러 나서 null체크를 먼저 하게 함
    if (username && bookmarkImg[index]!=null && bookmarkImg[index].includes('빨강'))
        heart[index].setAttribute('src', bookmarkImg[index])

    heart[index].addEventListener('click', function () {
        if (username) {
            var url = "/project-bookmark";
            var params = 'projectId=' + encodeURIComponent(this.id);

            fetch(url + '?' + params, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                },
            })
                .then(response => response.text())
                .then(data => {
                    if (data === "true") {
                        heart[index].src = "./images/하트모양(빨강).jpg";
                    } else {
                        heart[index].src = "./images/하트모양(회색).jpg";
                    }
                })
                .catch(error => {
                    // Handle the error case here if needed
                });
        }
    })
})

var page_btn=document.querySelectorAll(".number-button");
var cur_page=document.getElementById("cur_page").value;
console.log(cur_page)
//page_btn에 처
page_btn[cur_page].style.backgroundColor="cyan";
page_btn[cur_page].style.color="black";

page_btn.forEach(function (item,index){
    page_btn[index].addEventListener("click",function(){
        page_btn[index].style.backgroundColor="cyan";
        page_btn[index].style.color="black";

        present_page=index;

        page_btn.forEach(function (item,index){
            if(index!=present_page){
                page_btn[index].style.backgroundColor="#13161c";
                page_btn[index].style.color="white";
            }

        });
    });
})