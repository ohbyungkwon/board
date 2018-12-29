<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <title>Login Test</title>

    <link rel="stylesheet" type="text/css" href="/dist/js/total.css">
    <link rel="stylesheet" type="text/css" href="/dist/js/semantic.css">
    <link rel="stylesheet" type="text/css" href="/dist/js/bootstrap.css">

    <script src="/dist/js/vendor.js"></script>
    <script src="/dist/js/signIn.js"></script>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
    <div class="layout-small">
        <form class="ui form board_line" id="center-form-small">
            <div class="inline fields">
                <label>이름</label>
                <div class="field">
                    <input type="text" placeholder="First Name"/>
                </div>
                <div class="field">
                    <input type="text" placeholder="Last Name"/>
                </div>
            </div>
            <#--<div class="inline fields">-->
                <#--<label>아이디</label>-->
                <#--<div class="field">-->
                    <#--<input id="InputEmail" type="text" autocomplete="off" placeholder="Enter want you ID"/>-->
                    <#--<button id="IdDuplicate" class="ui button">중복확인</button>-->
                    <#--<button id="IdChange" class="ui button" disabled>바꾸기</button>-->
                <#--</div>-->
            <#--</div>-->

            <div class="inline fields">
                <label>이메일</label>
                <div class="field">
                    <input id="InputEmail" type="email" autocomplete="off" placeholder="Enter want you E-mail"/>
                    <button id="EmailDuplicate" class="ui button">중복확인</button>
                    <button id="EmailChange" class="ui button" disabled>바꾸기</button>
                </div>
            </div>

            <div class="inline fields">
                <label>비밀번호</label>
                <div class="field">
                    <input id="InputPWD" autocomplete="off" type="password" placeholder="Enter want you PWD"/>
                    <p id="passwordComment"style="color:red;"></p>
                </div>
            </div>

            <div class="inline fields">
                <label>비밀번호 확인</label>
                <div class="field">
                    <input id="ReInputPWD" autocomplete="off" type="password" placeholder="Enter want you PWD"/>
                    <p id="duplicateComment"style="color:red;"></p>
                </div>
            </div>

            <button id="SendLoginPage" class="ui green button">생성</button>
        </form>
    </div>
</body>
