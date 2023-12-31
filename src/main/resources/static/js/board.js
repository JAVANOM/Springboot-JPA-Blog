let index = {
    init: function(){
		$("#btn-save").on("click", () => { // function(){} , ()=>{} this를 바인딩하기 위해서!!
			this.save();
		});
		$("#btn-delete").on("click", () => { 
			this.deleteById();
		});
		$("#btn-update").on("click", () => { 
			this.update();
		});
	},
	
	save: function(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),		
		};

		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), //JSON 문자열 http body데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지 (MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면 ) => javascript 오브젝트
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다." + resp);
			console.log(resp);
			location.href = "/";
		}).fail(function(error){
			alert(Json.stringify(error));
		});  
		
	},
	
	deleteById: function(){
		
		let id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			contentType: "application/json; charset=utf-8", 
			dataType: "json" 
		}).done(function(resp){
			alert("삭제가 완료되었습니다." + resp);
			console.log(resp);
			location.href = "/";
		}).fail(function(error){
			alert(Json.stringify(error));
		});  
		
	},
	
	update: function(){
		
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()		
		};
        console.log(id);
        console.log(data);
		$.ajax({
			//회원가입 수행 요청
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data), //JSON 문자열 http body데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지 (MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면 ) => javascript 오브젝트
		}).done(function(resp){
			alert("글수정이 완료되었습니다." + resp);
			console.log(data);
			location.href = "/";
		}).fail(function(error){
			alert(Json.stringify(error));
		});  
		
	},
	
	
	/*login: function(){
		// alert('user의 save함수 호출 됨')
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			
		};
	
		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data), //JSON 문자열 http body데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지 (MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면 ) => javascript 오브젝트
		}).done(function(resp){
			alert("로그인이 완료되었습니다." + resp);
			console.log(resp);
			location.href = "/";
		}).fail(function(error){
			alert(Json.stringify(error));
		});  
		
	}*/
	
}

index.init();