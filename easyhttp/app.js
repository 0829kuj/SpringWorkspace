const http = new EasyHTTP();

//유저 데이터들을 받기
// http
//   .get('https://jsonplaceholder.typicode.com/users')
//   .then((data) => console.log(data))
//   .catch((err) => console.log(err));

// 입력용 유저 데이터 (POST)
const data = {
  name: '길동이',
  username: 'gildong',
  email: 'gildong@gmail.com',
};

//새 유저 생성
// http
//   .post('https://jsonplaceholder.typicode.com/users', data)
//   .then((data) => console.log(data))
//   .catch((err) => console.log(err));

//업데이트;
// http
//   .put('https://jsonplaceholder.typicode.com/users/2', data)
//   .then((data) => console.log(data))
//   .catch((err) => console.log(err));

// 삭제
http
  .delete('https://jsonplaceholder.typicode.com/users/2')
  .then((data) => console.log(data))
  .catch((err) => console.log(err));
