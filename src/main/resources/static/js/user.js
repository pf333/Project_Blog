let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
	},

	save: function() {
		//alert("user의 save함수 호출됨");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		//console.log(data);

		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터의 타입
			dataType: "json" // 요청한 것을 응답 받았을 때 json 형태의 데이터를 js로 변환
		}).done(function(resp) {
			if (resp.status === 500) {
				alert("회원가입에 실패 하였습니다.");
			} else {
				alert("회원가입이 완료 되었습니다.");
				location.href = "/";
			}

		}).fail(function(error) {
			alert(JSON.stringify(error));

		}); // ajax 통신을 사용하여 3개의 데이터를 json으로 변경하여 insert 요청 
	},

	update: function() {
		//alert("user의 save함수 호출됨");
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		//console.log(data);

		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터의 타입
			dataType: "json" // 요청한 것을 응답 받았을 때 json 형태의 데이터를 js로 변환
		}).done(function(resp) {
			alert("회원수정이 완료 되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));

		}); // ajax 통신을 사용하여 3개의 데이터를 json으로 변경하여 insert 요청 
	},

}

index.init(); 