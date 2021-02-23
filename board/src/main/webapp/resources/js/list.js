/**
 *  list.jsp에서 사용하는 스크립트
 */
$(function(){
	
	checkModal(result);
	
	//history 재지정
	history.replaceState({},null, null);
	
	//모달창 띄우기
	function checkModal(result){
		if(result===''||history.state){
			return;
		}
		if(parseInt(result)>0) {
			$(".modal-body").html("게시글 "+result+" 번이 등록되었습니다.");
		}
		$("#myModal").modal("show");
	}
	
	//actionForm 가져오기 - 페이지 이동시 사용할 폼
	var actionForm = $("#actionForm");
	
	//페이지 번호 클릭시 동작
	$(".paginate_button a").click(function(e){
		//a 태그의 기능을 중지
		e.preventDefault();
		//pageNum의 값을 사용자가 선택한 값으로 변경
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	})
	
	//amount 값 변경시 동작
	$(".form-control").change(function(){
		//amount 값을 사용자가 선택한 값으로 변경
		actionForm.find("input[name='amount']").val($(this).val());
		actionForm.submit();
	})
	
	//게시판 제목 클릭시 동작(현재글번호, pageNum, amount, 검색정보)
	$(".move").click(function(e){
		e.preventDefault();
		actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
		actionForm.attr("action","read");  //     /board/read
		actionForm.submit();
	})
	
	//검색시 동작
	$(".btn-default").click(function(){
		//searchForm 가져온 후 type과 keyword가 비어 있는지 확인한 후
		//비어있으면 메시지 띄워준 후 return
		var searchForm=$("#searchForm");
		
		var type=$("select[name='type']").val();
		var keyword=$("input[name='keyword']").val();
		
		if(type==='') {
			alert("검색 기준을 입력해 주세요");
			return false;
		} else if (keyword===''){
			alert("검색어를 입력해 주세요");
			return false;
		}
		//검색시 첫 페이지를 보여주기 위해 페이지 번호 변경
		searchForm.find("input[name='pageNum']").val("1");
		searchForm.submit();
	}) 
})