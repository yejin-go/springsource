/**
 * form 안에 input type='file'이 들어온 경우
 */
$(function(){
	$("button").click(function(e){
		e.preventDefault();  // submit막기
		
		var formData = new FormData();
		formData.append("code", $('#code').val());
		formData.append("category", $('#category').val());
		formData.append("file", $('#file')[0].file[0]);
		
		//일반 방식으로 전송할 때
		$.ajax({
			url:'/product',
			type:'post',
			contentType:false,
			processData:false,
			data: formData,
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