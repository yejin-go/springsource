/**
 *  파일첨부와 관련된 스크립트
 */

$(function(){
	
	//게시글 등록 버튼 동작
	$("button[type='submit']").click(function(e){
		e.preventDefault();
		
		var str="";
		//첨부파일 영역에 정보 수집
		$(".uploadResult ul li").each(function(idx,obj){
			var job = $(obj); 
		
		//수집된 정보를 hidden 태그로 작성
		str+="<input type='hidden' name='attachList["+idx+"].uuid' value='"+job.data("uuid")+"'>";
		str+="<input type='hidden' name='attachList["+idx+"].uploadPath' value='"+job.data("path")+"'>";
		str+="<input type='hidden' name='attachList["+idx+"].fileName' value='"+job.data("filename")+"'>";
		str+="<input type='hidden' name='attachList["+idx+"].fileType' value='"+job.data("type")+"'>";
	})
	console.log(str);
		
	//hidden 태그를 게시글 등록 폼에 추가한 후 폼 전송하기
	//1.게시글 등록 폼 가져오기
	var form = $("form");
	//2.폼에 추가하기
	form.append(str);
	//3.전송
	form.submit();
	})
	
	
	//파일 버튼이 클릭되어 변화가 일어나는 경우
	//현재 목록의 파일을 서버로 보내서 저장하기
	$("input[type='file']").change(function(){
		console.log("업로드 호출");
			
			
			var inputFile = $("input[name='uploadFile']");
			console.log(inputFile);
			
			var files = inputFile[0].files;
			
			//<form> ~ </form> 대체로 ajax로 데이터를 쉽게 전송할 수 있도록 해줌
			var formData = new FormData();
			//객체 안에 요소 추가
			for(var i=0; i<files.length; i++){
				formData.append("uploadFile", files[i]);
			}
		
		$.ajax({
			url:'/uploadAjax',
			type:'post',
			processData:false,//데이터를 query string 형태로 보낼 것인지 결정(기본값은 application/x-www-form-urlencoded임)
			contentType:false, //기본 값은 application/x-www-form-urlencoded임(파일첨부이므로 multipart/form-data로 보내야 함)
			data:formData,
			beforeSend:function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			success:function(result){
				console.log(result);
				showUploadedFile(result);
				$("input[name='uploadFile']").val("");
			},
			error:function(xhr,status,error){
				console.log(status);	
			}
		})
	})	 //파일첨부 종료
	
	function showUploadedFile(uploadResultArr){
		//결과를 보여줄 영역 가져오기
		var uploadResult = $(".uploadResult ul");
		var str="";
		$(uploadResultArr).each(function(idx, obj){	// var i=0
			if(obj.fileType) {
				//썸네일 이미지 경로
				var fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
				//원본 이미지 경로
				var originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
				
				originPath = originPath.replace(new RegExp(/\\/g),"/");
				
				str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
				str+=" data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
				str+="<a href=\"javascript:showImage(\'"+originPath+"\')\">";
				str+="<img src='/display?fileName="+fileCallPath+"'><div>"+obj.fileName+"</a>";
				str+="<button type='button' class='btn btn-warning btn-circle' data-file='";
				str+=fileCallPath+"' data-type='i'>";
				str+="<i class='fa fa-times'></i>";
				str+="</button>";
				str+="</div></li>";
			}else{
				//일반파일 경로 2021\01\21234567uuiddkie_text.txt
				var fileCallPath=encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);
				str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
				str+=" data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
				str+="<a href='/download?fileName="+fileCallPath+"'>";
				str+="<img src='/resources/img/attach.png'><div>"+obj.fileName+"</a>";
 				str+="<button type='button' class='btn btn-warning btn-circle' data-file='";
				str+=fileCallPath+"' data-type='file'>";
				str+="<i class='fa fa-times'></i>";
				str+="</button>";
				str+="</div></li>";
			}
		});
		uploadResult.append(str);
	} //첨부파일 보여주기 종료
	
	//X버튼 클릭 - 이벤트 위임
	$(".uploadResult").on("click", "button", function(){
		
		//해당 파일 경로 가져오기
		var targetFile = $(this).data("file");
		//파일 타입 가져오기
		var type = $(this).data("type");
		//span 태그가 속한 부모 li 태그 가져오기
		var targetLi = $(this).closest("li");
		

		//서버 폴더에서 제거
		$.ajax({
			url:'/deleteFile',
			type:'post',
			data:{
				fileName:targetFile,
				type:type
			},
			success: function(result){
				console.log(result);
				//화면 목록에서 제거
				targetLi.remove();
			}
		})
	})//x버튼 종료
	
	//크게 열린 이미지 다시 닫기
	$(".bigPictureWrapper").click(function(){
		$(".bigPicture").animate({width:'0%', height:'0%'},1000);
		setTimeout(function(){
			$(".bigPictureWrapper").hide();
		},1000);
	}) //이미지 닫기 종료
})

function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display", "flex").show();
	$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>")
					.animate({width:'100%', height:'100%'}, 1000);
}