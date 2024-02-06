const texts = [
'재능있는 크리에이터의 사진들',
'才能あるクリエイターの写真を見せる',
'展示才华横溢的创作者的照片',
'Présentation de photos de créateurs talentueux',
'Präsentiert Fotos talentierter Kreativer',
'Exhibición de fotos de creadores talentosos',
'Showcasing photos of talented creators'
];

let currentIndex = 0;
const paragraph = document.querySelector('.texts');

setInterval(() => {
  paragraph.textContent = texts[currentIndex];
  currentIndex = (currentIndex + 1) % texts.length;
}, 1000);