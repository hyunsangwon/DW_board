getPageNum();//페이지 번호 알아내는 함수 호출
getBoardStatistics(); //통계 함수 호출
 
function getBoardStatistics() {
	$.ajax({
		url: '/api/v1/board/statistics',
		type: 'GET',
		dataType: 'json',
		success: function(response) {
			//text() or html() input을 제외한 태그를 컨트롤할 때 사용.
			//val()은 input 컨트롤할 때 사용.
			$('#boardCnt').text(response.boardCnt);
			$('#studentsCnt').text(response.studentsCnt);
			$('#writerCnt').text(response.writerCnt);
			$('#viewsCnt').text(response.viewsCnt);
		}
	});
};

function getPageNum() {
	var pageNum = $('#nowPageNum').val();
	$('#pageNum' + pageNum).css('backgroundColor', '#287bff');
	$('#pageNum' + pageNum).css('color', '#fff');
}

function getBoardList(pageNum, pageSize) {
	location.href = "/board?pageNum=" + pageNum + "&pageSize=" + pageSize;
}

function getBoard(boardId) {//클릭한 게시물 확인하는 함수 
	//boardId html에 hidden 하기
	//1. 화면 none -> block
	$('.update-popup').css('display', 'block');
	//AJAX 작성
	//2. AJAX를 이용해서 서버와 연결
	$.ajax({
		url: '/api/v1/board/boardId/' + boardId,
		type: 'GET',
		dataType: 'json',
		success: function(response) {
			//3. input에 데이터 set 해주기!  
			$('#upt-title').val(response.title);
			$('#upt-content').val(response.content);
			$('#boardIdHidden').val(boardId);
			setBoardViews(boardId); //조회 수 증가하는 함수
		}
	});
}//end

function setBoardViews(boardId) { //게시판 조회수 증가 함수
	$.ajax({
		url: '/api/v1/board/views/boardId/' + boardId,
		type: 'PATCH',
		dataType: 'json',
		success: function(response) {
			if (response > 0) {
				//추후 로직 예정 (에러 페이지로 이동하는 로직)
			}
		}
	});
}

/* 게시판 작성 함수 */
$('#contentSubmit').click(function() {
	if (confirm('게시글을 작성하시겠습니까?')) {
		var title = $('#title').val();
		var content = $('#content').val();
		var studentsId = $('#studentsId').val();

		if (title == '') {
			alert('제목을 입력 해주세요');
			$('#title').focus();
			return false;
		}
		if (content == '') {
			alert('내용을 작성 해주세요');
			$('#content').focus();
			return false;
		}
		var jsonData = {
			studentsId: studentsId,
			title: title,
			content: content
		};
		$.ajax({
			url: '/api/v1/board',
			type: 'POST',
			contentType: 'application/json', //서버에 json 타입으로 보낼 예정(요청)
			dataType: 'json', //서버 결과를 json으로 응답받겠다.
			data: JSON.stringify(jsonData),
			success: function(response) {
				if (response > 0) {
					var pageNum = $('#nowPageNum').val();
					getBoardList(pageNum, 10);
				}
			}
		});//ajax end
	}//if end 
});

//게시물 수정 하는 함수
$('#contentUpdate').click(function() {
	//1. 게시판 번호 확인
	var boardId = $('#boardIdHidden').val(); //hidden에 숨겨둔 boardId 가져오기.
	//2. JSON 생성
	var title = $('#upt-title').val();
	var content = $('#upt-content').val();
	var jsonData = {
		title: title,
		content: content
	};
	//3. AJAX를 이용해서 업데이트!
	$.ajax({
		url: '/api/v1/board/boardId/' + boardId,
		type: 'PATCH', //HTTP 메소드는 PATCH
		contentType: 'application/json', //서버에 json 타입으로 보낼 예정(요청)
		dataType: 'json', //서버 결과를 json으로 응답받겠다.
		data: JSON.stringify(jsonData),
		success: function(response) {
			if (response > 0) {
				alert('수정완료');
				var pageNum = $('#nowPageNum').val();
				getBoardList(pageNum, 10);
			}
		}
	});//ajax end
});//end

//게시물 삭제 하는 함수
$('#contentDelete').click(function() {
	var boardId = $('#boardIdHidden').val(); //hidden에 숨겨둔 boardId 가져오기.
	if (confirm('해당 게시물을 정말 삭제하시겠습니까?')) {
		$.ajax({
			url: '/api/v1/board/boardId/' + boardId,
			type: 'DELETE',
			dataType: 'json',
			success: function(response) {
				if (response > 0) {
					alert('삭제완료');
					var pageNum = $('#nowPageNum').val();
					getBoardList(pageNum, 10);
				}
			}
		});
	}
});

$('#searchBar').keyup(function(key) {
	var pageSize = 10;
	var pageNum = 1;
	if (key.keyCode == 13) {
		var search = $('#searchBar').val().trim();//input에 작성한 작성자를 가져옴
		if (search != '') {
			location.href = "/board/search?writer=" + search + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
		}
	}
});