window.onload= function() {
    var bno;
    var action ;

    var modal = document.getElementById('newBoard');
    var delModal = document.getElementById('delModal');
    var createModal = document.getElementById("createModal");

    var close = document.getElementsByClassName("close")[0];

    createModal.onclick = function() {
        $("#modal-title").text("새 글 작성");
        modal.style.display = "block";
        action="create";
    };

    $("button[name='editModal']").click(function(){
        bno = this.value;
        var row = $(this).parent().parent();
        var tr = row.children();
        var title_c = tr.eq(1).text();
        var contents_c = tr.eq(2).text();

        $("#modal-title").text("다시 글 작성");

        $("#title").val(title_c);
        $("#contents").val(contents_c);

        modal.style.display = "block";
        action="modify";
    });//modal 출력

    $("button[name='deleteModal']").click(function(){
        bno = this.value;

        $.ajax({
            url : '/board/' + bno,
            type : 'DELETE',
            dataType:'json',
            contentType: 'application/json; charset=UTF-8',
            success: function(data){
                alert(data.msg);
                window.location.reload();
            },
            error(data){
                alert(data.responseJSON.msg);
            }
        });
    });

    close.onclick = function() {
        modal.style.display = "none";
        delModal.style.display = "none";
    };

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
            delModal.style.display = "none";
        }
    };//modal 제거
    //새글 쓰기, 수정, 삭제 모달 처리 로직.


    var addBoard = document.getElementById("addBoard");
    addBoard.onclick = function() {
        var data = {
            title : $("#title").val(),
            contents : $("#contents").val()
        };

        if(action == 'create'){
            url = '/board';
            action = 'POST';
        }
        else if(action == 'modify'){
            url = '/board/' + bno;
            action = "PUT";
        }

        $.ajax({
            url: url,
            method: action,
            dataType:'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data),
            success: function(data) {
                alert(data.msg);
                window.location.reload();
            },
            error: function (data) {
                console.log(data);
                alert(data.responseJSON.msg);
            }
        });
        modal.style.display = "none";
        return false;
    };

    $("#searchBtn").click(function(){
        var keyword = $("#keywordInput").val();
        console.log(keyword);
        window.location.href = "/board/keyword="+keyword+"/page=1";
    });

};