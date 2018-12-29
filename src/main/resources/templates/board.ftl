<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Board</title>
    <link rel="stylesheet" type="text/css" href="/dist/js/total.css">
    <link rel="stylesheet" type="text/css" href="/dist/js/semantic.css">
    <link rel="stylesheet" type="text/css" href="/dist/js/bootstrap.css">

    <script src="/dist/js/vendor.js"></script>
    <script src="/dist/js/board.js"></script>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
    <div class="header_c">
        <#--<div class="ui segment">-->
            <p class ="centerHeader">Board Page</p>
            <hr class="lineMargin">
        <#--</div>-->
    </div>
    <div class="content_c">
        <p> * ${loginedUser}님이 로그인 중
            <a href="/logOut"> [로그아웃]</a>
        </p>
        <div class="ui fluid action input">
            <input id="keywordInput" type="text" placeholder="제목을 입력하세요.">
            <button id="searchBtn" class="ui button">Search</button>
        </div>
        <table class="ui celled padded table">
            <thead>
                <tr>
                    <th>글쓴이</th>
                    <th>제목</th>
                    <th>내용</th>
                    <th>수정/삭제</th>
                </tr>
            </thead>
            <tbody>
                <#list boards as board>
                <tr id="tr${board.getSeq()}">
                    <td class="ui center aligned">
                        ${board.getWriter()}
                    </td>
                    <td class="ui center aligned header"><a href="/board/${board.getSeq()}">${board.getTitle()}</a></td>
                    <td class="ui center aligned header"><a href="/board/${board.getSeq()}">${board.getContent()}</a></td>
                    <td class="ui center aligned header">
                        <button value=${board.getSeq()} name="editModal" class="ui orange button">수정</button>
                        <button value=${board.getSeq()} name="deleteModal" class="ui red button">삭제</button>
                    </td>
                </tr>
                </#list>
            </tbody>
            <tfoot>
            <tr><th colspan="5">
                <button id="createModal" class="ui blue button">글 추가</button>
                <div class="ui right floated pagination menu">
                <#list 1..totalPage as i>
                    <a href="/board?page=${i-1}" class="item">${i}</a>
                </#list>
                </div>
            </th>
            </tr></tfoot>
        </table>
    </div>

    <div class="modal" id="newBoard">
        <div class="modal-content">
            <span class="close">&times;</span>
            <form class="ui form">
                <h4 id="modal-title" class="ui dividing header"></h4>
                <#--<div class="ui info message">-->
                    <#--<p>비밀번호 필수 입력.</p>-->
                    <#--<input id="pwdBoard" type="password"">-->
                <#--</div>-->
                <label>제목</label>
                <div class="field">
                    <input id="title" type="text" placeholder="글 제목을 입력하세요.">
                </div>
                <div class="field">
                    <label>본문</label>
                    <textarea id="contents" placeholder="내용을 입력하세요."></textarea>
                </div>
                <div class="field">
                    <button id="addBoard" class="ui button">추가</button>
                    <button id="delBoard" class="ui button">취소</button>
                </div>
            </form>
        </div>
    </div>

    <#--<div class="modal" id="delModal">-->
        <#--<div class="delModal-content">-->
            <#--<span class="close">&times;</span>-->
            <#--<form class="ui form">-->
                <#--<h4 id="delModal-title" class="ui dividing header"></h4>-->
                <#--<div class="ui info message">-->
                    <#--<p>삭제</p>-->
                    <#--<label>아이디</label>-->
                    <#--<input id="writer" type="text"">-->
                    <#--<label>비밀번호</label>-->
                    <#--<input id="password" type="password"">-->
                <#--</div>-->
                <#--<div class="field">-->
                    <#--<button id="delModal_delete" class="ui button">삭제</button>-->
                    <#--<button id="delModal_cancel" class="ui button">취소</button>-->
                <#--</div>-->
            <#--</form>-->
        <#--</div>-->
    <#--</div>-->

</body>
</html>