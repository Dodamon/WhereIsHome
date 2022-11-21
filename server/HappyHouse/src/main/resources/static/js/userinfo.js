
$(document).on('shown.bs.modal', "#mypageModal", openUserInfo);
$(document).on("click", "#updateUserInfoBtn", updateUserInfo);
$(document).on("click", "#deleteUserBtn", deleteUser);

async function deleteUser() {
	
	var result = confirm("회원탈퇴를 진행하시겠습니까? ");
	
	if(result) {
		let id = $.cookie("loginId");
		data =  $.post("member/delete", {id});

        $.removeCookie("loginId");
        alert("회원 탈퇴가 성공적으로 끝났습니다. ");
        location.reload();
	}
	
}
async function openUserInfo() {
	
	data = await fetch("member/userinfo");
	data = await data.text();
	if(data){data = JSON.parse(data);}
	
	document.querySelector("#idInfo").innerText = data.id;
	document.querySelector("#pwInfo").value = data.pw;
	document.querySelector("#nameInfo").value = data.name;

}

async function updateUserInfo(){
	
	let id = document.querySelector("#idInfo").textContent;
	let pw = document.querySelector("#pwInfo").value;
	let name = document.querySelector("#nameInfo").value;
	
	
	let data = {
		method: "POST",
		body: JSON.stringify({id, pw, name}),
		headers: {"Content-Type" : "application/json"},
	}
	
	data = await fetch("member/update", data);
	data = await data.text();
	if(data){data = JSON.parse(data);}
	console.log("update data")
	console.log(data);
	
	document.querySelector("#idInfo").innerText = data.id;
	document.querySelector("#pwInfo").value = data.pw;
	document.querySelector("#nameInfo").value = data.name;
	
	$.cookie("loginId", data.loginId);
	$.cookie("loginName", data.loginName);
	
	location.reload();
}
