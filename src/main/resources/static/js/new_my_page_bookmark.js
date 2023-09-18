

var edit_my_bookmarks=document.querySelectorAll('.edit_my_bookmarks');
var trashcan=document.querySelectorAll('.trashcan');


trashcan.forEach(function(item,index){
    trashcan[index].addEventListener('click',function(){
        edit_my_bookmarks[index].remove();
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

                    alert("북마크가 해제되었습니다.")
            })
            .catch(error => {
                // Handle the error case here if needed
            });

    })
})


var donga=document.querySelector('.donga');
donga.addEventListener('mouseover',function(){
    donga.style.cursor= "pointer";
})
