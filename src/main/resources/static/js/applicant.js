var approve_btn=document.querySelectorAll(".approve_btn");
var refuse_btn=document.querySelectorAll(".refuse_btn");
var userEmail=document.querySelectorAll("#userEmail");
var projectId=document.querySelector("#projectId").value;
approve_btn.forEach(function (item,index){
    item.addEventListener("click",function (){
        var url = "/mypage/project/approve";
        var param1 ="value="+encodeURIComponent(this.value);
        var param2="id="+encodeURIComponent(projectId);
        var param3="emailId="+encodeURIComponent(userEmail[index].value);

        fetch(url + '?' + param1+"&"+param2+"&"+param3, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            },
        })
            .then(response => response.text())
            .then(data=> {
                if (data == "승인")
                    alert("승인하였습니다.");
                else{
                    alert("오류")
                }

            });

        setTimeout(function(){
            location.reload();
        }, 300);

    })

})

refuse_btn.forEach(function (item,index){
    item.addEventListener("click",function (){
        var url = "/mypage/project/approve";
        var param1 ="value="+encodeURIComponent(this.value);
        var param2="id="+encodeURIComponent(projectId);
        var param3="emailId="+encodeURIComponent(userEmail[index].value);

        fetch(url + '?' + param1+"&"+param2+"&"+param3, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            },
        })
            .then(response => response.text())
            .then(data=> {
                if (data == "거절")
                    alert("거절하였습니다.");
                else{
                    alert("오류")
                }

            });

        setTimeout(function(){
            location.reload();
        }, 300);

    })

})
