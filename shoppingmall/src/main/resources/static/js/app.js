$(function () {
  $('a.deleteConfirm').click(function () {
    if (!confirm('삭제하겠습니까?')) return false; // 취소시 삭제 안됨
  });

  // 페이지 컨텐트에 CK에디터 추가
  if ($('#content').length) {
    // 제이쿼리에서 태그선택시 태그가 없어도 true가 나오기때문에 .length를 사용하면 있을때만 true가 됨
    ClassicEditor.create(document.querySelector('#content')).catch((error) => {
      console.error(error);
    });
  }
  // 상품설명에 CK에디터 추가
  if ($('#description').length) {
    // 제이쿼리에서 태그선택시 태그가 없어도 true가 나오기때문에 .length를 사용하면 있을때만 true가 됨
    ClassicEditor.create(document.querySelector('#description')).catch((error) => {
      console.error(error);
    });
  }
});
