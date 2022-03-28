const stages = document.querySelectorAll('.stage');
let s1 = 0;
let s2 = 0;
let s3 = 0;

// 각 stage의 텍스트컨텐츠(내용)이 셋 중 어느것에 해당하느냐에 따라 값을 +1 해줌. ===은 값과 타입을 모두 체크함.
stages.forEach((stage) => {
  if (stage.textContent === '시작전') s1++;
  else if (stage.textContent === '진행중') s2++;
  else if (stage.textContent === '완료') s3++;
});

const data = {
  labels: ['시작전', '진행중', '완료'],
  datasets: [
    {
      label: 'My First Dataset',
      data: [s1, s2, s3],
      backgroundColor: ['rgb(255, 99, 132)', 'rgb(54, 162, 235)', 'rgb(255, 205, 86)'],
      hoverOffset: 4,
    },
  ],
};

const config = {
  type: 'pie',
  data: data,
};

const myChart = new Chart(document.getElementById('myChart'), config);
