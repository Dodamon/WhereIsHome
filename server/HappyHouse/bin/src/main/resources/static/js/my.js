let cookie_loginId = $.cookie("loginId");

if(cookie_loginId) {
	 document.querySelector("#loginSpan").innerHTML =
            cookie_loginId + "님 환영합니다 "  +
            "<button id='logoutBtn' class='btn btn-primary'>로그아웃</button>  <button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#mypageModal' style='margin-right: 5px;' id='mypageBtn'>마이페이지 </button>";
	$("#loginModalBtn").css("display", "none");
}
// selectAllBoard(1);

$(document).on("click", "#loginBtn", login);
$(document).on("click", "#logoutBtn", logout);
$(document).on("click", "#joinMember", memberInsert);
$(document).on("click", "#boardListBtn", boardList);

async function login() {
	let id = $("#id").val();
    let pw = $("#pw").val();
    
    let data = {
		method: "POST",
		body: JSON.stringify({id, pw}),
		headers: {"Content-Type" : "application/json"},
	}
	
	data = await fetch("member/login", data);
	data = await data.text();
	if(data){
		data = JSON.parse(data);
		console.log(data.name)
	}
	

	
	if (data.name) {
        $.cookie("loginId", data.id);
        document.querySelector("#loginSpan").innerHTML =
            data.name + "님 환영합니다 "  +
            "<button id='logoutBtn' class='btn btn-primary'>로그아웃</button>  <button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#mypageModal' style='margin-right: 5px;' id='mypageBtn'>마이페이지 </button>";

        $("#loginModal").modal("hide");
        $("#loginModalBtn").css("display", "none");
    } else {
        alert("login fail");
    }
}

async function logout(){
	//let data = JSON.stringify({sign:"logout"});
 	//data = await fetch("main", {method:"POST", body:data});
 	fetch("member/logout", {method:"POST"});
 	$.removeCookie("loginId");
 	location.reload();
}

async function memberInsert(){
 	let id = document.querySelector("#user_id").value;
 	let pw = document.querySelector("#user_pw").value;
 	let name = document.querySelector("#user_name").value;
 	let address = document.querySelector("#user_addr").value;
 	let phone = document.querySelector("#user_tel").value;
 	
 	if(id == "") {
		 alert("아이디를 입력해주세요");
	 } else if(name == "") {
		 alert("이름을 입력해주세요");
	 } else if(pw == "") {
		 alert("비밀번호를 입력해주세요");
	 } else {
		 
		let data = {
		method: "POST",
		body: JSON.stringify({id, pw, name}),
		headers: {"Content-Type" : "application/json"},
		}
	
		console.log(data);
		data = await fetch("member/register", data);
		data = await data.text();
		
		if(data){
			data = JSON.parse(data);
			alert(data.name);
		}
		
		$('#myModal').modal('hide');

	 }

 }
 
function boardList() {
    	selectAllBoard(1);
}
    
function selectAllBoard(pageNo){
	console.log(pageNo);
	$.post('board/selectAll',{pageNum:pageNo,pageSize:10},function(data){
		console.log(data);
		let board=`<center>
					<a onclick='window.open("board/write", "_blank", "toolbar=yes, scrollbars=yes, resizable=yes, top=50,left=100, width=600, height=700");' type="button" class="btn btn-primary" style="margin-right: 5px;" id="writeBtn">
						글쓰기
                    </a><br>
                    <table class="table table-hover"><tr><th>index</th><th>글번호</th><th>제목</th></tr>`;
		data.list.forEach(function(item,index){
			board += `<tr onClick="board_click(${item.code})" style="cursor : pointer;"><td>${++index}</td><td>${item.code}</td><td>${item.title}</td></tr>`;
		});
		board += `</table><br>`;
		if(data.hasPreviousPage) board+=`<a href="#" onclick="selectAllBoard(${data.prePage})"> << </a>`;
		board+=`${data.pageNum}`;
		if(data.hasNextPage) board+=`<a href="#" onclick="selectAllBoard(${data.nextPage})"> >> </a>`;
		board+=`</center>`;
		console.log(board);
		$("#main").html(board);
	});
}

function board_click(code){
	$.post('board/clickArticle',{code},function(board){
		let inner = `
			<div class="col-lg-10 col-md-10 col-sm-12">
  <div class="row my-2">
    <h2 class="text-secondary px-5">${board.title}</h2>
  </div>

  <div class="row">
    <div class="col-md-8">
      <div class="clearfix align-content-center">
        <img
          class="avatar me-2 float-md-start bg-light p-2"
          src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
        />
        <p>
          <span class="fw-bold">${board.writer}</span> <br />
          <span class="text-secondary fw-light">
            ${board.reg_datetime} 
          </span>
        </p>
      </div>
    </div>
    <div class="text-secondary">${board.content}</div>
    <div>
   <!-- 	<img src="c:/board/${board.file_name}"  width="200px"> -->
    </div>
  </div>
</div>

<div class="d-flex justify-content-end">
  <button type="button" id="btn-list" class="btn btn-outline-primary mb-3" onClick="boardList()">
    글목록
  </button>
  <button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1" onClick="boardModifyForm('${code}', '${board.title}','${board.content}')" >
    글수정
  </button>
  <button
    type="button"
    id="btn-delete"
    class="btn btn-outline-danger mb-3 ms-1"
    onClick="boardDelete('${code}')"
  >
    글삭제
  </button>
  
</div>

		`;
		$("#main").html(inner);
		
	});
}

function boardModifyForm(code, title, content){
	alert("수정 진입");
	
	let inner = `
		<div class="mb-3">
  <label for="subject" class="form-label">제목 : </label>
  <input type="text" class="form-control" id="subject" name="subject" value=${title} />
</div>
<div class="mb-3">
  <label for="content" class="form-label">내용 : </label>
  <textarea class="form-control" id="content" name="content" rows="7">${content}</textarea>
</div>
<div class="col-auto text-center">
  <button type="button" id="btn-modify" class="btn btn-outline-primary mb-3" onClick="boardModify('${code}', document.getElementById('subject').value, document.getElementById('content').value)">
    글수정
  </button>
  <button type="button" id="btn-list" class="btn btn-outline-danger mb-3">
    목록으로이동...
  </button>
</div>
	`;
	
	$("#main").html(inner);
}

function boardModify(code, title, content){
	$.post('board/modifyArticle',{code, title, content},function(){});
	alert("글 수정 완료");
	
	boardList();
}

function boardDelete(code){
	$.post('board/deleteArticle',{code},function(){});
	alert("글 삭제 완료");
	
	boardList();
}
