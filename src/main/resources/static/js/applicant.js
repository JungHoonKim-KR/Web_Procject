var join_btn=document.querySelectorAll(".join_btn");
var userEmail=document.querySelector("#userEmail").value;
var projectId=document.querySelector("#projectId").value;
join_btn.forEach(function (element){
    element.addEventListener("click",function (){
        var url = "/mypage-project/approve";
        var param1 ="value="+encodeURIComponent(this.value);
        var param2="id="+encodeURIComponent(projectId);
        var param3="emailId="+encodeURIComponent(userEmail);

        fetch(url + '?' + param1+"&"+param2+"&"+param3, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            },
        })
            .then(response => response.text())
            .catch(error => {
                // Handle the error case here if needed
            });
        if(this.value=="승인")
            alert("승인하였습니다.")
        else if(this.value="거절")
            alert("거절하였습니다.")
        setTimeout(function(){
            location.reload();
        }, 300);

    })

})
