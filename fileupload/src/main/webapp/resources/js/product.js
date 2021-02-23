/**
 * form 안에 일반 요소만 있는 경우
 */
$(function(){
	$("button").click(function(e){
		e.preventDefault();  // submit막기
		
		//일반 방식으로 전송할 때
		$.ajax({
			url:'/product',
			type:'post',
			data: $("form").serialize(),
			success:function(result){
				console.log(result);
			},
			error:function(xhr,txtStatus,error){
				console.log(txtStatus.result);
			}
		})  
		
		//json 방식으로 전송하기
	/*	let form={
			code:$('#code').val(),
			category:$('#category').val(),
			pname:$('#pname').val(),
			amount:$('#amount').val(),
			price:$('#price').val(),
			etc:$('#etc').val()
		};
		
		$.ajax({
			url:'/product',
			type:'post',
			contentType:'application/json;charset=utf-8',
			data:JSON.stringify(form),
			success:function(result){
				console.log(result);
			},
			error:function(xhr,txtStatus,error){
				console.log(txtStatus.responseText);
			}
		})  */
		
	})
})